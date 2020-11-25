package ca.darrensjones.jonesbot.testcore;

import org.mockserver.integration.ClientAndServer;

import ca.darrensjones.jonesbot.log.Reporter;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-24
 * @since 1.0.0 2020-11-24
 */
public class BotMockServer {

	private static ClientAndServer cs;

	public static void createClientAndServer() {
		cs = new ClientAndServer(1080);
		Reporter.debug("Created new MockServer ClientAndServer");
	}

	public static ClientAndServer getClientAndServer() {
		if (cs == null) createClientAndServer();
		return cs;
	}
}