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
public class TCommandCowbell {

	private static final AbstractCommand c = BotTest.get().commandHandler.getCommand("cowbell");

	@Test
	public void commandBasics() {
		Assert.assertEquals(c.getName(), "Cowbell");

		Assert.assertEquals(c.getDescription(), "When you need more of it");

		Assert.assertEquals(c.getTriggers().length, 1);
		Assert.assertEquals(c.getTriggers()[0], "cowbell");

		Assert.assertEquals(c.getHelp(), "**%scowbell** When you need more of it");

		Assert.assertEquals(c.visibility(), CommandVisibility.HIDDEN);
	}
}