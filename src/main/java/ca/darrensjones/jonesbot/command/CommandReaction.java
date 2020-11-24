package ca.darrensjones.jonesbot.command;

import java.awt.Color;
import java.util.List;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.db.controller.CReaction;
import ca.darrensjones.jonesbot.db.model.OReaction;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-24
 * @since 1.0.0 2020-11-23
 */
public class CommandReaction extends AbstractCommand {

	public CommandReaction() {
		super();
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
	public boolean isVisible() {
		return true;
	}

	@Override
	public void help(Message message) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("Help: " + getName());
		eb.setDescription("Nothing to Help!");
		eb.setColor(new Color(0, 153, 255));
		message.getChannel().sendMessage(eb.build()).queue();
	}

	@Override
	public void execute(Bot bot, Message message) {
		List<OReaction> list = CReaction.getAll();
		String description = "Contact your Admin for additions:";
		for (OReaction r : list) description += String.format("\n%s", r.toEmbed());
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("Reaction List");
		eb.setDescription(description);
		eb.setColor(new Color(0, 153, 255));
		message.getChannel().sendMessage(eb.build()).queue();
	}
}