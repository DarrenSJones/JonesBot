package ca.darrensjones.jonesbot.test.command;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import ca.darrensjones.jonesbot.test.BotTest;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-24
 * @since 1.0.0 2020-11-24
 */
public class TCommandHelp {

	private static final AbstractCommand c = BotTest.get().commandHandler.getCommand("ping");

	@Test
	public void commandBasics() {
		Assert.assertEquals(c.getName(), "Help");

		Assert.assertEquals(c.getDescription(), "The full list of Commands (you are here!)");

		Assert.assertEquals(c.getTriggers().length, 2);
		Assert.assertEquals(c.getTriggers()[0], "help");
		Assert.assertEquals(c.getTriggers()[1], "h");

		Assert.assertEquals(c.getHelp(), "**!help** The full list of Commands (you are here!)");

		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}
}