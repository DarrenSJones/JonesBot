package ca.darrensjones.jonesbot.test.command;

import ca.darrensjones.jonesbot.command.CommandCatFact;
import ca.darrensjones.jonesbot.testcore.Mock;
import ca.darrensjones.jonesbot.testcore.TBot;
import java.io.File;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-24
 * @since   1.0.0 2020-11-26
 */
public class CommandRequests {

	@Test
	public void requestCatFact() {
		Mock.reset();
		String host = TBot.getConfig().HOST_CATFACT;

		Mock.setExpectation("GET", "/fact", 200, new File("src/test/resources/mock/CatFact.json"));
		Assert.assertEquals(CommandCatFact.getResponse(host), "This is a test Cat Fact!");
	}
}