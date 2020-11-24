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
public class TCommandReaction {

	private static final AbstractCommand c = BotTest.get().commandHandler.getCommand("reaction");

	@Test
	public void commandBasics() {
		Assert.assertEquals(c.getName(), "Reaction");

		Assert.assertEquals(c.getDescription(), "Displays a list of Reactions recognized by the Bot");

		Assert.assertTrue(c.isVisible());
	}
}