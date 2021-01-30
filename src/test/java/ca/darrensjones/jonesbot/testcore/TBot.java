package ca.darrensjones.jonesbot.testcore;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.bot.Config;
import ca.darrensjones.jonesbot.db.BotDB;

/**
 * @author Darren Jones
 * @version 1.1.4 2021-01-29
 * @since 1.1.3 2021-01-14
 */
public class TBot {

	private static Bot bot;

	private static void createBot() {
		BotDB.set("localhost:1433", "jonesbottest", "jonesbot", "jonesbot");
		bot = new Bot(); // Doesn't connect to Discord using JDA

		bot.getConfig().BOT_GITHUB = "http://localhost:1080";
		bot.getConfig().HOST_CATFACT = "http://localhost:1080";
		bot.getConfig().HOST_SIMPSONS = "http://localhost:1080";
		bot.getConfig().HOST_FUTURAMA = "http://localhost:1080";
		bot.getConfig().HOST_RICKMORTY = "http://localhost:1080";
		bot.getConfig().HOST_WEATHER = "http://localhost:1080";
	}

	public static Bot getBot() {
		if (bot == null) createBot();
		return bot;
	}

	public static Config getConfig() {
		if (bot == null) createBot();
		return bot.getConfig();
	}

	public static String getPrefix() {
		if (bot == null) createBot();
		return bot.getPrefix();
	}
}