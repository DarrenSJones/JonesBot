package ca.darrensjones.jonesbot.test.handler;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.handler.CommandHandler;
import ca.darrensjones.jonesbot.testcore.BotTest;

/**
 * @author Darren Jones
 * @version 1.1.2 2021-01-13
 * @since 1.0.0 2020-11-23
 */
public class TCommandHandler {

	private static final CommandHandler c = BotTest.get().commandHandler;

	@Test
	public void isCommand() {

		// Prefix Check
		Assert.assertTrue(c.isCommand("!"));
		Assert.assertTrue(c.isCommand("!!"));
		Assert.assertTrue(c.isCommand("!@"));
		Assert.assertFalse(c.isCommand("@"));
		Assert.assertFalse(c.isCommand("@!"));
		Assert.assertFalse(c.isCommand("#"));
		Assert.assertFalse(c.isCommand("$"));
		Assert.assertFalse(c.isCommand("%"));
		Assert.assertFalse(c.isCommand("^"));
		Assert.assertFalse(c.isCommand("&"));
		Assert.assertFalse(c.isCommand("*"));
		Assert.assertFalse(c.isCommand(">"));
		Assert.assertFalse(c.isCommand(">>"));
		Assert.assertFalse(c.isCommand("?"));
		Assert.assertFalse(c.isCommand("-"));
		Assert.assertFalse(c.isCommand("_"));
		Assert.assertFalse(c.isCommand("="));
		Assert.assertFalse(c.isCommand("+"));
		Assert.assertFalse(c.isCommand("|"));
		Assert.assertFalse(c.isCommand("~"));

		// Valid Commands
		Assert.assertTrue(c.isCommand("!ping"));
		Assert.assertTrue(c.isCommand("!p"));
		Assert.assertTrue(c.isCommand("!reaction"));
		Assert.assertTrue(c.isCommand("!reactions"));

		// Close-to-valid Commands
		Assert.assertFalse(c.isCommand("ping"));
		Assert.assertFalse(c.isCommand("p"));
		Assert.assertFalse(c.isCommand("reaction"));
		Assert.assertFalse(c.isCommand("reactions"));

		// Invalid Prefix
		Assert.assertFalse(c.isCommand("@ping"));
		Assert.assertFalse(c.isCommand("#p"));
		Assert.assertFalse(c.isCommand("$reaction"));
		Assert.assertFalse(c.isCommand("%reactions"));
		Assert.assertFalse(c.isCommand("@t !ping"));
		Assert.assertFalse(c.isCommand("#t !p"));
		Assert.assertFalse(c.isCommand("$t !reaction"));
		Assert.assertFalse(c.isCommand("%t !reactions"));
	}

	@Test(dependsOnMethods = "isCommand", alwaysRun = true)
	public void getCommand() {

		// Valid Commands
		Assert.assertNotNull(c.getCommand("!help"));
		Assert.assertNotNull(c.getCommand("!h"));
		Assert.assertNotNull(c.getCommand("!ping"));
		Assert.assertNotNull(c.getCommand("!p"));
		Assert.assertNotNull(c.getCommand("!r"));
		Assert.assertNotNull(c.getCommand("!reaction"));
		Assert.assertNotNull(c.getCommand("!reactions"));

		// Close-to-valid Commands
		Assert.assertNull(c.getCommand("!pi"));
		Assert.assertNull(c.getCommand("!pin"));
		Assert.assertNull(c.getCommand("!pings"));
		Assert.assertNull(c.getCommand("!pong"));
		Assert.assertNull(c.getCommand("!re"));
		Assert.assertNull(c.getCommand("!reactio"));
		Assert.assertNull(c.getCommand("!reactionss"));
	}

	@Test(dependsOnMethods = "getCommand", alwaysRun = true)
	public void hasHelp() {

		// Valid Help
		Assert.assertTrue(c.hasHelp("!ping !help"));
		Assert.assertTrue(c.hasHelp("!reaction !help"));

		// Close-to-valid Help
		Assert.assertFalse(c.hasHelp("!h"));
		Assert.assertFalse(c.hasHelp("!help"));
		Assert.assertFalse(c.hasHelp("!ping !h"));
		Assert.assertFalse(c.hasHelp("!ping !helps"));
	}
}