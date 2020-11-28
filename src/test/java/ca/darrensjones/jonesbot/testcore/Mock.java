package ca.darrensjones.jonesbot.testcore;

import java.io.File;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.mockserver.client.MockServerClient;
import org.mockserver.configuration.ConfigurationProperties;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.matchers.Times;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

import ca.darrensjones.jonesbot.log.Reporter;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-26
 * @since 1.0.0 2020-11-25
 */
public class Mock {

	private static ClientAndServer clientAndServer;
	private static MockServerClient client;

	public static ClientAndServer getClientAndServer() {
		return clientAndServer;
	}

	public static void reset() {
		clientAndServer = new ClientAndServer(1080);
		ConfigurationProperties.disableSystemOut(true); // Bot uses it's own output

		client = new MockServerClient("localhost", 1080);

		Reporter.debug("Mock Server and Client reset");
	}

	public static void setExpectation(String requestMethod, String requestPath, int responseStatusCode, String responseBodyFilePath) {
		try {
			String response = FileUtils.readFileToString(new File(responseBodyFilePath), StandardCharsets.UTF_8);
			client.when(HttpRequest.request().withMethod("GET").withPath("/fact"), Times.unlimited())
					.respond(HttpResponse.response().withStatusCode(200).withBody(response));
		} catch (Exception e) {
			Reporter.fatal(e.getMessage());
		}
	}
}