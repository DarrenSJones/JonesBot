package ca.darrensjones.jonesbot.testcore;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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
 * @version 1.0.0 2020-12-09
 * @since 1.0.0 2020-11-25
 */
public class Mock {

	private static ClientAndServer clientAndServer;
	private static MockServerClient client;

	public static ClientAndServer getClientAndServer() {
		return clientAndServer;
	}

	public static void reset() {
		if (clientAndServer == null) {
			clientAndServer = new ClientAndServer(1080);
			ConfigurationProperties.disableSystemOut(true); // Bot uses it's own output
			Reporter.debug("Mock Server reset");
		}

		client = new MockServerClient("localhost", 1080);
		Reporter.debug("Mock Client reset");
	}

	public static void setExpectation(String requestMethod, String requestPath, int responseStatusCode, File file) {
		String path;
		List<Parameter> parameters = new ArrayList<Parameter>();

		if (requestPath.contains("?")) {
			path = requestPath.split("\\?")[0];
			String query = requestPath.split("\\?")[1];
			if (StringUtils.isNotBlank(query)) {
				for (String pair : query.split("&")) parameters.add(Parameter.param(pair.split("=")[0], pair.split("=")[1]));
			} else {
				parameters = null;
			}
		} else {
			path = requestPath;
			parameters = null;
		}

		try {
			String response = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
			setExpectation(requestMethod, path, parameters, responseStatusCode, response);
		} catch (Exception e) {
			Reporter.fatal(e.getMessage());
		}
	}

	public static void setExpectation(String requestMethod, String requestPath, List<Parameter> parameters, int responseStatusCode, String response) {
		client.when(HttpRequest.request().withMethod(requestMethod).withPath(requestPath).withQueryStringParameters(parameters), Times.unlimited())
				.respond(HttpResponse.response().withStatusCode(responseStatusCode).withBody(response));
	}
}