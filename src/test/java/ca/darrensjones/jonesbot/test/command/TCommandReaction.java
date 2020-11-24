package ca.darrensjones.jonesbot.test.command;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.test.BotTest;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-24
 * @since 1.0.0 2020-11-23
 */
public class TCommandReaction {

	private static final AbstractCommand c = BotTest.get().commandHandler.getCommand("reaction");

	@Test
	public void commandBasics() {
		Assert.assertEquals(c.getName(), "Reaction");

		Assert.assertEquals(c.getDescription(), "A list of reactions the Bot will respond with");

		Assert.assertEquals(c.getTriggers().length, 2);
		Assert.assertEquals(c.getTriggers()[0], "reaction");
		Assert.assertEquals(c.getTriggers()[1], "reactions");

		Assert.assertEquals(c.getHelp(), "**%sreaction** A list of reactions the Bot will respond with");

		Assert.assertTrue(c.isVisible());
	}
}