package ca.darrensjones.jonesbot.testcore;

import org.mockserver.client.MockServerClient;
import org.mockserver.configuration.ConfigurationProperties;
import org.mockserver.integration.ClientAndServer;

import ca.darrensjones.jonesbot.log.Reporter;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-26
 * @since 1.0.0 2020-11-25
 */
public class Mock {

	private static ClientAndServer clientAndServer;
	private static MockServerClient client;

	public static void createClientAndServer() {
		clientAndServer = new ClientAndServer(1080);
		ConfigurationProperties.disableSystemOut(true); // Bot uses it's own output
		Reporter.debug("Created new MockServer ClientAndServer: localhost:1080");
	}

	public static ClientAndServer getClientAndServer() {
		if (clientAndServer == null) createClientAndServer();
		return clientAndServer;
	}

	public static void stopClientAndServer() {
		if (clientAndServer != null) clientAndServer.stop();
	}

	public static void createClient() {
		client = new MockServerClient("localhost", 1080);
		Reporter.debug("Created new MockServer Client: localhost:1080");
	}

	public static MockServerClient getClient() {
		if (clientAndServer == null) createClientAndServer();
		if (client == null) createClient();
		return client;
	}
}