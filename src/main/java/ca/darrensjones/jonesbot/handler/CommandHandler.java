package ca.darrensjones.jonesbot.handler;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.log.LogUtils;
import ca.darrensjones.jonesbot.log.Reporter;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-22
 * @since 1.0.0 2020-11-22
 */
public class CommandHandler {

	private final Bot bot;

	public CommandHandler(Bot bot) {
		this.bot = bot;
	}

	public void process(Message message) {
		Reporter.info("Start CommandHandler. " + LogUtils.getMessageInfo(message));
		Reporter.info("End CommandHandler.");
	}
}