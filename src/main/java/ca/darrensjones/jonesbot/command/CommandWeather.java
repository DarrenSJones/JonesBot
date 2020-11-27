package ca.darrensjones.jonesbot.command;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-26
 * @since 1.0.0 2020-11-26
 */
public class CommandWeather extends AbstractCommand {

	public CommandWeather() {
		super();
	}

	@Override
	public String getName() {
		return "Weather";
	}

	@Override
	public String getDescription() {
		return "Gets the Weather from http://https://openweathermap.org/";
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
		String output = "**%sweather** " + getDescription();
		return output;
	}

	@Override
	public void execute(Bot bot, Message message) {
		message.getChannel().sendMessage("Weather!").queue();
	}
}