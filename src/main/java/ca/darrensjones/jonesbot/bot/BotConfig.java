package ca.darrensjones.jonesbot.bot;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-18
 * @since 1.0.0 2020-11-18
 */
public class BotConfig {

	public final String BOT_TOKEN;
	public final String BOT_OWNER_ID;
	public final String BOT_PREFIX;
	public final String PATH_RESOURCES;
	public final String PATH_FRINKIAC;
	public final String PATH_SCHEDULE;
	public final String TOKEN_OPENWEATHERMAP;

	public BotConfig(String botToken, String botOwnerId, String prefix, String pathResources, String pathFrinkiac, String pathSchedule, String tokenWeather) {
		this.BOT_TOKEN = botToken;
		this.BOT_OWNER_ID = botOwnerId;
		this.BOT_PREFIX = prefix;
		this.PATH_RESOURCES = pathResources;
		this.PATH_FRINKIAC = pathFrinkiac;
		this.PATH_SCHEDULE = pathSchedule;
		this.TOKEN_OPENWEATHERMAP = tokenWeather;
	}
}
