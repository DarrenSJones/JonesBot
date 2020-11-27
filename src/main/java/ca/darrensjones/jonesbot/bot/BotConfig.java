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
	public final String WEATHER_TOKEN;
	public final String WEATHER_HOST;
	public final String WEATHER_DEFAULT_CITY;

	public BotConfig(String botVersion, String botToken, String botOwnerId, String prefix, String catFactHost, String weatherToken, String weatherHost,
			String weatherDefaultCity) {
		this.BOT_VERSION = botVersion;
		this.BOT_TOKEN = botToken;
		this.BOT_OWNER_ID = botOwnerId;
		this.BOT_PREFIX = prefix;
		this.CATFACT_HOST = catFactHost;
		this.WEATHER_TOKEN = weatherToken;
		this.WEATHER_HOST = weatherHost;
		this.WEATHER_DEFAULT_CITY = weatherDefaultCity;
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