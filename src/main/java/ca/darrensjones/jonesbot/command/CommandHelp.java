package ca.darrensjones.jonesbot.command;

import java.awt.Color;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.1.2 2020-12-29
 * @since 1.0.0 2020-11-24
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
		return "**" + bot.config.BOT_PREFIX + "help** " + getDescription();
	}

	@Override
	public void execute(Message message) {
		String help = String.format("Commands are not case-sensitive. Try \"**{command} %shelp**\" for more information.", bot.config.BOT_PREFIX);
		for (AbstractCommand c : bot.commandHandler.commands) {
			if (c.visibility().isPublic()) {
				help += String.format("%n**%s%s**: %s", bot.config.BOT_PREFIX, c.getTriggers()[0], c.getDescription());
			}
		}

		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("Bot Help");
		eb.setDescription(help);
		eb.setColor(new Color(0, 153, 255));
		message.getChannel().sendMessage(eb.build()).queue();
	}
}