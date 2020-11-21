package ca.darrensjones.jonesbot.test.reaction;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.handlers.ReactionHandler;
import ca.darrensjones.jonesbot.test.BotTest;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-21
 * @since 1.0.0 2020-11-21
 */
public class GetReactions {

	private static final ReactionHandler r = BotTest.get().reactionHandler;

	@Test
	public void validateGetMatchNoRegex() {
		Assert.assertEquals(r.getReactions("canada").size(), 1);
		Assert.assertEquals(r.getReactions("canada").get(0).shortcode, ":flag_ca:");
		Assert.assertEquals(r.getReactions("canada").get(0).unicode, "🇨🇦");
		Assert.assertEquals(r.getReactions("canada").get(0).regex, "canada");

		Assert.assertEquals(r.getReactions("canad").size(), 0);
	}

	@Test(dependsOnMethods = "validateGetMatchNoRegex", alwaysRun = true)
	public void validateGetMatchSimple() {
		Assert.assertEquals(r.getReactions("sandwich").size(), 1);
		Assert.assertEquals(r.getReactions("sandwich").get(0).shortcode, ":sandwich:");
		Assert.assertEquals(r.getReactions("sandwich").get(0).unicode, "🥪");
		Assert.assertEquals(r.getReactions("sandwich").get(0).regex, "sandwich(es)?");

		Assert.assertEquals(r.getReactions("sandwiches").size(), 1);
		Assert.assertEquals(r.getReactions("sandwiches").get(0).shortcode, ":sandwich:");
		Assert.assertEquals(r.getReactions("sandwiches").get(0).unicode, "🥪");
		Assert.assertEquals(r.getReactions("sandwiches").get(0).regex, "sandwich(es)?");

		Assert.assertEquals(r.getReactions("sandwiche").size(), 0);
	}

	@Test(dependsOnMethods = "validateGetMatchSimple", alwaysRun = true)
	public void validateGetMatchMultiple() {
		Assert.assertEquals(r.getReactions("sandwich sandwich").size(), 1);
		Assert.assertEquals(r.getReactions("sandwich sandwich").get(0).shortcode, ":sandwich:");
		Assert.assertEquals(r.getReactions("sandwich sandwich").get(0).unicode, "🥪");
		Assert.assertEquals(r.getReactions("sandwich sandwich").get(0).regex, "sandwich(es)?");

		Assert.assertEquals(r.getReactions("sandwiches sandwiches").size(), 1);
		Assert.assertEquals(r.getReactions("sandwiches sandwiches").get(0).shortcode, ":sandwich:");
		Assert.assertEquals(r.getReactions("sandwiches sandwiches").get(0).unicode, "🥪");
		Assert.assertEquals(r.getReactions("sandwiches sandwiches").get(0).regex, "sandwich(es)?");

		Assert.assertEquals(r.getReactions("sandwich sandwiches").size(), 1);
		Assert.assertEquals(r.getReactions("sandwich sandwiches").get(0).shortcode, ":sandwich:");
		Assert.assertEquals(r.getReactions("sandwich sandwiches").get(0).unicode, "🥪");
		Assert.assertEquals(r.getReactions("sandwich sandwiches").get(0).regex, "sandwich(es)?");

		Assert.assertEquals(r.getReactions("sandwich sandwiche").size(), 1);
		Assert.assertEquals(r.getReactions("sandwich sandwiche").get(0).shortcode, ":sandwich:");
		Assert.assertEquals(r.getReactions("sandwich sandwiche").get(0).unicode, "🥪");
		Assert.assertEquals(r.getReactions("sandwich sandwiche").get(0).regex, "sandwich(es)?");

		Assert.assertEquals(r.getReactions("sandwich sandwich sandwich sandwich sandwich").size(), 1);
		Assert.assertEquals(r.getReactions("sandwic sandwiche sandwichess").size(), 0);
	}

	@Test(dependsOnMethods = "validateGetMatchMultiple", alwaysRun = true)
	public void validateGetMatchMultipleMatches() {
		Assert.assertEquals(r.getReactions("sandwich wizard").size(), 2);
		Assert.assertEquals(r.getReactions("sandwich wizard").get(0).shortcode, ":sandwich:");
		Assert.assertEquals(r.getReactions("sandwich wizard").get(0).unicode, "🥪");
		Assert.assertEquals(r.getReactions("sandwich wizard").get(0).regex, "sandwich(es)?");
		Assert.assertEquals(r.getReactions("sandwich wizard").get(1).shortcode, ":man_mage:");
		Assert.assertEquals(r.getReactions("sandwich wizard").get(1).unicode, "🧙‍♂️");
		Assert.assertEquals(r.getReactions("sandwich wizard").get(1).regex, "(mages?|wizards?)");

		Assert.assertEquals(r.getReactions("sandwich wizard sandwich").size(), 2);
		Assert.assertEquals(r.getReactions("sandwich wizard sandwich").get(0).shortcode, ":sandwich:");
		Assert.assertEquals(r.getReactions("sandwich wizard sandwich").get(0).unicode, "🥪");
		Assert.assertEquals(r.getReactions("sandwich wizard sandwich").get(0).regex, "sandwich(es)?");
		Assert.assertEquals(r.getReactions("sandwich wizard sandwich").get(1).shortcode, ":man_mage:");
		Assert.assertEquals(r.getReactions("sandwich wizard sandwich").get(1).unicode, "🧙‍♂️");
		Assert.assertEquals(r.getReactions("sandwich wizard sandwich").get(1).regex, "(mages?|wizards?)");

		Assert.assertEquals(r.getReactions("sandwiche wizard sandwich").size(), 2);
		Assert.assertEquals(r.getReactions("sandwiche wizard sandwich").get(0).shortcode, ":man_mage:");
		Assert.assertEquals(r.getReactions("sandwiche wizard sandwich").get(0).unicode, "🧙‍♂️");
		Assert.assertEquals(r.getReactions("sandwiche wizard sandwich").get(0).regex, "(mages?|wizards?)");
		Assert.assertEquals(r.getReactions("sandwiche wizard sandwich").get(1).shortcode, ":sandwich:");
		Assert.assertEquals(r.getReactions("sandwiche wizard sandwich").get(1).unicode, "🥪");
		Assert.assertEquals(r.getReactions("sandwiche wizard sandwich").get(1).regex, "sandwich(es)?");

		Assert.assertEquals(r.getReactions("sandwich wizard canada").size(), 3);
		Assert.assertEquals(r.getReactions("sandwich wizard canada").get(0).shortcode, ":sandwich:");
		Assert.assertEquals(r.getReactions("sandwich wizard canada").get(0).unicode, "🥪");
		Assert.assertEquals(r.getReactions("sandwich wizard canada").get(0).regex, "sandwich(es)?");
		Assert.assertEquals(r.getReactions("sandwich wizard canada").get(1).shortcode, ":man_mage:");
		Assert.assertEquals(r.getReactions("sandwich wizard canada").get(1).unicode, "🧙‍♂️");
		Assert.assertEquals(r.getReactions("sandwich wizard canada").get(1).regex, "(mages?|wizards?)");
		Assert.assertEquals(r.getReactions("sandwich wizard canada").get(2).shortcode, ":flag_ca:");
		Assert.assertEquals(r.getReactions("sandwich wizard canada").get(2).unicode, "🇨🇦");
		Assert.assertEquals(r.getReactions("sandwich wizard canada").get(2).regex, "canada");

		Assert.assertEquals(r.getReactions("sandwiches wizards top hat canada").size(), 4);
	}
}
