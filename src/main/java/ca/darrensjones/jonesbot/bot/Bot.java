package ca.darrensjones.jonesbot.bot;

import javax.security.auth.login.LoginException;

import ca.darrensjones.jonesbot.db.controller.CConfig;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-18
 * @since 1.0.0 2020-11-18
 */
public class Bot {

	public final BotConfig config;
	public JDA jda;

	public Bot() {
		this.config = CConfig.getConfig();
		resetJDA();
	}

	public void resetJDA() {
		try {
			JDABuilder builder = JDABuilder.createDefault(config.BOT_TOKEN);// .addEventListeners(new EventListener(this));
			jda = builder.build();
			jda.awaitReady();
			System.out.println("JDA Reset and Ready.");
		} catch (InterruptedException | LoginException e) {
			System.out.println(e.getMessage());
		}
	}
}