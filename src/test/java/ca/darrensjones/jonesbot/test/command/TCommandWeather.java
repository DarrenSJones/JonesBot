package ca.darrensjones.jonesbot.test.command;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.command.CommandWeather;
import ca.darrensjones.jonesbot.testcore.Mock;
import ca.darrensjones.jonesbot.testcore.TBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-23
 * @since   1.0.0 2020-11-28
 */
public class TCommandWeather {

	@Test
	public void process() {

		Mock.reset();
		String path = "src/test/resources/mock/weather/";

		// Current High
		Mock.setExpectation("GET",
				"/data/2.5/weather?units=metric&appid=12345678901234567890123456789012&q=Regina,Saskatchewan,CA",
				200, new File(path + "current_high.json"));
		MessageEmbed hc = CommandWeather.process(TBot.getBot(), "!w").build();
		Assert.assertEquals(hc.getTitle(), "Current Weather");
		Assert.assertEquals(hc.getUrl(), "http://localhost:1080/city/6119109");
		Assert.assertEquals(hc.getDescription(), "This is the name of a town that is very long");
		Assert.assertEquals(hc.getThumbnail().getUrl(), "http://openweathermap.org/img/w/04d.png");
		Assert.assertEquals(hc.getFields().size(), 6);
		Assert.assertEquals(hc.getFields().get(0).getName(), "Clouds");
		Assert.assertEquals(hc.getFields().get(0).getValue(), "1000°C, Feels Like 1000°C");
		Assert.assertEquals(hc.getFields().get(1).getName(), "Wind: 100 kph N");
		Assert.assertEquals(hc.getFields().get(1).getValue(), "High: 1000°C Low: 1000°C");
		Assert.assertEquals(hc.getFields().get(2).getName(), EmbedBuilder.ZERO_WIDTH_SPACE);
		Assert.assertEquals(hc.getFields().get(2).getValue(), EmbedBuilder.ZERO_WIDTH_SPACE);
		Assert.assertEquals(hc.getFields().get(3).getName(), "Sunrise");
		Assert.assertEquals(hc.getFields().get(3).getValue(), "06:45:40");
		Assert.assertEquals(hc.getFields().get(4).getName(), "Sunset");
		Assert.assertEquals(hc.getFields().get(4).getValue(), "18:56:21");
		Assert.assertEquals(hc.getFields().get(5).getName(), EmbedBuilder.ZERO_WIDTH_SPACE);
		Assert.assertEquals(hc.getFields().get(5).getValue(), EmbedBuilder.ZERO_WIDTH_SPACE);

		// Current Low
		Mock.setExpectation("GET",
				"/data/2.5/weather?units=metric&appid=12345678901234567890123456789012&q=New York",
				200, new File(path + "current_low.json"));
		MessageEmbed lc = CommandWeather.process(TBot.getBot(), "!w New York").build();
		Assert.assertEquals(lc.getTitle(), "Current Weather");
		Assert.assertEquals(lc.getUrl(), "http://localhost:1080/city/6119109");
		Assert.assertEquals(lc.getDescription(), "AZ");
		Assert.assertEquals(lc.getThumbnail().getUrl(), "http://openweathermap.org/img/w/04d.png");
		Assert.assertEquals(lc.getFields().size(), 6);
		Assert.assertEquals(lc.getFields().get(0).getName(), "Clouds");
		Assert.assertEquals(lc.getFields().get(0).getValue(), "-1000°C, Feels Like -1000°C");
		Assert.assertEquals(lc.getFields().get(1).getName(), "Wind: 0 kph S");
		Assert.assertEquals(lc.getFields().get(1).getValue(), "High: -1000°C Low: -1000°C");
		Assert.assertEquals(lc.getFields().get(2).getName(), EmbedBuilder.ZERO_WIDTH_SPACE);
		Assert.assertEquals(lc.getFields().get(2).getValue(), EmbedBuilder.ZERO_WIDTH_SPACE);
		Assert.assertEquals(lc.getFields().get(3).getName(), "Sunrise");
		Assert.assertEquals(lc.getFields().get(3).getValue(), "06:45:40");
		Assert.assertEquals(lc.getFields().get(4).getName(), "Sunset");
		Assert.assertEquals(lc.getFields().get(4).getValue(), "18:56:21");
		Assert.assertEquals(lc.getFields().get(5).getName(), EmbedBuilder.ZERO_WIDTH_SPACE);
		Assert.assertEquals(lc.getFields().get(5).getValue(), EmbedBuilder.ZERO_WIDTH_SPACE);

		// 5 Day High
		Mock.setExpectation("GET",
				"/data/2.5/forecast?units=metric&appid=12345678901234567890123456789012&q=Regina,Saskatchewan,CA",
				200, new File(path + "forecast_high.json"));
		MessageEmbed h5 = CommandWeather.process(TBot.getBot(), "!w !5day").build();
		Assert.assertEquals(h5.getTitle(), "5 Day Forecast");
		Assert.assertEquals(h5.getUrl(), "http://localhost:1080/city/6119109");
		Assert.assertEquals(h5.getDescription(), "city5DayHighEmbed");
		Assert.assertEquals(h5.getThumbnail().getUrl(), "http://openweathermap.org/img/w/02d.png");
		Assert.assertEquals(h5.getFields().size(), 15);
		Assert.assertEquals(h5.getFields().get(0).getName(), "Saturday Sep. 26\n12 PM");
		Assert.assertEquals(h5.getFields().get(0).getValue(), "\u200B");
		Assert.assertEquals(h5.getFields().get(1).getName(), "Clouds");
		Assert.assertEquals(h5.getFields().get(1).getValue(), "1000°C, Feels Like 1000°C");
		Assert.assertEquals(h5.getFields().get(2).getName(), "Wind: 1000 kph NE");
		Assert.assertEquals(h5.getFields().get(2).getValue(), "High: 1000°C Low: 1000°C");
		Assert.assertEquals(h5.getFields().get(3).getName(), "Sunday Sep. 27\n12 PM");
		Assert.assertEquals(h5.getFields().get(3).getValue(), "\u200B");
		Assert.assertEquals(h5.getFields().get(4).getName(), "Clouds");
		Assert.assertEquals(h5.getFields().get(4).getValue(), "1000°C, Feels Like 1000°C");
		Assert.assertEquals(h5.getFields().get(5).getName(), "Wind: 1000 kph SE");
		Assert.assertEquals(h5.getFields().get(5).getValue(), "High: 1000°C Low: 1000°C");
		Assert.assertEquals(h5.getFields().get(6).getName(), "Monday Sep. 28\n12 PM");
		Assert.assertEquals(h5.getFields().get(6).getValue(), "\u200B");
		Assert.assertEquals(h5.getFields().get(7).getName(), "Clouds");
		Assert.assertEquals(h5.getFields().get(7).getValue(), "1000°C, Feels Like 1000°C");
		Assert.assertEquals(h5.getFields().get(8).getName(), "Wind: 1000 kph SW");
		Assert.assertEquals(h5.getFields().get(8).getValue(), "High: 1000°C Low: 1000°C");
		Assert.assertEquals(h5.getFields().get(9).getName(), "Tuesday Sep. 29\n12 PM");
		Assert.assertEquals(h5.getFields().get(9).getValue(), "\u200B");
		Assert.assertEquals(h5.getFields().get(10).getName(), "Clouds");
		Assert.assertEquals(h5.getFields().get(10).getValue(), "1000°C, Feels Like 1000°C");
		Assert.assertEquals(h5.getFields().get(11).getName(), "Wind: 1000 kph NW");
		Assert.assertEquals(h5.getFields().get(11).getValue(), "High: 1000°C Low: 1000°C");
		Assert.assertEquals(h5.getFields().get(12).getName(), "Wednesday Sep. 30\n12 PM");
		Assert.assertEquals(h5.getFields().get(12).getValue(), "\u200B");
		Assert.assertEquals(h5.getFields().get(13).getName(), "Clouds");
		Assert.assertEquals(h5.getFields().get(13).getValue(), "1000°C, Feels Like 1000°C");
		Assert.assertEquals(h5.getFields().get(14).getName(), "Wind: 1000 kph N");
		Assert.assertEquals(h5.getFields().get(14).getValue(), "High: 1000°C Low: 1000°C");

		// 5 Day High
		Mock.setExpectation("GET",
				"/data/2.5/forecast?units=metric&appid=12345678901234567890123456789012&q=New York",
				200, new File(path + "forecast_low.json"));
		MessageEmbed l5 = CommandWeather.process(TBot.getBot(), "!w !5day New York").build();
		Assert.assertEquals(l5.getTitle(), "5 Day Forecast");
		Assert.assertEquals(l5.getUrl(), "http://localhost:1080/city/6119109");
		Assert.assertEquals(l5.getDescription(), "city5DayHighEmbed");
		Assert.assertEquals(l5.getThumbnail().getUrl(), "http://openweathermap.org/img/w/02d.png");
		Assert.assertEquals(l5.getFields().size(), 15);
		Assert.assertEquals(l5.getFields().get(0).getName(), "Saturday Sep. 26\n12 PM");
		Assert.assertEquals(l5.getFields().get(0).getValue(), "\u200B");
		Assert.assertEquals(l5.getFields().get(1).getName(), "Clouds");
		Assert.assertEquals(l5.getFields().get(1).getValue(), "-1000°C, Feels Like -1000°C");
		Assert.assertEquals(l5.getFields().get(2).getName(), "Wind: 0 kph NE");
		Assert.assertEquals(l5.getFields().get(2).getValue(), "High: -1000°C Low: -1000°C");
		Assert.assertEquals(l5.getFields().get(3).getName(), "Sunday Sep. 27\n12 PM");
		Assert.assertEquals(l5.getFields().get(3).getValue(), "\u200B");
		Assert.assertEquals(l5.getFields().get(4).getName(), "Clouds");
		Assert.assertEquals(l5.getFields().get(4).getValue(), "-1000°C, Feels Like -1000°C");
		Assert.assertEquals(l5.getFields().get(5).getName(), "Wind: 0 kph SE");
		Assert.assertEquals(l5.getFields().get(5).getValue(), "High: -1000°C Low: -1000°C");
		Assert.assertEquals(l5.getFields().get(6).getName(), "Monday Sep. 28\n12 PM");
		Assert.assertEquals(l5.getFields().get(6).getValue(), "\u200B");
		Assert.assertEquals(l5.getFields().get(7).getName(), "Clouds");
		Assert.assertEquals(l5.getFields().get(7).getValue(), "-1000°C, Feels Like -1000°C");
		Assert.assertEquals(l5.getFields().get(8).getName(), "Wind: 0 kph SW");
		Assert.assertEquals(l5.getFields().get(8).getValue(), "High: -1000°C Low: -1000°C");
		Assert.assertEquals(l5.getFields().get(9).getName(), "Tuesday Sep. 29\n12 PM");
		Assert.assertEquals(l5.getFields().get(9).getValue(), "\u200B");
		Assert.assertEquals(l5.getFields().get(10).getName(), "Clouds");
		Assert.assertEquals(l5.getFields().get(10).getValue(), "-1000°C, Feels Like -1000°C");
		Assert.assertEquals(l5.getFields().get(11).getName(), "Wind: 0 kph NW");
		Assert.assertEquals(l5.getFields().get(11).getValue(), "High: -1000°C Low: -1000°C");
		Assert.assertEquals(l5.getFields().get(12).getName(), "Wednesday Sep. 30\n12 PM");
		Assert.assertEquals(l5.getFields().get(12).getValue(), "\u200B");
		Assert.assertEquals(l5.getFields().get(13).getName(), "Clouds");
		Assert.assertEquals(l5.getFields().get(13).getValue(), "-1000°C, Feels Like -1000°C");
		Assert.assertEquals(l5.getFields().get(14).getName(), "Wind: 0 kph N");
		Assert.assertEquals(l5.getFields().get(14).getValue(), "High: -1000°C Low: -1000°C");

		// City not found Current
		Mock.setExpectation("GET",
				"/data/2.5/weather?units=metric&appid=12345678901234567890123456789012&q=FakeTownName",
				404, new File(path + "city_not_found.json"));
		MessageEmbed cnfc = CommandWeather.process(TBot.getBot(), "!w FakeTownName").build();
		Assert.assertEquals(cnfc.getTitle(), "Current Weather");
		Assert.assertEquals(cnfc.getUrl(), "http://localhost:1080/find?q=FakeTownName");
		Assert.assertEquals(cnfc.getDescription(), "City not found: FakeTownName");

		// City not found Forecast
		Mock.setExpectation("GET",
				"/data/2.5/forecast?units=metric&appid=12345678901234567890123456789012&q=FakeTownName",
				404, new File(path + "city_not_found.json"));
		MessageEmbed cnff = CommandWeather.process(TBot.getBot(), "!w !5day FakeTownName").build();
		Assert.assertEquals(cnff.getTitle(), "5 Day Forecast");
		Assert.assertEquals(cnff.getUrl(), "http://localhost:1080/find?q=FakeTownName");
		Assert.assertEquals(cnff.getDescription(), "City not found: FakeTownName");
	}
}