package ca.darrensjones.jonesbot.command;

import java.time.Instant;
import java.time.ZoneId;
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
import ca.darrensjones.jonesbot.utilities.RequestUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.1.4 2021-02-02
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
		String p = bot.getPrefix();
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

		String token = bot.getConfig().WEATHER_TOKEN;
		String host = bot.getConfig().HOST_WEATHER;
		String defaultCity = bot.getConfig().WEATHER_DEFAULT;

		boolean is5Day;
		String city;
		String request;

		city = content.replaceAll(bot.getPrefix() + "\\w+(\\s+)?", "").trim().replaceAll("\\s", "%20");
		if (StringUtils.isBlank(city)) city = defaultCity;

		if (Pattern.compile(bot.getPrefix() + "5day\\s?").matcher(content.toLowerCase()).find()) {
			is5Day = true;
			request = String.format("%s/data/2.5/forecast?units=metric&appid=%s&q=%s", host, token, city);
		} else {
			is5Day = false;
			request = String.format("%s/data/2.5/weather?units=metric&appid=%s&q=%s", host, token, city);
		}

		EmbedBuilder eb = new EmbedBuilder();
		try {
			String response = RequestUtils.getResponseBody(request);

			if (is5Day) { // Forecast
				eb.setTitle("5 Day Forecast", String.format("%s/find?q=%s", host, city).replace("api.", ""));

				JSONObject resp = (JSONObject) new JSONParser().parse(response);
				String id = ((JSONObject) resp.get("city")).get("id").toString();
				String name = ((JSONObject) resp.get("city")).get("name").toString();

				eb.setTitle("5 Day Forecast", String.format("%s/city/%s", host, id).replace("api.", ""));
				eb.setDescription(name);

				// 5 Day responses have 40 entries, every 3 hours for 5 days
				JSONArray array = (JSONArray) resp.get("list");
				for (int i = 0; i < array.size(); i++) {
					JSONObject json = (JSONObject) array.get(i);
					ZonedDateTime date = longToZDT(json.get("dt").toString() + "000");

					if (date.getHour() == 12) {
						String desc = ((JSONObject) ((JSONArray) json.get("weather")).get(0)).get("main").toString();
						String icon = "http://openweathermap.org/img/w/" + ((JSONObject) ((JSONArray) json.get("weather")).get(0)).get("icon").toString()
								+ ".png";
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

			} else { // Current
				eb.setTitle("Current Weather", String.format("%s/find?q=%s", host, city).replace("api.", ""));

				JSONObject json = (JSONObject) new JSONParser().parse(response);
				String desc = ((JSONObject) ((JSONArray) json.get("weather")).get(0)).get("main").toString();
				String icon = "http://openweathermap.org/img/w/" + ((JSONObject) ((JSONArray) json.get("weather")).get(0)).get("icon").toString() + ".png";
				long date = Long.parseLong(json.get("dt").toString() + "000");
				String temp = Integer.toString(Math.round(Float.parseFloat(((JSONObject) json.get("main")).get("temp").toString())));
				String tempFeel = Integer.toString(Math.round(Float.parseFloat(((JSONObject) json.get("main")).get("feels_like").toString())));
				String tempMax = Integer.toString(Math.round(Float.parseFloat(((JSONObject) json.get("main")).get("temp_max").toString())));
				String tempMin = Integer.toString(Math.round(Float.parseFloat(((JSONObject) json.get("main")).get("temp_min").toString())));
				String windSpeed = Integer.toString(Math.round(Float.parseFloat(((JSONObject) json.get("wind")).get("speed").toString())));
				String windDirection = windDirection(Float.parseFloat(((JSONObject) json.get("wind")).get("deg").toString()));
				String id = json.get("id").toString();
				String name = json.get("name").toString();
				String sunrise = longToZDT(((JSONObject) json.get("sys")).get("sunrise").toString() + "000").toLocalTime().toString();
				String sunset = longToZDT(((JSONObject) json.get("sys")).get("sunset").toString() + "000").toLocalTime().toString();

				eb.setTitle("Current Weather", String.format("%s/city/%s", host, id).replace("api.", ""));
				eb.setDescription(name);
				eb.setThumbnail(icon);
				eb.addField(desc, String.format("%s°C, Feels Like %s°C", temp, tempFeel), true);
				eb.addField(String.format("Wind: %s kph %s", windSpeed, windDirection), String.format("High: %s°C Low: %s°C", tempMax, tempMin), true);
				eb.addBlankField(true);
				eb.addField("Sunrise", sunrise, true);
				eb.addField("Sunset", sunset, true);
				eb.addBlankField(true);
				eb.setTimestamp(Instant.ofEpochMilli(date));
			}

		} catch (NullPointerException e) {
			eb.setDescription("City Not Found: " + city);

		} catch (Exception e) {
			String message = "CommandWeather EmbedBuilder error.";
			Reporter.fatal(message, e);
			eb.setDescription(message);
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

	private static ZonedDateTime longToZDT(String epochMilliString) {
		long epochMilli = Long.parseLong(epochMilliString);
		return ZonedDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault());
	}
}