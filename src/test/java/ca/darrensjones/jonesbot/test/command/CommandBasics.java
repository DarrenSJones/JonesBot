package ca.darrensjones.jonesbot.test.command;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import ca.darrensjones.jonesbot.command.utilities.Frinkiac;
import ca.darrensjones.jonesbot.handler.CommandHandler;
import ca.darrensjones.jonesbot.testcore.BotTest;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-12-14
 * @since 1.0.0 2020-11-24
 */
public class CommandBasics {

	private static final CommandHandler h = BotTest.get().commandHandler;

	@Test
	public void getCommandsList() {
		Assert.assertEquals(h.getCommands().size(), 12);
		Assert.assertEquals(h.getCommands().get(0).getName(), "CatFact");
		Assert.assertEquals(h.getCommands().get(1).getName(), "Cowbell");
		Assert.assertEquals(h.getCommands().get(2).getName(), "Futurama");
		Assert.assertEquals(h.getCommands().get(3).getName(), "Help");
		Assert.assertEquals(h.getCommands().get(4).getName(), "Owner");
		Assert.assertEquals(h.getCommands().get(5).getName(), "Ping");
		Assert.assertEquals(h.getCommands().get(6).getName(), "Reaction");
		Assert.assertEquals(h.getCommands().get(7).getName(), "Reload");
		Assert.assertEquals(h.getCommands().get(8).getName(), "Rick&Morty");
		Assert.assertEquals(h.getCommands().get(9).getName(), "Simpsons");
		Assert.assertEquals(h.getCommands().get(10).getName(), "Version");
		Assert.assertEquals(h.getCommands().get(11).getName(), "Weather");
	}

	@Test(dependsOnMethods = "getCommandsList", alwaysRun = true)
	public void basicsCatFact() {
		AbstractCommand c = h.getCommand("catfact");
		Assert.assertEquals(c.getName(), "CatFact");
		Assert.assertEquals(c.getDescription(), "Displays a random cat fact from https://catfact.ninja");
		Assert.assertEquals(c.getTriggers(), new String[] { "catfact", "catfacts" });
		Assert.assertEquals(c.getHelp(), "**!catfact** Displays a random cat fact from https://catfact.ninja");
		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}

	@Test(dependsOnMethods = "basicsCatFact", alwaysRun = true)
	public void basicsCowbell() {
		AbstractCommand c = h.getCommand("cowbell");
		Assert.assertEquals(c.getName(), "Cowbell");
		Assert.assertEquals(c.getDescription(), "When you need more of it");
		Assert.assertEquals(c.getTriggers(), new String[] { "cowbell" });
		Assert.assertEquals(c.getHelp(), "**!cowbell** When you need more of it");
		Assert.assertEquals(c.visibility(), CommandVisibility.HIDDEN);
	}

	@Test(dependsOnMethods = "basicsCatFact", alwaysRun = true)
	public void basicsFuturama() {
		AbstractCommand c = h.getCommand("futurama");
		Assert.assertEquals(c.getName(), "Futurama");
		Assert.assertEquals(c.getDescription(), "Returns an image from http://morbotron.com");
		Assert.assertEquals(c.getTriggers(), new String[] { "futurama", "f" });
		Assert.assertEquals(c.getHelp(), Frinkiac.getHelp("!"));
		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}

	@Test(dependsOnMethods = "basicsFuturama", alwaysRun = true)
	public void basicsHelp() {
		AbstractCommand c = h.getCommand("help");
		Assert.assertEquals(c.getName(), "Help");
		Assert.assertEquals(c.getDescription(), "The full list of Commands");
		Assert.assertEquals(c.getTriggers(), new String[] { "help", "h" });
		Assert.assertEquals(c.getHelp(), "**!help** The full list of Commands");
		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}

	@Test(dependsOnMethods = "basicsHelp", alwaysRun = true)
	public void basicsOwner() {
		AbstractCommand c = h.getCommand("owner");
		Assert.assertEquals(c.getName(), "Owner");
		Assert.assertEquals(c.getDescription(), "The full list of Owner-only Commands");
		Assert.assertEquals(c.getTriggers(), new String[] { "owner" });
		Assert.assertEquals(c.getHelp(), "**!owner** The full list of Owner-only Commands");
		Assert.assertEquals(c.visibility(), CommandVisibility.OWNER);
	}

