package ca.darrensjones.jonesbot.bot;

/**
 * @author Darren Jones
 * @version 1.1.3 2021-01-14
 * @since 1.1.3 2021-01-14
 */
public class Config {

	public final String VERSION = "1.1.3a";
	public final String PREFIX = "!";

	// Default values for testing, these will be updated from the DB.
	public String BOT_TOKEN = "discord-bot-token-here";
	public String BOT_OWNER_ID = "discord-bot-owner-id-here";
	public String BOT_GITHUB = "github-repo-path-here";

	// These are set to "http://localhost:1080" for testing.
	public String HOST_CATFACT = "https://catfact.ninja";
	public String HOST_SIMPSONS = "https://frinkiac.com";
	public String HOST_FUTURAMA = "https://morbotron.com";
	public String HOST_RICKMORTY = "https://masterofallscience.com";
	public String HOST_WEATHER = "http://api.openweathermap.org";

	public String WEATHER_TOKEN = "openweathermap-token-here";
	public String WEATHER_DEFAULT = "default-city-here";

	public Config() {

	}
}