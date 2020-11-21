package ca.darrensjones.jonesbot.test;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.db.BotDB;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-21
 * @since 1.0.0 2020-11-21
 */
public class BotTest {

	private static Bot bot;

	public static void createBot() {
		BotDB.test();
		BotDB.init();
		bot = new Bot(true);
	}

	public static Bot get() {
		if (bot == null) createBot();
		return bot;
	}
}