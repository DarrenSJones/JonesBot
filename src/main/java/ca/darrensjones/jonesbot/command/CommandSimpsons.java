package ca.darrensjones.jonesbot.command;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-28
 * @since 1.0.0 2020-11-28
 */
public class CommandSimpsons extends AbstractCommand {

	public CommandSimpsons(Bot bot) {
		super(bot);
	}

	@Override
	public String getName() {
		return "Simpsons";
	}

	@Override
	public String getDescription() {
		return "Returns an image from http://frinkiac.com";
	}

	@Override
	public String[] getTriggers() {
		return new String[] { "simpsons", "s" };
	}

	@Override
	public CommandVisibility visibility() {
		return CommandVisibility.PUBLIC;
	}

	@Override
	public String getHelp() {
		return "**%ssimpsons** " + getDescription();
	}

	@Override
	public void execute(Message message) {
		message.getChannel().sendMessage("Simpsons").queue();
	}
}