package ca.darrensjones.jonesbot.test.command;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import ca.darrensjones.jonesbot.handler.CommandHandler;
import ca.darrensjones.jonesbot.testcore.BotTest;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-25
 * @since 1.0.0 2020-11-24
 */
public class CommandBasics {

	private static final CommandHandler h = BotTest.get().commandHandler;

	@Test
	public void getCommandsList() {
		Assert.assertEquals(h.getCommands().size(), 8);
		Assert.assertEquals(h.getCommands().get(0).getName(), "CatFact");
		Assert.assertEquals(h.getCommands().get(1).getName(), "Cowbell");
		Assert.assertEquals(h.getCommands().get(2).getName(), "Help");
		Assert.assertEquals(h.getCommands().get(3).getName(), "Owner");
		Assert.assertEquals(h.getCommands().get(4).getName(), "Ping");
		Assert.assertEquals(h.getCommands().get(5).getName(), "Reaction");
		Assert.assertEquals(h.getCommands().get(6).getName(), "Reload");
		Assert.assertEquals(h.getCommands().get(7).getName(), "Weather");
	}

	@Test(dependsOnMethods = "getCommandsList", alwaysRun = true)
	public void basicsCatFact() {
		AbstractCommand c = h.getCommand("catfact");
		Assert.assertEquals(c.getName(), "CatFact");
		Assert.assertEquals(c.getDescription(), "Displays a random cat fact from https://catfact.ninja");
		Assert.assertEquals(c.getTriggers(), new String[] { "catfact", "catfacts" });
		Assert.assertEquals(c.getHelp(), "**%scatfact** Displays a random cat fact from https://catfact.ninja");
		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}

	@Test(dependsOnMethods = "basicsCatFact", alwaysRun = true)
	public void basicsCowbell() {
		AbstractCommand c = h.getCommand("cowbell");
		Assert.assertEquals(c.getName(), "Cowbell");
		Assert.assertEquals(c.getDescription(), "When you need more of it");
		Assert.assertEquals(c.getTriggers(), new String[] { "cowbell" });
		Assert.assertEquals(c.getHelp(), "**%scowbell** When you need more of it");
		Assert.assertEquals(c.visibility(), CommandVisibility.HIDDEN);
	}

	@Test(dependsOnMethods = "basicsCowbell", alwaysRun = true)
	public void basicsHelp() {
		AbstractCommand c = h.getCommand("help");
		Assert.assertEquals(c.getName(), "Help");
		Assert.assertEquals(c.getDescription(), "The full list of Commands");
		Assert.assertEquals(c.getTriggers(), new String[] { "help", "h" });
		Assert.assertEquals(c.getHelp(), "**%shelp** The full list of Commands");
		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}

	@Test(dependsOnMethods = "basicsHelp", alwaysRun = true)
	public void basicsOwner() {
		AbstractCommand c = h.getCommand("owner");
		Assert.assertEquals(c.getDescription(), "The full list of Owner-only Commands");
		Assert.assertEquals(c.getTriggers(), new String[] { "owner" });
		Assert.assertEquals(c.getHelp(), "**%sowner** The full list of Owner-only Commands");
		Assert.assertEquals(c.visibility(), CommandVisibility.OWNER);
	}

	@Test(dependsOnMethods = "basicsOwner", alwaysRun = true)
	public void basicsPing() {
		AbstractCommand c = h.getCommand("ping");
		Assert.assertEquals(c.getDescription(), "Pong!");
		Assert.assertEquals(c.getTriggers(), new String[] { "ping", "p" });
		Assert.assertEquals(c.getHelp(), "**%sping** Pong!");
		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}

	@Test(dependsOnMethods = "basicsPing", alwaysRun = true)
	public void basicsReaction() {
		AbstractCommand c = h.getCommand("reaction");
		Assert.assertEquals(c.getDescription(), "A list of reactions the Bot will respond with");
		Assert.assertEquals(c.getTriggers(), new String[] { "reaction", "reactions" });
		Assert.assertEquals(c.getHelp(), "**%sreaction** A list of reactions the Bot will respond with");
		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}

	@Test(dependsOnMethods = "basicsHelp", alwaysRun = true)
	public void basicsReload() {
		AbstractCommand c = h.getCommand("reload");
		Assert.assertEquals(c.getDescription(), "Reloads all lists from the SQL Database");
		Assert.assertEquals(c.getTriggers(), new String[] { "reload" });
		Assert.assertEquals(c.getHelp(), "**%sreload** Reloads all lists from the SQL Database");
		Assert.assertEquals(c.visibility(), CommandVisibility.OWNER);
	}

	@Test(dependsOnMethods = "basicsHelp", alwaysRun = true)
	public void basicsWeather() {
		AbstractCommand c = h.getCommand("weather");
		Assert.assertEquals(c.getDescription(), "Gets the Weather from http://https://openweathermap.org/");
		Assert.assertEquals(c.getTriggers(), new String[] { "weather", "w" });
		Assert.assertEquals(c.getHelp(), "**%sweather** Gets the Weather from http://https://openweathermap.org/");
		Assert.assertEquals(c.visibility(), CommandVisibility.PUBLIC);
	}
}