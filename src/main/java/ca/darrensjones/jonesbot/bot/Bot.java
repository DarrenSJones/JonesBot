package ca.darrensjones.jonesbot.bot;

import javax.security.auth.login.LoginException;

import ca.darrensjones.jonesbot.db.controller.CConfig;
import ca.darrensjones.jonesbot.handler.CommandHandler;
import ca.darrensjones.jonesbot.handler.DataHandler;
import ca.darrensjones.jonesbot.handler.ReactionHandler;
import ca.darrensjones.jonesbot.handler.AutoResponseHandler;
import ca.darrensjones.jonesbot.log.Reporter;
import ca.darrensjones.jonesbot.main.EventListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

/**
 * @author Darren Jones
 * @version 1.1.1 2020-12-29
 * @since 1.0.0 2020-11-18
 */
public class Bot {

	public JDA jda;
	public final BotConfig config;
	public final AutoResponseHandler autoResponseHandler;
	public final CommandHandler commandHandler;
	public final DataHandler dataHandler;
	public final ReactionHandler reactionHandler;

	public Bot() {
		this(false);
	}

	public Bot(boolean test) {
		this.config = CConfig.getConfig();
		this.autoResponseHandler = new AutoResponseHandler(this);
		this.commandHandler = new CommandHandler(this);
		this.dataHandler = new DataHandler();
		this.reactionHandler = new ReactionHandler(this);
		if (!test) setJDA();
	}

	public void setJDA() {
		try {
			JDABuilder builder = JDABuilder.createDefault(config.BOT_TOKEN).addEventListeners(new EventListener(this));
			jda = builder.build();
			jda.awaitReady();
			Reporter.info("JDA set and ready.");
		} catch (InterruptedException | LoginException e) {
			Reporter.fatal("Failed to set JDA.\n" + e.getMessage());
		}
	}
}