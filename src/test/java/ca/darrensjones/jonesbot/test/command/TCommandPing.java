package ca.darrensjones.jonesbot.test.command;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.command.AbstractCommand;
import ca.darrensjones.jonesbot.test.BotTest;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-23
 * @since 1.0.0 2020-11-23
 */
public class TCommandPing {

	private static final AbstractCommand c = BotTest.get().commandHandler.getCommand("ping");

	@Test
	public void commandBasics() {
		Assert.assertEquals(c.getName(), "Ping");

		Assert.assertEquals(c.getDescription(), "Pong!");

		Assert.assertEquals(c.getTriggers().length, 2);
		Assert.assertEquals(c.getTriggers()[0], "ping");
		Assert.assertEquals(c.getTriggers()[1], "p");

		Assert.assertEquals(c.getHelp(), "**%sping** Pong!");

		Assert.assertTrue(c.isVisible());
	}
}