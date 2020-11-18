package ca.darrensjones.jonesbot.bot;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-18
 * @since 1.0.0 2020-11-18
 */
public class BotConfig {

	public final String BOT_VERSION;
	public final String BOT_TOKEN;
	public final String BOT_OWNER_ID;
	public final String BOT_PREFIX;
	public final String TOKEN_OPENWEATHERMAP;

	public BotConfig(String botVersion, String botToken, String botOwnerId, String prefix, String tokenOpenWeatherMap) {
		this.BOT_VERSION = botVersion;
		this.BOT_TOKEN = botToken;
		this.BOT_OWNER_ID = botOwnerId;
		this.BOT_PREFIX = prefix;
		this.TOKEN_OPENWEATHERMAP = tokenOpenWeatherMap;
	}
}