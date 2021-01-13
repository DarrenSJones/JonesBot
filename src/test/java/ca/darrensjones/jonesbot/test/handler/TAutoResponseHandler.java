package ca.darrensjones.jonesbot.test.handler;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.handler.AutoResponseHandler;
import ca.darrensjones.jonesbot.testcore.BotTest;

/**
 * @author Darren Jones
 * @version 1.1.2 2021-01-13
 * @since 1.0.0 2020-11-21
 */
public class TAutoResponseHandler {

	private static final AutoResponseHandler r = BotTest.get().autoResponseHandler;

	@Test
	public void hasReaction() {

		// Casing
		Assert.assertTrue(r.hasReaction("sandwich"));
		Assert.assertTrue(r.hasReaction("sandwich"));
		Assert.assertTrue(r.hasReaction("sandwiches"));
		Assert.assertTrue(r.hasReaction("Sandwich"));
		Assert.assertTrue(r.hasReaction("Sandwiches"));
		Assert.assertTrue(r.hasReaction("SandwicH"));
		Assert.assertTrue(r.hasReaction("SandwicHeS"));
		Assert.assertTrue(r.hasReaction("SANDWICH"));
		Assert.assertTrue(r.hasReaction("SANDWICHES"));
		Assert.assertTrue(r.hasReaction("SaNdWiChEs"));
		Assert.assertTrue(r.hasReaction("sAnDwIcHeS"));

		// Spacing
		Assert.assertTrue(r.hasReaction("sandwich "));
		Assert.assertTrue(r.hasReaction(" sandwich"));
		Assert.assertTrue(r.hasReaction(" sandwich "));
		Assert.assertTrue(r.hasReaction("sandwich a"));
		Assert.assertTrue(r.hasReaction("a sandwich"));
		Assert.assertTrue(r.hasReaction("a sandwich a"));
		Assert.assertTrue(r.hasReaction("sandwiches "));
		Assert.assertTrue(r.hasReaction(" sandwiches"));
		Assert.assertTrue(r.hasReaction(" sandwiches "));
		Assert.assertTrue(r.hasReaction("sandwiches a"));
		Assert.assertTrue(r.hasReaction("a sandwiches"));
		Assert.assertTrue(r.hasReaction("a sandwiches a"));

		// Special Characters
		Assert.assertTrue(r.hasReaction("!sandwich!"));
		Assert.assertTrue(r.hasReaction("a!sandwich!a"));
		Assert.assertTrue(r.hasReaction("@sandwich@"));
		Assert.assertTrue(r.hasReaction("a@sandwich@a"));
		Assert.assertTrue(r.hasReaction("#sandwich#"));
		Assert.assertTrue(r.hasReaction("a#sandwich#a"));
		Assert.assertTrue(r.hasReaction("$sandwich$"));
		Assert.assertTrue(r.hasReaction("a$sandwich$a"));
		Assert.assertTrue(r.hasReaction("%sandwich%"));
		Assert.assertTrue(r.hasReaction("a%sandwich%a"));
		Assert.assertTrue(r.hasReaction("^sandwich^"));
		Assert.assertTrue(r.hasReaction("a^sandwich^a"));
		Assert.assertTrue(r.hasReaction("&sandwich&"));
		Assert.assertTrue(r.hasReaction("a&sandwich&a"));
		Assert.assertTrue(r.hasReaction("*sandwich*"));
		Assert.assertTrue(r.hasReaction("a*sandwich*a"));
		Assert.assertTrue(r.hasReaction("(sandwich("));
		Assert.assertTrue(r.hasReaction("a(sandwich(a"));
		Assert.assertTrue(r.hasReaction(")sandwich)"));
		Assert.assertTrue(r.hasReaction("a)sandwich)a"));
		Assert.assertTrue(r.hasReaction("-sandwich-"));
		Assert.assertTrue(r.hasReaction("a-sandwich-a"));
		Assert.assertFalse(r.hasReaction("_sandwich_"));
		Assert.assertFalse(r.hasReaction("a_sandwich_a"));
		Assert.assertTrue(r.hasReaction("=sandwich="));
		Assert.assertTrue(r.hasReaction("a=sandwich=a"));
		Assert.assertTrue(r.hasReaction("+sandwich+"));
		Assert.assertTrue(r.hasReaction("a+sandwich+a"));
		Assert.assertTrue(r.hasReaction("[sandwich["));
		Assert.assertTrue(r.hasReaction("a[sandwich[a"));
		Assert.assertTrue(r.hasReaction("]sandwich]"));
		Assert.assertTrue(r.hasReaction("a]sandwich]a"));
		Assert.assertTrue(r.hasReaction("{sandwich{"));
		Assert.assertTrue(r.hasReaction("a{sandwich{a"));
		Assert.assertTrue(r.hasReaction("}sandwich}"));
		Assert.assertTrue(r.hasReaction("a}sandwich}a"));
		Assert.assertTrue(r.hasReaction("\\sandwich\\"));
		Assert.assertTrue(r.hasReaction("a\\sandwich\\a"));
		Assert.assertTrue(r.hasReaction("|sandwich|"));
		Assert.assertTrue(r.hasReaction("a|sandwich|a"));
		Assert.assertTrue(r.hasReaction(";sandwich;"));
		Assert.assertTrue(r.hasReaction("a;sandwich;a"));
		Assert.assertTrue(r.hasReaction(":sandwich:"));
		Assert.assertTrue(r.hasReaction("a:sandwich:a"));
		Assert.assertTrue(r.hasReaction("'sandwich'"));
		Assert.assertTrue(r.hasReaction("a'sandwich'a"));
		Assert.assertTrue(r.hasReaction("\"sandwich\""));
		Assert.assertTrue(r.hasReaction("a\"sandwich\"a"));
		Assert.assertTrue(r.hasReaction(",sandwich,"));
		Assert.assertTrue(r.hasReaction("a,sandwich,a"));
		Assert.assertTrue(r.hasReaction("<sandwich<"));
		Assert.assertTrue(r.hasReaction("a<sandwich<a"));
		Assert.assertTrue(r.hasReaction(".sandwich."));
		Assert.assertTrue(r.hasReaction("a.sandwich.a"));
		Assert.assertTrue(r.hasReaction(">sandwich>"));
		Assert.assertTrue(r.hasReaction("a>sandwich>a"));
		Assert.assertTrue(r.hasReaction("/sandwich/"));
		Assert.assertTrue(r.hasReaction("a/sandwich/a"));
		Assert.assertTrue(r.hasReaction("?sandwich?"));
		Assert.assertTrue(r.hasReaction("a?sandwich?a"));
		Assert.assertTrue(r.hasReaction("😊sandwich😊"));
		Assert.assertTrue(r.hasReaction("a😊sandwich😊a"));
		Assert.assertTrue(r.hasReaction("!sandwiches!"));
		Assert.assertTrue(r.hasReaction("a!sandwiches!a"));
		Assert.assertTrue(r.hasReaction("@sandwiches@"));
		Assert.assertTrue(r.hasReaction("a@sandwiches@a"));
		Assert.assertTrue(r.hasReaction("#sandwiches#"));
		Assert.assertTrue(r.hasReaction("a#sandwiches#a"));
		Assert.assertTrue(r.hasReaction("$sandwiches$"));
		Assert.assertTrue(r.hasReaction("a$sandwiches$a"));
		Assert.assertTrue(r.hasReaction("%sandwiches%"));
		Assert.assertTrue(r.hasReaction("a%sandwiches%a"));
		Assert.assertTrue(r.hasReaction("^sandwiches^"));
		Assert.assertTrue(r.hasReaction("a^sandwiches^a"));
		Assert.assertTrue(r.hasReaction("&sandwiches&"));
		Assert.assertTrue(r.hasReaction("a&sandwiches&a"));
		Assert.assertTrue(r.hasReaction("*sandwiches*"));
		Assert.assertTrue(r.hasReaction("a*sandwiches*a"));
		Assert.assertTrue(r.hasReaction("(sandwiches("));
		Assert.assertTrue(r.hasReaction("a(sandwiches(a"));
		Assert.assertTrue(r.hasReaction(")sandwiches)"));
		Assert.assertTrue(r.hasReaction("a)sandwiches)a"));
		Assert.assertTrue(r.hasReaction("-sandwiches-"));
		Assert.assertTrue(r.hasReaction("a-sandwiches-a"));
		Assert.assertFalse(r.hasReaction("_sandwiches_"));
		Assert.assertFalse(r.hasReaction("a_sandwiches_a"));
		Assert.assertTrue(r.hasReaction("=sandwiches="));
		Assert.assertTrue(r.hasReaction("a=sandwiches=a"));
		Assert.assertTrue(r.hasReaction("+sandwiches+"));
		Assert.assertTrue(r.hasReaction("a+sandwiches+a"));
		Assert.assertTrue(r.hasReaction("[sandwiches["));
		Assert.assertTrue(r.hasReaction("a[sandwiches[a"));
		Assert.assertTrue(r.hasReaction("]sandwiches]"));
		Assert.assertTrue(r.hasReaction("a]sandwiches]a"));
		Assert.assertTrue(r.hasReaction("{sandwiches{"));
		Assert.assertTrue(r.hasReaction("a{sandwiches{a"));
		Assert.assertTrue(r.hasReaction("}sandwiches}"));
		Assert.assertTrue(r.hasReaction("a}sandwiches}a"));
		Assert.assertTrue(r.hasReaction("\\sandwiches\\"));
		Assert.assertTrue(r.hasReaction("a\\sandwiches\\a"));
		Assert.assertTrue(r.hasReaction("|sandwiches|"));
		Assert.assertTrue(r.hasReaction("a|sandwiches|a"));
		Assert.assertTrue(r.hasReaction(";sandwiches;"));
		Assert.assertTrue(r.hasReaction("a;sandwiches;a"));
		Assert.assertTrue(r.hasReaction(":sandwiches:"));
		Assert.assertTrue(r.hasReaction("a:sandwiches:a"));
		Assert.assertTrue(r.hasReaction("'sandwiches'"));
		Assert.assertTrue(r.hasReaction("a'sandwiches'a"));
		Assert.assertTrue(r.hasReaction("\"sandwiches\""));
		Assert.assertTrue(r.hasReaction("a\"sandwiches\"a"));
		Assert.assertTrue(r.hasReaction(",sandwiches,"));
		Assert.assertTrue(r.hasReaction("a,sandwiches,a"));
		Assert.assertTrue(r.hasReaction("<sandwiches<"));
		Assert.assertTrue(r.hasReaction("a<sandwiches<a"));
		Assert.assertTrue(r.hasReaction(".sandwiches."));
		Assert.assertTrue(r.hasReaction("a.sandwiches.a"));
		Assert.assertTrue(r.hasReaction(">sandwiches>"));
		Assert.assertTrue(r.hasReaction("a>sandwiches>a"));
		Assert.assertTrue(r.hasReaction("/sandwiches/"));
		Assert.assertTrue(r.hasReaction("a/sandwiches/a"));
		Assert.assertTrue(r.hasReaction("?sandwiches?"));
		Assert.assertTrue(r.hasReaction("a?sandwiches?a"));
		Assert.assertTrue(r.hasReaction("😊sandwiches😊"));
		Assert.assertTrue(r.hasReaction("a😊sandwiches😊a"));

		// Negative Tests
		Assert.assertFalse(r.hasReaction("test"));
		Assert.assertFalse(r.hasReaction("s"));
		Assert.assertFalse(r.hasReaction("sandwic"));
		Assert.assertFalse(r.hasReaction("sandwiche"));
		Assert.assertFalse(r.hasReaction("asandwich"));
		Assert.assertFalse(r.hasReaction("sandwicha"));
		Assert.assertFalse(r.hasReaction("asandwicha"));
		Assert.assertFalse(r.hasReaction("asandwiches"));
		Assert.assertFalse(r.hasReaction("sandwichesa"));
		Assert.assertFalse(r.hasReaction("asandwichesa"));
		Assert.assertFalse(r.hasReaction("0sandwich"));
		Assert.assertFalse(r.hasReaction("sandwich0"));
		Assert.assertFalse(r.hasReaction("0sandwich0"));
		Assert.assertFalse(r.hasReaction("0sandwiches"));
		Assert.assertFalse(r.hasReaction("sandwiches0"));
		Assert.assertFalse(r.hasReaction("0sandwiches0"));

		// Special Cases
		Assert.assertTrue(r.hasReaction("top hat"));
		Assert.assertFalse(r.hasReaction("top  hat"));
		Assert.assertFalse(r.hasReaction("top x hat"));
	}

