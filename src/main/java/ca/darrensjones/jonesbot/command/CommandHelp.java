package ca.darrensjones.jonesbot.command;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import java.awt.Color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-18
 * @since   1.0.0 2020-11-24
 */
public class CommandHelp extends AbstractCommand {

	public CommandHelp(Bot bot) {
		super(bot);
	}

	@Override
	public String getName() {
		return "Help";
	}

	@Override
	public String getDescription() {
		return "The full list of Commands";
	}

	@Override
	public String[] getTriggers() {
		return new String[] { "help", "h" };
	}

	@Override
	public CommandVisibility visibility() {
		return CommandVisibility.PUBLIC;
	}

	@Override
	public String getHelp() {
		return "**" + bot.getPrefix() + "help** " + getDescription();
	}

	@Override
	public void execute(Message message) {
		String prefix = bot.getPrefix();
		String help = String.format("Try \"**{command} %shelp**\" for more information.", prefix);
		for (AbstractCommand c : bot.commandHandler.commands) {
			if (c.visibility().isPublic()) {
				String trigger = c.getTriggers()[0];
				String description = c.getDescription();
				help += String.format("%n**%s%s**: %s", prefix, trigger, description);
			}
		}

		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("Bot Help");
		eb.setDescription(help);
		eb.setColor(new Color(0, 153, 255));
		message.getChannel().sendMessage(eb.build()).queue();
	}
}