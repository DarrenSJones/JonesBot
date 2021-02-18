package ca.darrensjones.jonesbot.command;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import ca.darrensjones.jonesbot.db.model.OAutoResponseReaction;
import ca.darrensjones.jonesbot.utilities.DiscordUtils;
import java.awt.Color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-18
 * @since   1.0.0 2020-11-23
 */
public class CommandReaction extends AbstractCommand {

	public CommandReaction(Bot bot) {
		super(bot);
	}

	@Override
	public String getName() {
		return "Reaction";
	}

	@Override
	public String getDescription() {
		return "A list of reactions the Bot will respond with";
	}

	@Override
	public String[] getTriggers() {
		return new String[] { "reaction", "reactions" };
	}

	@Override
	public CommandVisibility visibility() {
		return CommandVisibility.PUBLIC;
	}

	@Override
	public String getHelp() {
		return "**" + bot.getPrefix() + "reaction** " + getDescription();
	}

	@Override
	public void execute(Message message) {
		String description = "Contact your Admin for additions:";
		for (OAutoResponseReaction reaction : bot.dataHandler.autoResponseReactions) {
			String code = reaction.unicode;
			if (reaction.isCustom()) { // Custom emoji are guild-dependent
				code = DiscordUtils.getCustomEmoji(bot.jda, message.getGuild().getId(), code);
			}
			description += String.format("\n<%s> regex:[%s]", code, reaction.regex);
		}

		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("Reaction List");
		eb.setDescription(description);
		eb.setColor(new Color(0, 153, 255));
		message.getChannel().sendMessage(eb.build()).queue();
	}
}