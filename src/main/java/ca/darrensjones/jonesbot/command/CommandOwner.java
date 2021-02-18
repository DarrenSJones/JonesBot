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
public class CommandOwner extends AbstractCommand {

	public CommandOwner(Bot bot) {
		super(bot);
	}

	@Override
	public String getName() {
		return "Owner";
	}

	@Override
	public String getDescription() {
		return "The full list of Owner-only Commands";
	}

	@Override
	public String[] getTriggers() {
		return new String[] { "owner" };
	}

	@Override
	public CommandVisibility visibility() {
		return CommandVisibility.OWNER;
	}

	@Override
	public String getHelp() {
		return "**" + bot.getPrefix() + "owner** " + getDescription();
	}

	@Override
	public void execute(Message message) {
		if (!message.getAuthor().getId().equals(bot.getConfig().BOT_OWNER_ID)) return;

		String prefix = bot.getPrefix();
		String help = String.format("Try \"**{command} %shelp**\" for more information.", prefix);
		for (AbstractCommand c : bot.commandHandler.commands) {
			if (c.visibility().isOwner()) {
				String trigger = c.getTriggers()[0];
				String description = c.getDescription();
				help += String.format("%n**%s%s**: %s", bot.getPrefix(), trigger, description);
			}
		}

		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("Bot Owner Help");
		eb.setDescription(help);
		eb.setColor(new Color(0, 153, 255));
		message.getChannel().sendMessage(eb.build()).queue();
	}
}