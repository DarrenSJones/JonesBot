package ca.darrensjones.jonesbot.main;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.db.BotDB;
import ca.darrensjones.jonesbot.log.Reporter;
import ca.darrensjones.jonesbot.simpleschedule.SimpleSchedule;
import it.sauronsoftware.cron4j.Scheduler;

/**
 * @author Darren Jones
 * @version 1.2.0 2021-02-13
 * @since 1.0.0 2020-11-18
 */
public class Main {

	public static void main(String[] args) {
		Reporter.info("");
		Reporter.info("Bot initializating...");

		BotDB.init();
		Bot bot = new Bot();
		bot.setJDA(); // Connects to Discord using JDA
		Reporter.info(String.format("Bot Initialized! Logged into Discord as:[%s]", bot.jda.getSelfUser().toString()));

		String cron = "0 6 * * *";
		Scheduler scheduler = new Scheduler();
		scheduler.schedule(cron, new Thread(() -> SimpleSchedule.scheduleCron(bot)));
		scheduler.start();
		Reporter.info(String.format("Scheduler Initialized! Cron:[%s]", cron));
	}
}