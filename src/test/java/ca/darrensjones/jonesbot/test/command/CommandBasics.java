package ca.darrensjones.jonesbot.test.command;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import ca.darrensjones.jonesbot.command.utilities.Frinkiac;
import ca.darrensjones.jonesbot.handler.CommandHandler;
import ca.darrensjones.jonesbot.testcore.TBot;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-23
 * @since   1.0.0 2020-11-24
 */
public class CommandBasics {

	private static final CommandHandler h = TBot.getBot().commandHandler;

	@Test
	public void commands() {
		Assert.assertEquals(h.commands.size(), 17);
		Assert.assertNotNull(h.getCommand("!CatFact"));
		Assert.assertNotNull(h.getCommand("!ChangeLog"));
		Assert.assertNotNull(h.getCommand("!Cowbell"));
		Assert.assertNotNull(h.getCommand("!Futurama"));
		Assert.assertNotNull(h.getCommand("!Help"));
		Assert.assertNotNull(h.getCommand("!Owner"));
		Assert.assertNotNull(h.getCommand("!Ping"));
		Assert.assertNotNull(h.getCommand("!Reaction"));
		Assert.assertNotNull(h.getCommand("!Reload"));
		Assert.assertNotNull(h.getCommand("!Rick&Morty"));
		Assert.assertNotNull(h.getCommand("!Roll"));
		Assert.assertNotNull(h.getCommand("!SimpleSchedule"));
		Assert.assertNotNull(h.getCommand("!Simpsons"));
		Assert.assertNotNull(h.getCommand("!ToDo"));
		Assert.assertNotNull(h.getCommand("!Version"));
		Assert.assertNotNull(h.getCommand("!Weather"));
	}

	@Test(dependsOnMethods = "commands", alwaysRun = true)
	public void validateTriggers() {
		List<String> triggers = new ArrayList<String>();
		for (AbstractCommand command : h.commands) {
			for (String trigger : command.getTriggers()) {
				Assert.assertFalse(triggers.contains(trigger)); // Fails if duplicate command
																// triggers exist
				triggers.add(trigger);
			}
		}
	}

	@Test(dependsOnMethods = "validateTriggers", alwaysRun = true)
	public void basicsCatFact() {
		AbstractCommand c = h.getCommand("!catfact");
		Assert.assertEquals(c.getName(), "CatFact");
		Assert.assertEquals(c.getDescription(),
				"Displays a random cat fact from https://catfact.ninja");
		Assert.assertEquals(c.getTriggers(), new String[] { "catfact", "catfacts" });
		Assert.assertEquals(c.getHelp(),
				"**!catfact** Displays a random cat fact from https://catfact.ninja");
		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}

	@Test(dependsOnMethods = "basicsCatFact", alwaysRun = true)
	public void basicsChangelog() {
		AbstractCommand c = h.getCommand("!changelog");
		Assert.assertEquals(c.getName(), "Change Log");
		Assert.assertEquals(c.getDescription(), "JonesBot Change Log Information");
		Assert.assertEquals(c.getTriggers(), new String[] { "changelog" });
		Assert.assertEquals(c.getHelp(), "**!changelog** Displays change log link list.");
		Assert.assertEquals(c.visibility(), CommandVisibility.HIDDEN);
	}

	@Test(dependsOnMethods = "basicsChangelog", alwaysRun = true)
	public void basicsCowbell() {
		AbstractCommand c = h.getCommand("!cowbell");
		Assert.assertEquals(c.getName(), "Cowbell");
		Assert.assertEquals(c.getDescription(), "When you need more of it");
		Assert.assertEquals(c.getTriggers(), new String[] { "cowbell" });
		Assert.assertEquals(c.getHelp(), "**!cowbell** When you need more of it");
		Assert.assertEquals(c.visibility(), CommandVisibility.HIDDEN);
	}