	@Test(dependsOnMethods = "hasReaction", alwaysRun = true)
	public void getReaction() {

		// Single Reaction Single Match Simple
		Assert.assertEquals(r.getReactions("lacrosse").size(), 1);
		Assert.assertEquals(r.getReactions("lacrosse").get(0).id, 1);
		Assert.assertEquals(r.getReactions("lacrosse").get(0).shortcode, ":lacrosse:");
		Assert.assertEquals(r.getReactions("lacrosse").get(0).unicode, "🥍");
		Assert.assertEquals(r.getReactions("lacrosse").get(0).regex, "lacrosse");

		// Single Reaction Single Match with Space
		Assert.assertEquals(r.getReactions("top hat").size(), 1);
		Assert.assertEquals(r.getReactions("top hat").get(0).id, 2);
		Assert.assertEquals(r.getReactions("top hat").get(0).shortcode, ":tophat:");
		Assert.assertEquals(r.getReactions("top hat").get(0).unicode, "🎩");
		Assert.assertEquals(r.getReactions("top hat").get(0).regex, "top hat");

		// Single Reaction Single Match with Regex
		Assert.assertEquals(r.getReactions("sandwich").size(), 1);
		Assert.assertEquals(r.getReactions("sandwich").get(0).id, 3);
		Assert.assertEquals(r.getReactions("sandwich").get(0).shortcode, ":sandwich:");
		Assert.assertEquals(r.getReactions("sandwich").get(0).unicode, "🥪");
		Assert.assertEquals(r.getReactions("sandwich").get(0).regex, "sandwich(es)?");
		Assert.assertEquals(r.getReactions("sandwiches").size(), 1);
		Assert.assertEquals(r.getReactions("sandwiches").get(0).id, 3);
		Assert.assertEquals(r.getReactions("sandwiches").get(0).shortcode, ":sandwich:");
		Assert.assertEquals(r.getReactions("sandwiches").get(0).unicode, "🥪");
		Assert.assertEquals(r.getReactions("sandwiches").get(0).regex, "sandwich(es)?");

		// Single Reaction with Multiple Matches
		Assert.assertEquals(r.getReactions("sandwich sandwich").size(), 1);
		Assert.assertEquals(r.getReactions("sandwich sandwich").get(0).id, 3);
		Assert.assertEquals(r.getReactions("sandwich sandwich").get(0).shortcode, ":sandwich:");
		Assert.assertEquals(r.getReactions("sandwich sandwich").get(0).unicode, "🥪");
		Assert.assertEquals(r.getReactions("sandwich sandwich").get(0).regex, "sandwich(es)?");
		Assert.assertEquals(r.getReactions("sandwiches sandwiches").size(), 1);
		Assert.assertEquals(r.getReactions("sandwiches sandwiches").get(0).id, 3);
		Assert.assertEquals(r.getReactions("sandwiches sandwiches").get(0).shortcode, ":sandwich:");
		Assert.assertEquals(r.getReactions("sandwiches sandwiches").get(0).unicode, "🥪");
		Assert.assertEquals(r.getReactions("sandwiches sandwiches").get(0).regex, "sandwich(es)?");
		Assert.assertEquals(r.getReactions("sandwich sandwiches").size(), 1);
		Assert.assertEquals(r.getReactions("sandwich sandwiches").get(0).id, 3);
		Assert.assertEquals(r.getReactions("sandwich sandwiches").get(0).shortcode, ":sandwich:");
		Assert.assertEquals(r.getReactions("sandwich sandwiches").get(0).unicode, "🥪");
		Assert.assertEquals(r.getReactions("sandwich sandwiches").get(0).regex, "sandwich(es)?");
		Assert.assertEquals(r.getReactions("sandwich sandwiche").size(), 1);
		Assert.assertEquals(r.getReactions("sandwich sandwiche").get(0).id, 3);
		Assert.assertEquals(r.getReactions("sandwich sandwiche").get(0).shortcode, ":sandwich:");
		Assert.assertEquals(r.getReactions("sandwich sandwiche").get(0).unicode, "🥪");
		Assert.assertEquals(r.getReactions("sandwich sandwiche").get(0).regex, "sandwich(es)?");
		Assert.assertEquals(r.getReactions("sandwich sandwich sandwich sandwich sandwich").size(), 1);

		// Multiple Reactions with Single Match
		Assert.assertEquals(r.getReactions("sandwich wizard").size(), 2);
		Assert.assertEquals(r.getReactions("sandwich wizard").get(0).id, 3);
		Assert.assertEquals(r.getReactions("sandwich wizard").get(0).shortcode, ":sandwich:");
		Assert.assertEquals(r.getReactions("sandwich wizard").get(0).unicode, "🥪");
		Assert.assertEquals(r.getReactions("sandwich wizard").get(0).regex, "sandwich(es)?");
		Assert.assertEquals(r.getReactions("sandwich wizard").get(1).id, 4);
		Assert.assertEquals(r.getReactions("sandwich wizard").get(1).shortcode, ":man_mage:");
		Assert.assertEquals(r.getReactions("sandwich wizard").get(1).unicode, "🧙‍♂️");
		Assert.assertEquals(r.getReactions("sandwich wizard").get(1).regex, "(mages?|wizards?)");

		// Multiple Reactions with Single Match Opposite Order
		Assert.assertEquals(r.getReactions("wizard sandwich").size(), 2);
		Assert.assertEquals(r.getReactions("wizard sandwich").get(0).id, 4);
		Assert.assertEquals(r.getReactions("wizard sandwich").get(0).shortcode, ":man_mage:");
		Assert.assertEquals(r.getReactions("wizard sandwich").get(0).unicode, "🧙‍♂️");
		Assert.assertEquals(r.getReactions("wizard sandwich").get(0).regex, "(mages?|wizards?)");
		Assert.assertEquals(r.getReactions("wizard sandwich").get(1).id, 3);
		Assert.assertEquals(r.getReactions("wizard sandwich").get(1).shortcode, ":sandwich:");
		Assert.assertEquals(r.getReactions("wizard sandwich").get(1).unicode, "🥪");
		Assert.assertEquals(r.getReactions("wizard sandwich").get(1).regex, "sandwich(es)?");

		// Multiple Reactions with Multiple Matches
		Assert.assertEquals(r.getReactions("sandwich wizard sandwich").size(), 2);
		Assert.assertEquals(r.getReactions("sandwich wizard sandwich").get(0).id, 3);
		Assert.assertEquals(r.getReactions("sandwich wizard sandwich").get(0).shortcode, ":sandwich:");
		Assert.assertEquals(r.getReactions("sandwich wizard sandwich").get(0).unicode, "🥪");
		Assert.assertEquals(r.getReactions("sandwich wizard sandwich").get(0).regex, "sandwich(es)?");
		Assert.assertEquals(r.getReactions("sandwich wizard sandwich").get(1).id, 4);
		Assert.assertEquals(r.getReactions("sandwich wizard sandwich").get(1).shortcode, ":man_mage:");
		Assert.assertEquals(r.getReactions("sandwich wizard sandwich").get(1).unicode, "🧙‍♂️");
		Assert.assertEquals(r.getReactions("sandwich wizard sandwich").get(1).regex, "(mages?|wizards?)");

		// Reaction Order
		Assert.assertEquals(r.getReactions("sandwich top hat canada wizard lacrosse").size(), 5);
		Assert.assertEquals(r.getReactions("sandwich top hat canada wizard lacrosse").get(0).unicode, "🥪");
		Assert.assertEquals(r.getReactions("sandwich top hat canada wizard lacrosse").get(1).unicode, "🎩");
		Assert.assertEquals(r.getReactions("sandwich top hat canada wizard lacrosse").get(2).unicode, "🇨🇦");
		Assert.assertEquals(r.getReactions("sandwich top hat canada wizard lacrosse").get(3).unicode, "🧙‍♂️");
		Assert.assertEquals(r.getReactions("sandwich top hat canada wizard lacrosse").get(4).unicode, "🥍");
	}
}