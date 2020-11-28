package ca.darrensjones.jonesbot.test.command;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.command.CommandWeather;
import ca.darrensjones.jonesbot.testcore.BotTest;
import ca.darrensjones.jonesbot.testcore.TestUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-28
 * @since 1.0.0 2020-11-28
 */
public class TCommandWeather {

	private static final CommandWeather w = new CommandWeather(BotTest.get());

	@Test
	public void buildEmbedCurrent() {

		/* Current High */
		MessageEmbed high = w.buildEmbedCurrent(TestUtils.readFile("src/test/resources/mock/WeatherCurrentHigh.json")).build();
		Assert.assertEquals(high.getTitle(), "Current Weather");
		Assert.assertEquals(high.getDescription(), "This is the name of a town that is very long");
		Assert.assertEquals(high.getThumbnail().getUrl(), "http://openweathermap.org/img/w/04d.png");
		Assert.assertEquals(high.getFields().size(), 6);
		Assert.assertEquals(high.getFields().get(0).getName(), "Clouds");
		Assert.assertEquals(high.getFields().get(0).getValue(), "1000°C, Feels Like 1000°C");
		Assert.assertEquals(high.getFields().get(1).getName(), "Wind: 100 kph N");
		Assert.assertEquals(high.getFields().get(1).getValue(), "High: 1000°C Low: 1000°C");
		Assert.assertEquals(high.getFields().get(2).getName(), EmbedBuilder.ZERO_WIDTH_SPACE);
		Assert.assertEquals(high.getFields().get(2).getValue(), EmbedBuilder.ZERO_WIDTH_SPACE);
		Assert.assertEquals(high.getFields().get(3).getName(), "Sunrise");
		Assert.assertEquals(high.getFields().get(3).getValue(), "06:45:40");
		Assert.assertEquals(high.getFields().get(4).getName(), "Sunset");
		Assert.assertEquals(high.getFields().get(4).getValue(), "18:56:21");
		Assert.assertEquals(high.getFields().get(5).getName(), EmbedBuilder.ZERO_WIDTH_SPACE);
		Assert.assertEquals(high.getFields().get(5).getValue(), EmbedBuilder.ZERO_WIDTH_SPACE);

		/* Current Low */
		MessageEmbed low = w.buildEmbedCurrent(TestUtils.readFile("src/test/resources/mock/WeatherCurrentLow.json")).build();
		Assert.assertEquals(low.getTitle(), "Current Weather");
		Assert.assertEquals(low.getDescription(), "AZ");
		Assert.assertEquals(low.getThumbnail().getUrl(), "http://openweathermap.org/img/w/04d.png");
		Assert.assertEquals(low.getFields().size(), 6);
		Assert.assertEquals(low.getFields().get(0).getName(), "Clouds");
		Assert.assertEquals(low.getFields().get(0).getValue(), "-1000°C, Feels Like -1000°C");
		Assert.assertEquals(low.getFields().get(1).getName(), "Wind: 0 kph S");
		Assert.assertEquals(low.getFields().get(1).getValue(), "High: -1000°C Low: -1000°C");
		Assert.assertEquals(low.getFields().get(2).getName(), EmbedBuilder.ZERO_WIDTH_SPACE);
		Assert.assertEquals(low.getFields().get(2).getValue(), EmbedBuilder.ZERO_WIDTH_SPACE);
		Assert.assertEquals(low.getFields().get(3).getName(), "Sunrise");
		Assert.assertEquals(low.getFields().get(3).getValue(), "06:45:40");
		Assert.assertEquals(low.getFields().get(4).getName(), "Sunset");
		Assert.assertEquals(low.getFields().get(4).getValue(), "18:56:21");
		Assert.assertEquals(low.getFields().get(5).getName(), EmbedBuilder.ZERO_WIDTH_SPACE);
		Assert.assertEquals(low.getFields().get(5).getValue(), EmbedBuilder.ZERO_WIDTH_SPACE);
	}

