package ca.darrensjones.jonesbot.command;

import java.awt.Color;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import ca.darrensjones.jonesbot.db.model.OReaction;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.1.1 2020-12-29
 * @since 1.0.0 2020-11-23
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
		return "**" + bot.config.BOT_PREFIX + "reaction** " + getDescription();
	}

	@Override
	public void execute(Message message) {
		String description = "Contact your Admin for additions:";
		for (OReaction reaction : bot.dataHandler.autoResponseReactions) {
			String output = reaction.unicode;
			String regex = reaction.regex;
			if (reaction.isCustom()) {
				for (Emote emote : bot.jda.getGuildById(message.getGuild().getId()).getEmotes()) {
					if (emote.getName().equals(reaction.unicode.replaceAll(":", ""))) {
						output = String.format("<%s%s>", reaction.unicode, emote.getId());
						break;
					}
				}
			}
			description += String.format("\n%s regex:[%s]", output, regex);
		}
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("Reaction List");
		eb.setDescription(description);
		eb.setColor(new Color(0, 153, 255));
		message.getChannel().sendMessage(eb.build()).queue();
	}
}