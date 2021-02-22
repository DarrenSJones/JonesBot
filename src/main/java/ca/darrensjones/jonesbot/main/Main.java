package ca.darrensjones.jonesbot.main;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.db.BotDB;
import ca.darrensjones.jonesbot.log.Reporter;
import ca.darrensjones.jonesbot.simpleschedule.SimpleSchedule;
import it.sauronsoftware.cron4j.Scheduler;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-22
 * @since   1.0.0 2020-11-18
 */
public class Main {

	public static void main(String[] args) {
		Reporter.info("");
		Reporter.info("Bot initializing...");

		// SQL Database Connection
		BotDB.init();

		// Set Bot
		Bot bot = new Bot();

		// Discord Connection using JDA
		bot.setJDA();

		// Set Schedule
		schedule(bot, "0 6 * * *");

		Reporter.info("Bot initialized!");
	}

	private static void schedule(Bot bot, String cron) {
		Scheduler scheduler = new Scheduler();
		scheduler.schedule(cron, new Thread(() -> SimpleSchedule.scheduleCron(bot)));
		scheduler.start();
		Reporter.info(String.format("Scheduler set, cron:[%s]", cron));
	}
}