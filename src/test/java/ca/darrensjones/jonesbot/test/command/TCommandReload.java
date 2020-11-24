package ca.darrensjones.jonesbot.test.command;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import ca.darrensjones.jonesbot.test.BotTest;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-23
 * @since 1.0.0 2020-11-23
 */
public class TCommandReload {

	private static final AbstractCommand c = BotTest.get().commandHandler.getCommand("reload");

	@Test
	public void commandBasics() {
		Assert.assertEquals(c.getName(), "Reload");

		Assert.assertEquals(c.getDescription(), "Reloads all lists from the SQL Database.");

		Assert.assertEquals(c.getTriggers().length, 1);
		Assert.assertEquals(c.getTriggers()[0], "reload");

		Assert.assertEquals(c.getHelp(), "**%sreload** Reloads all lists from the SQL Database.");

		Assert.assertEquals(c.visibility(), CommandVisibility.OWNER);
	}
}