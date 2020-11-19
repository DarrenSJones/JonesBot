package ca.darrensjones.jonesbot.main;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.db.BotDB;
import ca.darrensjones.jonesbot.log.Reporter;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-18
 * @since 1.0.0 2020-11-18
 */
public class Main {

	private static Bot bot;

	public static void main(String[] args) {
		Reporter.info("Bot initializating...");

		BotDB.init();
		bot = new Bot();

		Reporter.info(String.format("Bot Initialized! Logged in as: [%s]", bot.jda.getSelfUser().toString()));
	}
}