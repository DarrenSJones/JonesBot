package ca.darrensjones.jonesbot.test.handler;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.handler.CommandHandler;
import ca.darrensjones.jonesbot.test.BotTest;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-24
 * @since 1.0.0 2020-11-23
 */
public class TCommandHandler {

	private static final CommandHandler c = BotTest.get().commandHandler;

	@Test
	public void isCommand() {

		/* Prefix Check */
		Assert.assertTrue(c.isCommand("!"));
		Assert.assertTrue(c.isCommand("!!"));
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

		/* Valid Commands */
		Assert.assertTrue(c.isCommand("!ping"));
		Assert.assertTrue(c.isCommand("!p"));
		Assert.assertTrue(c.isCommand("!reaction"));
		Assert.assertTrue(c.isCommand("!reactions"));

		/* Close-to-valid Commands */
		Assert.assertFalse(c.isCommand("ping"));
		Assert.assertFalse(c.isCommand("p"));
		Assert.assertFalse(c.isCommand("reaction"));
		Assert.assertFalse(c.isCommand("reactions"));
	}

	@Test(dependsOnMethods = "isCommand", alwaysRun = true)
	public void getCommands() {

		/* Validates List */
		Assert.assertEquals(c.getCommands().size(), 2);
		Assert.assertEquals(c.getCommands().get(0).getName(), "Ping");
		Assert.assertEquals(c.getCommands().get(1).getName(), "Reaction");
	}

	@Test(dependsOnMethods = "getCommands", alwaysRun = true)
	public void getCommand() {

		/* Valid Commands */
		Assert.assertNotNull(c.getCommand("ping"));
		Assert.assertNotNull(c.getCommand("p"));
		Assert.assertNotNull(c.getCommand("reaction"));
		Assert.assertNotNull(c.getCommand("reactions"));

		/* Close-to-valid Commands */
		Assert.assertNull(c.getCommand("pi"));
		Assert.assertNull(c.getCommand("pin"));
		Assert.assertNull(c.getCommand("pings"));
		Assert.assertNull(c.getCommand("pong"));
		Assert.assertNull(c.getCommand("r"));
		Assert.assertNull(c.getCommand("re"));
		Assert.assertNull(c.getCommand("reactio"));
		Assert.assertNull(c.getCommand("reactionss"));
	}
}