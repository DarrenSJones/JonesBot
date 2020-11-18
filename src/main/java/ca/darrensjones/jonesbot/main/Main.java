package ca.darrensjones.jonesbot.main;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.db.BotDB;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-18
 * @since 1.0.0 2020-11-18
 */
public class Main {

	private static Bot bot;

	public static void main(String[] args) {
		BotDB.init();

		bot = new Bot();

		System.out.println(String.format("Bot Initialized! Logged in as: [%s]", bot.jda.getSelfUser().toString()));
	}
}
