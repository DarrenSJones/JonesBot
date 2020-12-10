package ca.darrensjones.jonesbot.command;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import ca.darrensjones.jonesbot.log.Reporter;
import ca.darrensjones.jonesbot.utilities.MyDateUtils;
import ca.darrensjones.jonesbot.utilities.RequestUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-12-10
 * @since 1.0.0 2020-11-26
 */
public class CommandWeather extends AbstractCommand {

	public CommandWeather(Bot bot) {
		super(bot);
	}

	@Override
	public String getName() {
		return "Weather";
	}

	@Override
	public String getDescription() {
		return "Gets the Weather from https://openweathermap.org/";
	}

	@Override
	public String[] getTriggers() {
		return new String[] { "weather", "w" };
	}

	@Override
	public CommandVisibility visibility() {
		return CommandVisibility.PUBLIC;
	}

	@Override
	public String getHelp() {
		String p = bot.config.BOT_PREFIX;
		String output = "**" + p + "w** " + getDescription();
		output += "\n**" + p + "w {city}** Gets the Weather for the given city.";
		output += "\n**" + p + "w " + p + "5day** Gets the 5-Day Forecast.";
		output += "\n**" + p + "w " + p + "5day {city}** Gets the 5-Day Forecast for the given city.";
		return output;
	}

	@Override
	public void execute(Message message) {
		message.getChannel().sendMessage(process(bot, message.getContentDisplay()).build()).queue();
	}

	public static EmbedBuilder process(Bot bot, String content) {
		EmbedBuilder eb = new EmbedBuilder();

		boolean is5Day = Pattern.compile(bot.config.BOT_PREFIX + "5day\\s?").matcher(content.toLowerCase()).find();

		String forecastType = "weather";
		if (is5Day) forecastType = "forecast";
		String city = content.replaceAll(bot.config.BOT_PREFIX + "\\w+(\\s+)?", "").trim().replaceAll("\\s", "%20");
		if (StringUtils.isBlank(city)) city = bot.config.WEATHER_DEFAULT_CITY;
		String request = String.format("%s/data/2.5/%s?units=metric&appid=%s&q=%s", bot.config.WEATHER_HOST, forecastType, bot.config.WEATHER_TOKEN, city);

		try {
			String response = RequestUtils.getResponseBody(request);

			if (is5Day) eb = buildEmbed5Day(response);
			else eb = buildEmbedCurrent(response);
		} catch (Exception e) {
			Reporter.fatal(e.getMessage());
		}

		return eb;
	}

	public static EmbedBuilder buildEmbedCurrent(String responseBody) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("Current Weather");
		try {
			JSONObject json = (JSONObject) new JSONParser().parse(responseBody);
			String desc = ((JSONObject) ((JSONArray) json.get("weather")).get(0)).get("main").toString();
			String icon = "http://openweathermap.org/img/w/" + ((JSONObject) ((JSONArray) json.get("weather")).get(0)).get("icon").toString() + ".png";
			long date = Long.parseLong(json.get("dt").toString() + "000");
			String temp = Integer.toString(Math.round(Float.parseFloat(((JSONObject) json.get("main")).get("temp").toString())));
			String tempFeel = Integer.toString(Math.round(Float.parseFloat(((JSONObject) json.get("main")).get("feels_like").toString())));
			String tempMax = Integer.toString(Math.round(Float.parseFloat(((JSONObject) json.get("main")).get("temp_max").toString())));
			String tempMin = Integer.toString(Math.round(Float.parseFloat(((JSONObject) json.get("main")).get("temp_min").toString())));
			String windSpeed = Integer.toString(Math.round(Float.parseFloat(((JSONObject) json.get("wind")).get("speed").toString())));
			String windDirection = windDirection(Float.parseFloat(((JSONObject) json.get("wind")).get("deg").toString()));
			String city = json.get("name").toString();
			String sunrise = MyDateUtils.longStringToZDT(((JSONObject) json.get("sys")).get("sunrise").toString() + "000").toLocalTime().toString();
			String sunset = MyDateUtils.longStringToZDT(((JSONObject) json.get("sys")).get("sunset").toString() + "000").toLocalTime().toString();

			eb.setDescription(city);
			eb.setThumbnail(icon);
			eb.addField(desc, String.format("%s°C, Feels Like %s°C", temp, tempFeel), true);
			eb.addField(String.format("Wind: %s kph %s", windSpeed, windDirection), String.format("High: %s°C Low: %s°C", tempMax, tempMin), true);
			eb.addBlankField(true);
			eb.addField("Sunrise", sunrise, true);
			eb.addField("Sunset", sunset, true);
			eb.addBlankField(true);
			eb.setTimestamp(Instant.ofEpochMilli(date));

		} catch (Exception e) {
			eb.setDescription("EmbedBuilder Error!");
			Reporter.fatal(e.getMessage());
		}

