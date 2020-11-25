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
public class TCommandCatFact {

	private static final AbstractCommand c = BotTest.get().commandHandler.getCommand("catfact");

	@Test
	public void commandBasics() {
		Assert.assertEquals(c.getName(), "Cat Fact");

		Assert.assertEquals(c.getDescription(), "Displays a random cat fact from https://catfact.ninja");

		Assert.assertEquals(c.getTriggers().length, 2);
		Assert.assertEquals(c.getTriggers()[0], "catfact");
		Assert.assertEquals(c.getTriggers()[1], "catfacts");

		Assert.assertEquals(c.getHelp(), "**%scatfact** Displays a random cat fact from https://catfact.ninja");

		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}
}