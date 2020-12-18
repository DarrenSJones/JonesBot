package ca.darrensjones.jonesbot.main;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.db.BotDB;
import ca.darrensjones.jonesbot.log.Reporter;

/**
 * @author Darren Jones
 * @version 1.0.1 2020-12-18
 * @since 1.0.0 2020-11-18
 */
public class Main {

	public static void main(String[] args) {
		Reporter.info("");
		Reporter.info("Bot initializating...");

		BotDB.init("localhost:1433", "jonesbot", "jonesbot", "jonesbot");
		Bot bot = new Bot();

		Reporter.info(String.format("Bot Initialized! Logged in as:[%s]", bot.jda.getSelfUser().toString()));
	}
}