package ca.darrensjones.jonesbot.command;

import java.awt.Color;

import ca.darrensjones.jonesbot.bot.Bot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-24
 * @since 1.0.0 2020-11-24
 */
public class CommandHelp extends AbstractCommand {

	public CommandHelp() {
		super();
	}

	@Override
	public String getName() {
		return "Help";
	}

	@Override
	public String getDescription() {
		return "The full list of Commands (you are here!)";
	}

	@Override
	public String[] getTriggers() {
		return new String[] { "help", "h" };
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public String getHelp() {
		return "**%shelp** " + getDescription();
	}

	@Override
	public void execute(Bot bot, Message message) {
		String help = String.format("Commands are not case-sensitive. Try \"{command} %shelp\" for more information.", bot.config.BOT_PREFIX);
		for (AbstractCommand c : bot.commandHandler.getCommands()) {
			help += String.format("%n**%s%s**: %s", bot.config.BOT_PREFIX, c.getTriggers()[0], c.getDescription());
		}

		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("Bot Help");
		eb.setDescription(help);
		eb.setColor(new Color(0, 153, 255));
		message.getChannel().sendMessage(eb.build()).queue();
	}
}