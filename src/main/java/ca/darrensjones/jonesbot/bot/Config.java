package ca.darrensjones.jonesbot.bot;

import java.util.ArrayList;
import java.util.List;

import ca.darrensjones.jonesbot.db.controller.CConfig;
import ca.darrensjones.jonesbot.db.controller.CVersion;
import ca.darrensjones.jonesbot.db.model.OVersion;

/**
 * @author Darren Jones
 * @version 1.1.4 2021-01-22
 * @since 1.1.3 2021-01-14
 */
public class Config {

	public final String VERSION = "1.1.4";

	// Default values for testing, these will be updated from the DB.
	public String PREFIX = "!";
	public String BOT_TOKEN = "discord-bot-token-here";
	public String BOT_OWNER_ID = "discord-bot-owner-id-here";
	public String BOT_GITHUB = "github-repo-path-here";

	// Gets the version list from the change log
	public List<OVersion> VERSION_LIST = new ArrayList<OVersion>();

	// These are set to "http://localhost:1080" for testing.
	public String HOST_CATFACT = "https://catfact.ninja";
	public String HOST_SIMPSONS = "https://frinkiac.com";
	public String HOST_FUTURAMA = "https://morbotron.com";
	public String HOST_RICKMORTY = "https://masterofallscience.com";
	public String HOST_WEATHER = "http://api.openweathermap.org";

	public String WEATHER_TOKEN = "openweathermap-token-here";
	public String WEATHER_DEFAULT = "Regina,Saskatchewan,CA";

	public Config() {
		String[] values = CConfig.getConfigValues();
		this.PREFIX = values[0];
		this.BOT_TOKEN = values[1];
		this.BOT_OWNER_ID = values[2];
		this.BOT_GITHUB = values[3];
		this.WEATHER_TOKEN = values[4];

		this.VERSION_LIST = CVersion.getVersionList();
	}

	public String getPrefix() {
		return PREFIX;
	}
}