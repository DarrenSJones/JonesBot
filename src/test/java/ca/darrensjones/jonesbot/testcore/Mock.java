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
 * @version 1.0.1 2020-12-19
 * @since 1.0.0 2020-11-25
 */
public class Mock {

	private static ClientAndServer server;
	private static MockServerClient client;

	private static final String host = "localhost";
	private static final int port = 1080;

	public static void reset() {
		if (server == null) {
			server = new ClientAndServer(port);
			ConfigurationProperties.disableSystemOut(true); // Bot uses it's own output
			Reporter.debug(String.format("Mock Server reset. host:[%s] port:[%s]", host, port));
		}

		client = new MockServerClient(host, port);
		Reporter.debug(String.format("Mock Client reset. host:[%s] port:[%s]", host, port));
	}

	public static void setExpectation(String requestMethod, String requestPath, int responseStatusCode, File file) {
		String path = requestPath;
		List<Parameter> parameters = new ArrayList<Parameter>();
		if (path.contains("?")) {
			path = requestPath.split("\\?")[0];
			String query = requestPath.split("\\?")[1];
			if (StringUtils.isNotBlank(query)) {
				for (String pair : query.split("&")) {
					parameters.add(Parameter.param(pair.split("=")[0], pair.split("=")[1]));
				}
			}
		}

		try {
			String responseBody = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
			client.when(HttpRequest.request().withMethod(requestMethod).withPath(requestPath).withQueryStringParameters(parameters), Times.unlimited())
					.respond(HttpResponse.response().withStatusCode(responseStatusCode).withBody(responseBody));
		} catch (Exception e) {
			Reporter.fatal("SetExpectation Exception.\n" + e.getMessage());
		}
	}
}