	@Test(dependsOnMethods = "buildEmbedCurrent", alwaysRun = true)
	public void buildEmbed5Day() {

		/* 5 Day High */
		MessageEmbed high = w.buildEmbed5Day(TestUtils.readFile("src/test/resources/mock/Weather5DayHigh.json")).build();
		Assert.assertEquals(high.getTitle(), "5 Day Forecast");
		Assert.assertEquals(high.getDescription(), "city5DayHighEmbed");
		Assert.assertEquals(high.getThumbnail().getUrl(), "http://openweathermap.org/img/w/02d.png");
		Assert.assertEquals(high.getFields().size(), 15);
		Assert.assertEquals(high.getFields().get(0).getName(), "Saturday Sep. 26\n12 PM");
		Assert.assertEquals(high.getFields().get(0).getValue(), "\u200B");
		Assert.assertEquals(high.getFields().get(1).getName(), "Clouds");
		Assert.assertEquals(high.getFields().get(1).getValue(), "1000°C, Feels Like 1000°C");
		Assert.assertEquals(high.getFields().get(2).getName(), "Wind: 1000 kph NE");
		Assert.assertEquals(high.getFields().get(2).getValue(), "High: 1000°C Low: 1000°C");
		Assert.assertEquals(high.getFields().get(3).getName(), "Sunday Sep. 27\n12 PM");
		Assert.assertEquals(high.getFields().get(3).getValue(), "\u200B");
		Assert.assertEquals(high.getFields().get(4).getName(), "Clouds");
		Assert.assertEquals(high.getFields().get(4).getValue(), "1000°C, Feels Like 1000°C");
		Assert.assertEquals(high.getFields().get(5).getName(), "Wind: 1000 kph SE");
		Assert.assertEquals(high.getFields().get(5).getValue(), "High: 1000°C Low: 1000°C");
		Assert.assertEquals(high.getFields().get(6).getName(), "Monday Sep. 28\n12 PM");
		Assert.assertEquals(high.getFields().get(6).getValue(), "\u200B");
		Assert.assertEquals(high.getFields().get(7).getName(), "Clouds");
		Assert.assertEquals(high.getFields().get(7).getValue(), "1000°C, Feels Like 1000°C");
		Assert.assertEquals(high.getFields().get(8).getName(), "Wind: 1000 kph SW");
		Assert.assertEquals(high.getFields().get(8).getValue(), "High: 1000°C Low: 1000°C");
		Assert.assertEquals(high.getFields().get(9).getName(), "Tuesday Sep. 29\n12 PM");
		Assert.assertEquals(high.getFields().get(9).getValue(), "\u200B");
		Assert.assertEquals(high.getFields().get(10).getName(), "Clouds");
		Assert.assertEquals(high.getFields().get(10).getValue(), "1000°C, Feels Like 1000°C");
		Assert.assertEquals(high.getFields().get(11).getName(), "Wind: 1000 kph NW");
		Assert.assertEquals(high.getFields().get(11).getValue(), "High: 1000°C Low: 1000°C");
		Assert.assertEquals(high.getFields().get(12).getName(), "Wednesday Sep. 30\n12 PM");
		Assert.assertEquals(high.getFields().get(12).getValue(), "\u200B");
		Assert.assertEquals(high.getFields().get(13).getName(), "Clouds");
		Assert.assertEquals(high.getFields().get(13).getValue(), "1000°C, Feels Like 1000°C");
		Assert.assertEquals(high.getFields().get(14).getName(), "Wind: 1000 kph N");
		Assert.assertEquals(high.getFields().get(14).getValue(), "High: 1000°C Low: 1000°C");

		/* 5 Day Low */
		MessageEmbed low = w.buildEmbed5Day(TestUtils.readFile("src/test/resources/mock/Weather5DayLow.json")).build();
		Assert.assertEquals(low.getTitle(), "5 Day Forecast");
		Assert.assertEquals(low.getDescription(), "city5DayHighEmbed");
		Assert.assertEquals(low.getThumbnail().getUrl(), "http://openweathermap.org/img/w/02d.png");
		Assert.assertEquals(low.getFields().size(), 15);
		Assert.assertEquals(low.getFields().get(0).getName(), "Saturday Sep. 26\n12 PM");
		Assert.assertEquals(low.getFields().get(0).getValue(), "\u200B");
		Assert.assertEquals(low.getFields().get(1).getName(), "Clouds");
		Assert.assertEquals(low.getFields().get(1).getValue(), "-1000°C, Feels Like -1000°C");
		Assert.assertEquals(low.getFields().get(2).getName(), "Wind: 0 kph NE");
		Assert.assertEquals(low.getFields().get(2).getValue(), "High: -1000°C Low: -1000°C");
		Assert.assertEquals(low.getFields().get(3).getName(), "Sunday Sep. 27\n12 PM");
		Assert.assertEquals(low.getFields().get(3).getValue(), "\u200B");
		Assert.assertEquals(low.getFields().get(4).getName(), "Clouds");
		Assert.assertEquals(low.getFields().get(4).getValue(), "-1000°C, Feels Like -1000°C");
		Assert.assertEquals(low.getFields().get(5).getName(), "Wind: 0 kph SE");
		Assert.assertEquals(low.getFields().get(5).getValue(), "High: -1000°C Low: -1000°C");
		Assert.assertEquals(low.getFields().get(6).getName(), "Monday Sep. 28\n12 PM");
		Assert.assertEquals(low.getFields().get(6).getValue(), "\u200B");
		Assert.assertEquals(low.getFields().get(7).getName(), "Clouds");
		Assert.assertEquals(low.getFields().get(7).getValue(), "-1000°C, Feels Like -1000°C");
		Assert.assertEquals(low.getFields().get(8).getName(), "Wind: 0 kph SW");
		Assert.assertEquals(low.getFields().get(8).getValue(), "High: -1000°C Low: -1000°C");
		Assert.assertEquals(low.getFields().get(9).getName(), "Tuesday Sep. 29\n12 PM");
		Assert.assertEquals(low.getFields().get(9).getValue(), "\u200B");
		Assert.assertEquals(low.getFields().get(10).getName(), "Clouds");
		Assert.assertEquals(low.getFields().get(10).getValue(), "-1000°C, Feels Like -1000°C");
		Assert.assertEquals(low.getFields().get(11).getName(), "Wind: 0 kph NW");
		Assert.assertEquals(low.getFields().get(11).getValue(), "High: -1000°C Low: -1000°C");
		Assert.assertEquals(low.getFields().get(12).getName(), "Wednesday Sep. 30\n12 PM");
		Assert.assertEquals(low.getFields().get(12).getValue(), "\u200B");
		Assert.assertEquals(low.getFields().get(13).getName(), "Clouds");
		Assert.assertEquals(low.getFields().get(13).getValue(), "-1000°C, Feels Like -1000°C");
		Assert.assertEquals(low.getFields().get(14).getName(), "Wind: 0 kph N");
		Assert.assertEquals(low.getFields().get(14).getValue(), "High: -1000°C Low: -1000°C");
	}
}