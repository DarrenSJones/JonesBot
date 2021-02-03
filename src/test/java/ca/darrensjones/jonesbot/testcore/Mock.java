package ca.darrensjones.jonesbot.testcore;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.mockserver.client.MockServerClient;
import org.mockserver.configuration.ConfigurationProperties;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.matchers.Times;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.Parameter;

import ca.darrensjones.jonesbot.log.Reporter;

/**
 * @author Darren Jones
 * @version 1.1.4 2021-02-02
 * @since 1.0.0 2020-11-25
 */
public class Mock {

	private static String host;
	private static String port;

	private static ClientAndServer server;
	private static MockServerClient client;

	/**
	 * If the Mock Server hasn't be started, starts it and a new Client. Otherwise, only resets the Client.
	 */
	public static void reset() {
		if (StringUtils.isBlank(host)) setMock();

		if (server == null) {
			server = new ClientAndServer(Integer.parseInt(port));
			ConfigurationProperties.disableSystemOut(true); // Bot uses it's own output
			Reporter.debug(String.format("Mock Server reset. host:[%s] port:[%s]", host, port));
		}

		client = new MockServerClient(host, Integer.parseInt(port));
		Reporter.debug(String.format("Mock Client reset. host:[%s] port:[%s]", host, port));
	}

	/**
	 * Sets all Mock Expectations before external requests are sent.
	 * 
	 * @param method             Currently only supports "GET"
	 * @param path               URL the Expectation is mocking
	 * @param responseStatusCode Status Code to return with the response
	 * @param file               File that contains the response Body
	 */
	public static void setExpectation(String method, String path, int responseStatusCode, File file) {
		String requestPath = path;
		List<Parameter> requestParameters = new ArrayList<Parameter>();
		if (path.contains("?")) {
			requestPath = path.split("\\?")[0];
			if (StringUtils.isNotBlank(path.split("\\?")[1])) {
				for (String pair : path.split("\\?")[1].split("&")) {
					requestParameters.add(Parameter.param(pair.split("=")[0], pair.split("=")[1]));
				}
			}
		}

		try {
			String responseBody = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
			client.when(HttpRequest.request().withMethod(method).withPath(requestPath).withQueryStringParameters(requestParameters), Times.unlimited())
					.respond(HttpResponse.response().withStatusCode(responseStatusCode).withBody(responseBody));
		} catch (Exception e) {
			Reporter.fatal("Mock setExpectation.", e);
		}
	}

	/**
	 * Sets the Mock Host and Port from the database.properties file.
	 */
	private static void setMock() {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(new File("src/main/resources/config/database.properties")));
			host = properties.getProperty("mockHost");
			port = properties.getProperty("mockPort");
			Reporter.debug("Loaded mock properties.");
		} catch (Exception e) {
			Reporter.fatal("Failed to set mock properties.", e);
		}
	}
}