	@Test(dependsOnMethods = "basicsCatFact", alwaysRun = true)
	public void basicsFuturama() {
		AbstractCommand c = h.getCommand("!futurama");
		Assert.assertEquals(c.getName(), "Futurama");
		Assert.assertEquals(c.getDescription(), "Returns an image from http://morbotron.com");
		Assert.assertEquals(c.getTriggers(), new String[] { "futurama", "f" });
		Assert.assertEquals(c.getHelp(), Frinkiac.getHelp("!"));
		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}

	@Test(dependsOnMethods = "basicsFuturama", alwaysRun = true)
	public void basicsGame() {
		AbstractCommand c = h.getCommand("!game");
		Assert.assertEquals(c.getName(), "Game");
		Assert.assertEquals(c.getDescription(), "Starts a new game!");
		Assert.assertEquals(c.getTriggers(), new String[] { "game" });
		Assert.assertEquals(c.getHelp(), "**!game** Starts a new game!");
		Assert.assertEquals(c.visibility(), CommandVisibility.HIDDEN);
	}

	@Test(dependsOnMethods = "basicsGame", alwaysRun = true)
	public void basicsHelp() {
		AbstractCommand c = h.getCommand("!help");
		Assert.assertEquals(c.getName(), "Help");
		Assert.assertEquals(c.getDescription(), "The full list of Commands");
		Assert.assertEquals(c.getTriggers(), new String[] { "help", "h" });
		Assert.assertEquals(c.getHelp(), "**!help** The full list of Commands");
		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}

	@Test(dependsOnMethods = "basicsHelp", alwaysRun = true)
	public void basicsOwner() {
		AbstractCommand c = h.getCommand("!owner");
		Assert.assertEquals(c.getName(), "Owner");
		Assert.assertEquals(c.getDescription(), "The full list of Owner-only Commands");
		Assert.assertEquals(c.getTriggers(), new String[] { "owner" });
		Assert.assertEquals(c.getHelp(), "**!owner** The full list of Owner-only Commands");
		Assert.assertEquals(c.visibility(), CommandVisibility.OWNER);
	}

	@Test(dependsOnMethods = "basicsOwner", alwaysRun = true)
	public void basicsPing() {
		AbstractCommand c = h.getCommand("!ping");
		Assert.assertEquals(c.getName(), "Ping");
		Assert.assertEquals(c.getDescription(), "Pong!");
		Assert.assertEquals(c.getTriggers(), new String[] { "ping", "p" });
		Assert.assertEquals(c.getHelp(), "**!ping** Pong!");
		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}

	@Test(dependsOnMethods = "basicsPing", alwaysRun = true)
	public void basicsReaction() {
		AbstractCommand c = h.getCommand("!reaction");
		Assert.assertEquals(c.getName(), "Reaction");
		Assert.assertEquals(c.getDescription(), "A list of reactions the Bot will respond with");
		Assert.assertEquals(c.getTriggers(), new String[] { "reaction", "reactions" });
		Assert.assertEquals(c.getHelp(),
				"**!reaction** A list of reactions the Bot will respond with");
		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}

	@Test(dependsOnMethods = "basicsReaction", alwaysRun = true)
	public void basicsReload() {
		AbstractCommand c = h.getCommand("!reload");
		Assert.assertEquals(c.getName(), "Reload");
		Assert.assertEquals(c.getDescription(),
				"Reloads all Commands and data from the SQL Database");
		Assert.assertEquals(c.getTriggers(), new String[] { "reload" });
		Assert.assertEquals(c.getHelp(),
				"**!reload** Reloads all Commands and data from the SQL Database");
		Assert.assertEquals(c.visibility(), CommandVisibility.OWNER);
	}

