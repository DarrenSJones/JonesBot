package ca.darrensjones.jonesbot.test.command;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.CommandCatFact;
import ca.darrensjones.jonesbot.command.utilities.Frinkiac;
import ca.darrensjones.jonesbot.testcore.BotTest;
import ca.darrensjones.jonesbot.testcore.Mock;
import ca.darrensjones.jonesbot.testcore.TestUtils;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-12-09
 * @since 1.0.0 2020-11-26
 */
public class CommandRequests {

	private static final Bot b = BotTest.get();

	@Test
	public void requestCatFact() {
		Mock.reset();
		String host = b.config.CATFACT_HOST;

		Mock.setExpectation("GET", "/fact", 200, new File("src/test/resources/mock/CatFact.json"));
		Assert.assertEquals(CommandCatFact.getResponse(host), "This is a test Cat Fact!");
	}

	@Test(dependsOnMethods = "requestCatFact", alwaysRun = true)
	public void requestSimpsons() {
		Mock.reset();
		String host = b.config.SIMPSONS_HOST;

		String random = "src/test/resources/mock/frinkiac/frinkiac_blank.json";
		Mock.setExpectation("GET", "/api/random", 200, new File(random));
		Assert.assertEquals(Frinkiac.getResponse(host, host + "/api/random"), TestUtils.readFile(random));

		String timestamp1 = "src/test/resources/mock/frinkiac/frinkiac_blank.json";
		String timestamp2 = "src/test/resources/mock/frinkiac/frinkiac_large.json";
		String timestamp3 = "src/test/resources/mock/frinkiac/frinkiac_not_found.json";
		Mock.setExpectation("GET", "/api/caption?e=S00E00&t=0", 200, new File(timestamp1));
		Mock.setExpectation("GET", "/api/caption?e=S99E99&t=9999999", 200, new File(timestamp2));
		Mock.setExpectation("GET", "/api/caption?e=S00E00&t=1", 200, new File(timestamp3));
		Assert.assertEquals(Frinkiac.getResponse(host, host + "/api/caption?e=S00E00&t=0"), TestUtils.readFile(timestamp1));
		Assert.assertEquals(Frinkiac.getResponse(host, host + "/api/caption?e=S99E99&t=9999999"), TestUtils.readFile(timestamp2));
		Assert.assertEquals(Frinkiac.getResponse(host, host + "/api/caption?e=S00E00&t=1"), TestUtils.readFile(timestamp3));

		String query1 = "src/test/resources/mock/frinkiac/frinkiac_query.json";
		String query2 = "src/test/resources/mock/frinkiac/frinkiac_query_large.json";
		String query3 = "src/test/resources/mock/frinkiac/frinkiac_query_blank.json";
		Mock.setExpectation("GET", "/api/search?q=test1", 200, new File(query1));
		Mock.setExpectation("GET", "/api/search?q=test2", 200, new File(query2));
		Mock.setExpectation("GET", "/api/search?q=test3", 200, new File(query3));
		Assert.assertEquals(Frinkiac.getResponse(host, host + "/api/search?q=test1"), TestUtils.readFile(timestamp1));
		Assert.assertEquals(Frinkiac.getResponse(host, host + "/api/search?q=test2"), TestUtils.readFile(timestamp2));
		Assert.assertEquals(Frinkiac.getResponse(host, host + "/api/search?q=test3"), "");
	}
}