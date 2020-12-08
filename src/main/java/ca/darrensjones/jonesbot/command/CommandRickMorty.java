package ca.darrensjones.jonesbot.command;

import java.awt.Color;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-12-08
 * @since 1.0.0 2020-12-08
 */
public class CommandRickMorty extends AbstractCommand {

	private final String host;
	private final Color color;

	public CommandRickMorty(Bot bot) {
		super(bot);
		this.host = bot.config.RICKMORTY_HOST;
		this.color = new Color(207, 219, 219);
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
		EmbedBuilder eb = new EmbedBuilder();
		eb.setColor(color);
		eb.setTitle("Command: " + getName());
		eb.setDescription(host);
		message.getChannel().sendMessage(eb.build()).queue();
	}
}