		return eb;
	}

	public static EmbedBuilder buildEmbed5Day(String responseBody) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("5 Day Forecast");

		try {
			JSONObject resp = (JSONObject) new JSONParser().parse(responseBody);

			eb.setDescription(((JSONObject) resp.get("city")).get("name").toString());

			// 5 Day responses have 40 entries, every 3 hours for 5 days
			JSONArray array = (JSONArray) resp.get("list");
			for (int i = 0; i < array.size(); i++) {
				JSONObject json = (JSONObject) array.get(i);
				ZonedDateTime date = MyDateUtils.longStringToZDT(json.get("dt").toString() + "000");

				if (date.getHour() == 12) {
					String desc = ((JSONObject) ((JSONArray) json.get("weather")).get(0)).get("main").toString();
					String icon = "http://openweathermap.org/img/w/" + ((JSONObject) ((JSONArray) json.get("weather")).get(0)).get("icon").toString() + ".png";
					String temp = Integer.toString(Math.round(Float.parseFloat(((JSONObject) json.get("main")).get("temp").toString())));
					String tempFeel = Integer.toString(Math.round(Float.parseFloat(((JSONObject) json.get("main")).get("feels_like").toString())));
					String tempMax = Integer.toString(Math.round(Float.parseFloat(((JSONObject) json.get("main")).get("temp_max").toString())));
					String tempMin = Integer.toString(Math.round(Float.parseFloat(((JSONObject) json.get("main")).get("temp_min").toString())));
					String windSpeed = Integer.toString(Math.round(Float.parseFloat(((JSONObject) json.get("wind")).get("speed").toString())));
					String windDirection = windDirection(Float.parseFloat(((JSONObject) json.get("wind")).get("deg").toString()));

					String s = String.format("%s %s. %s\n%s", date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()),
							date.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()).substring(0, 3), date.getDayOfMonth(),
							DateTimeFormatter.ofPattern("hh a").format(date).toUpperCase().replaceAll("(0|\\.)", ""));

					eb.addField(s, "\u200B", true);
					eb.addField(desc, String.format("%s°C, Feels Like %s°C", temp, tempFeel), true);
					eb.addField(String.format("Wind: %s kph %s", windSpeed, windDirection), String.format("High: %s°C Low: %s°C", tempMax, tempMin), true);
					eb.setThumbnail(icon);

					i += 7;
				}
			}

		} catch (Exception e) {
			eb.setDescription("EmbedBuilder Error!");
			Reporter.fatal(e.getMessage());
		}

		return eb;
	}

	private static String windDirection(Float degrees) {
		if (degrees < 0) return "X";
		if (degrees < 11.25) return "N";
		if (degrees < 33.75) return "NNE";
		if (degrees < 56.25) return "NE";
		if (degrees < 78.75) return "ENE";
		if (degrees < 101.25) return "E";
		if (degrees < 123.75) return "ESE";
		if (degrees < 146.25) return "SE";
		if (degrees < 168.75) return "SSE";
		if (degrees < 191.25) return "S";
		if (degrees < 213.75) return "SSW";
		if (degrees < 236.25) return "SW";
		if (degrees < 258.75) return "WSW";
		if (degrees < 281.25) return "W";
		if (degrees < 303.75) return "WNW";
		if (degrees < 326.25) return "NW";
		if (degrees < 348.75) return "NNW";
		if (degrees <= 360) return "N";
		return "X";
	}
}