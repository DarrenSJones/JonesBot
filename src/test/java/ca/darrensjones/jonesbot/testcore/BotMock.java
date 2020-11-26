package ca.darrensjones.jonesbot.testcore;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.mockserver.integration.ClientAndServer;

import ca.darrensjones.jonesbot.log.Reporter;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-25
 * @since 1.0.0 2020-11-25
 */
public class BotMock {

	private static ClientAndServer clientAndServer;
	private static CloseableHttpClient httpClient;

	public static void createClientAndServer() {
		clientAndServer = new ClientAndServer(1080);
		Reporter.debug("Created new MockServer ClientAndServer");
	}

	public static ClientAndServer getClientAndServer() {
		if (clientAndServer == null) createClientAndServer();
		return clientAndServer;
	}

	public static void createHttpClient() {
		httpClient = HttpClients.createDefault();
		Reporter.debug("Created new MockServer HttpClient");
	}

	public static CloseableHttpClient getHttpClient() {
		if (httpClient == null) createHttpClient();
		return httpClient;
	}
}