	@Test(dependsOnMethods = "basicsCatFact", alwaysRun = true)
	public void basicsRickMorty() {
		AbstractCommand c = h.getCommand("!rick&morty");
		Assert.assertEquals(c.getName(), "Rick&Morty");
		Assert.assertEquals(c.getDescription(),
				"Returns an image from http://masterofallscience.com");
		Assert.assertEquals(c.getTriggers(), new String[] { "rick&morty", "rick", "morty" });
		Assert.assertEquals(c.getHelp(), Frinkiac.getHelp("!"));
		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}

	@Test(dependsOnMethods = "basicsRickMorty", alwaysRun = true)
	public void basicsRoll() {
		AbstractCommand c = h.getCommand("!roll");
		Assert.assertEquals(c.getName(), "Roll");
		Assert.assertEquals(c.getDescription(), "Roll some dice!");
		Assert.assertEquals(c.getTriggers(), new String[] { "roll", "r" });
		Assert.assertEquals(c.getHelp(),
				"**!roll** Rolls a 6-sided die.\n**!roll {sides}** Rolls a die with the given number of sides.\n"
						+ "**!roll {amount}d{sides}** Rolls the die the given amount (eg. 2d6).");
		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}

	@Test(dependsOnMethods = "basicsRoll", alwaysRun = true)
	public void basicsSimpleSchedule() {
		AbstractCommand c = h.getCommand("!simpleschedule");
		Assert.assertEquals(c.getName(), "Simple Schedule");
		Assert.assertEquals(c.getDescription(), "A simple schedule for JonesBot");
		Assert.assertEquals(c.getTriggers(), new String[] { "simpleschedule", "schedule" });
		Assert.assertEquals(c.getHelp(), "**!schedule** Displays today's schedule.");
		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}

	@Test(dependsOnMethods = "basicsSimpleSchedule", alwaysRun = true)
	public void basicsSimpsons() {
		AbstractCommand c = h.getCommand("!simpsons");
		Assert.assertEquals(c.getName(), "Simpsons");
		Assert.assertEquals(c.getDescription(), "Returns an image from http://frinkiac.com");
		Assert.assertEquals(c.getTriggers(), new String[] { "simpsons", "s" });
		Assert.assertEquals(c.getHelp(), Frinkiac.getHelp("!"));
		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}

	@Test(dependsOnMethods = "basicsSimpsons", alwaysRun = true)
	public void basicsToDo() {
		AbstractCommand c = h.getCommand("!todo");
		Assert.assertEquals(c.getName(), "To-Do");
		Assert.assertEquals(c.getDescription(), "JonesBot to-do list");
		Assert.assertEquals(c.getTriggers(), new String[] { "todo", "to-do" });
		Assert.assertEquals(c.getHelp(), "**!todo** Displays the current to-do list.");
		Assert.assertEquals(c.visibility(), CommandVisibility.HIDDEN);
	}

	@Test(dependsOnMethods = "basicsToDo", alwaysRun = true)
	public void basicsVersion() {
		AbstractCommand c = h.getCommand("!version");
		Assert.assertEquals(c.getName(), "Version");
		Assert.assertEquals(c.getDescription(), "JonesBot version information");
		Assert.assertEquals(c.getTriggers(), new String[] { "version", "v" });
		Assert.assertEquals(c.getHelp(), "**!version** Displays current version information.");
		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}

	@Test(dependsOnMethods = "basicsVersion", alwaysRun = true)
	public void basicsWeather() {
		AbstractCommand c = h.getCommand("!weather");
		Assert.assertEquals(c.getName(), "Weather");
		Assert.assertEquals(c.getDescription(),
				"Gets the Weather from https://openweathermap.org/");
		Assert.assertEquals(c.getTriggers(), new String[] { "weather", "w" });
		Assert.assertEquals(c.getHelp(),
				"**!w** Gets the Weather from https://openweathermap.org/\n**!w {city}** Gets the Weather for the given city."
						+ "\n**!w !5day** Gets the 5-Day Forecast.\n**!w !5day {city}** Gets the 5-Day Forecast for the given city.");
		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}
}