package ca.darrensjones.jonesbot.bot;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-27
 * @since 1.0.0 2020-11-18
 */
public class BotConfig {

	public final String BOT_VERSION;
	public final String BOT_TOKEN;
	public final String BOT_OWNER_ID;
	public final String BOT_PREFIX;
	public final String CATFACT_HOST;
	public final String TOKEN_OPENWEATHERMAP;

	public BotConfig(String botVersion, String botToken, String botOwnerId, String prefix, String catFactHost, String weatherToken) {
		this.BOT_VERSION = botVersion;
		this.BOT_TOKEN = botToken;
		this.BOT_OWNER_ID = botOwnerId;
		this.BOT_PREFIX = prefix;
		this.CATFACT_HOST = catFactHost;
		this.TOKEN_OPENWEATHERMAP = weatherToken;
	}
}
/*-
Schedule
Command: Frinkiac
Command: MoaS
Command: Morbotron
Command: Schedule
Command: Weather
*/