package ca.darrensjones.jonesbot.test.command;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.CommandCatFact;
import ca.darrensjones.jonesbot.testcore.BotTest;
import ca.darrensjones.jonesbot.testcore.Mock;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-27
 * @since 1.0.0 2020-11-26
 */
public class CommandRequests {

	private static final Bot b = BotTest.get();

	@Test
	public void requestCatFact() {
		Mock.reset();
		Mock.setExpectation("GET", "/fact", 200, "src/test/resources/mock/CatFact.json");

		Assert.assertEquals(new CommandCatFact().getResponse(b.config.CATFACT_HOST), "This is a test Cat Fact!");
	}
}