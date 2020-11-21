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
public class HasReaction {

	private static final ReactionHandler r = BotTest.get().reactionHandler;

	@Test
	public void validateCasing() {
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
	}

	@Test(dependsOnMethods = "validateCasing", alwaysRun = true)
	public void validateSpacing() {
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
	}

	@Test(dependsOnMethods = "validateSpacing", alwaysRun = true)
	public void validateSpecialCharacters() {
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
	}

	@Test(dependsOnMethods = "validateSpecialCharacters", alwaysRun = true)
	public void validateNegativeTests() {
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
	}

	@Test(dependsOnMethods = "validateNegativeTests", alwaysRun = true)
	public void validateSpecialCases() {
		Assert.assertTrue(r.hasReaction("top hat"));
		Assert.assertFalse(r.hasReaction("top  hat"));
		Assert.assertFalse(r.hasReaction("top x hat"));
	}
}
