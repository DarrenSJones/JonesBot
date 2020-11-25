package ca.darrensjones.jonesbot.test.handler;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.handler.CommandHandler;
import ca.darrensjones.jonesbot.testcore.BotTest;

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
		Assert.assertEquals(c.getCommands().size(), 7);
		Assert.assertEquals(c.getCommands().get(0).getName(), "Cat Fact");
		Assert.assertEquals(c.getCommands().get(1).getName(), "Cowbell");
		Assert.assertEquals(c.getCommands().get(2).getName(), "Help");
		Assert.assertEquals(c.getCommands().get(3).getName(), "Owner");
		Assert.assertEquals(c.getCommands().get(4).getName(), "Ping");
		Assert.assertEquals(c.getCommands().get(5).getName(), "Reaction");
		Assert.assertEquals(c.getCommands().get(6).getName(), "Reload");
	}

	@Test(dependsOnMethods = "getCommands", alwaysRun = true)
	public void getCommand() {

		/* Valid Commands */
		Assert.assertNotNull(c.getCommand("help"));
		Assert.assertNotNull(c.getCommand("h"));
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

	@Test(dependsOnMethods = "getCommand", alwaysRun = true)
	public void hasHelp() {

		/* Valid Help */
		Assert.assertTrue(c.hasHelp("!ping !help"));
		Assert.assertTrue(c.hasHelp("!reaction !help"));

		/* Close-to-valid Help */
		Assert.assertFalse(c.hasHelp("!h"));
		Assert.assertFalse(c.hasHelp("!help"));
		Assert.assertFalse(c.hasHelp("!ping !h"));
		Assert.assertFalse(c.hasHelp("!ping !helps"));
	}
}