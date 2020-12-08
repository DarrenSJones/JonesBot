package ca.darrensjones.jonesbot.command;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-12-08
 * @since 1.0.0 2020-12-08
 */
public class CommandRickMorty extends AbstractCommand {

	public CommandRickMorty(Bot bot) {
		super(bot);
	}

	@Override
	public String getName() {
		return "Rick&Morty";
	}

	@Override
	public String getDescription() {
		return "Returns an image from http://masterofallscience.com";
	}

	@Override
	public String[] getTriggers() {
		return new String[] { "rick&morty", "rick", "morty" };
	}

	@Override
	public CommandVisibility visibility() {
		return CommandVisibility.PUBLIC;
	}

	@Override
	public String getHelp() {
		return "**%srick&morty** " + getDescription();
	}

	@Override
	public void execute(Message message) {
		message.getChannel().sendMessage("Command: Rick&Morty").queue();
	}
}