	@Test(dependsOnMethods = "basicsOwner", alwaysRun = true)
	public void basicsPing() {
		AbstractCommand c = h.getCommand("ping");
		Assert.assertEquals(c.getName(), "Ping");
		Assert.assertEquals(c.getDescription(), "Pong!");
		Assert.assertEquals(c.getTriggers(), new String[] { "ping", "p" });
		Assert.assertEquals(c.getHelp(), "**!ping** Pong!");
		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}

	@Test(dependsOnMethods = "basicsPing", alwaysRun = true)
	public void basicsReaction() {
		AbstractCommand c = h.getCommand("reaction");
		Assert.assertEquals(c.getName(), "Reaction");
		Assert.assertEquals(c.getDescription(), "A list of reactions the Bot will respond with");
		Assert.assertEquals(c.getTriggers(), new String[] { "reaction", "reactions" });
		Assert.assertEquals(c.getHelp(), "**!reaction** A list of reactions the Bot will respond with");
		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}

	@Test(dependsOnMethods = "basicsReaction", alwaysRun = true)
	public void basicsReload() {
		AbstractCommand c = h.getCommand("reload");
		Assert.assertEquals(c.getName(), "Reload");
		Assert.assertEquals(c.getDescription(), "Reloads all lists from the SQL Database");
		Assert.assertEquals(c.getTriggers(), new String[] { "reload" });
		Assert.assertEquals(c.getHelp(), "**!reload** Reloads all lists from the SQL Database");
		Assert.assertEquals(c.visibility(), CommandVisibility.OWNER);
	}

	@Test(dependsOnMethods = "basicsCatFact", alwaysRun = true)
	public void basicsRickMorty() {
		AbstractCommand c = h.getCommand("rick&morty");
		Assert.assertEquals(c.getName(), "Rick&Morty");
		Assert.assertEquals(c.getDescription(), "Returns an image from http://masterofallscience.com");
		Assert.assertEquals(c.getTriggers(), new String[] { "rick&morty", "rick", "morty" });
		Assert.assertEquals(c.getHelp(), Frinkiac.getHelp("!"));
		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}

	@Test(dependsOnMethods = "basicsRickMorty", alwaysRun = true)
	public void basicsSimpsons() {
		AbstractCommand c = h.getCommand("simpsons");
		Assert.assertEquals(c.getName(), "Simpsons");
		Assert.assertEquals(c.getDescription(), "Returns an image from http://frinkiac.com");
		Assert.assertEquals(c.getTriggers(), new String[] { "simpsons", "s" });
		Assert.assertEquals(c.getHelp(), Frinkiac.getHelp("!"));
		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}

	@Test(dependsOnMethods = "basicsSimpsons", alwaysRun = true)
	public void basicsVersion() {
		AbstractCommand c = h.getCommand("version");
		Assert.assertEquals(c.getName(), "Version");
		Assert.assertEquals(c.getDescription(), "JonesBot Version Information");
		Assert.assertEquals(c.getTriggers(), new String[] { "version", "v" });
		Assert.assertEquals(c.getHelp(), "**!version** Displays the current version with changes.");
		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}

	@Test(dependsOnMethods = "basicsVersion", alwaysRun = true)
	public void basicsWeather() {
		AbstractCommand c = h.getCommand("weather");
		Assert.assertEquals(c.getName(), "Weather");
		Assert.assertEquals(c.getDescription(), "Gets the Weather from https://openweathermap.org/");
		Assert.assertEquals(c.getTriggers(), new String[] { "weather", "w" });
		Assert.assertEquals(c.getHelp(), "**!w** Gets the Weather from https://openweathermap.org/\n**!w {city}** Gets the Weather for the given city."
				+ "\n**!w !5day** Gets the 5-Day Forecast.\n**!w !5day {city}** Gets the 5-Day Forecast for the given city.");
		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}
}