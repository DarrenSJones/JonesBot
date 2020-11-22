package ca.darrensjones.jonesbot.bot;

import javax.security.auth.login.LoginException;

import ca.darrensjones.jonesbot.db.controller.CConfig;
import ca.darrensjones.jonesbot.handler.CommandHandler;
import ca.darrensjones.jonesbot.handler.ReactionHandler;
import ca.darrensjones.jonesbot.log.Reporter;
import ca.darrensjones.jonesbot.main.EventListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-22
 * @since 1.0.0 2020-11-18
 */
public class Bot {

	public final BotConfig config;
	public JDA jda;
	public CommandHandler commandHandler;
	public ReactionHandler reactionHandler;

	public Bot() {
		this(false);
	}

	public Bot(boolean test) {
		this.config = CConfig.getConfig();
		this.commandHandler = new CommandHandler(this);
		this.reactionHandler = new ReactionHandler();
		if (!test) resetJDA();
	}

	public void resetJDA() {
		try {
			JDABuilder builder = JDABuilder.createDefault(config.BOT_TOKEN).addEventListeners(new EventListener(this));
			jda = builder.build();
			jda.awaitReady();
			Reporter.info("JDA Reset and Ready.");
		} catch (InterruptedException | LoginException e) {
			Reporter.fatal(e.getMessage());
		}
	}
}