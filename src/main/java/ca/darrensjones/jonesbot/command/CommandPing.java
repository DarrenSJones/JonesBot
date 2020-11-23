package ca.darrensjones.jonesbot.command;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-23
 * @since 1.0.0 2020-11-23
 */
public class CommandPing extends AbstractCommand {

	public CommandPing() {
		super();
	}

	@Override
	public String getName() {
		return "Ping";
	}

	@Override
	public String getDescription() {
		return "Pong!";
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
		eb.setColor(new Color(0, 0, 0));
		message.getChannel().sendMessage(eb.build()).queue();
	}

	@Override
	public void execute(Message message) {
		message.getChannel().sendMessage("Pong!").queue();
	}
}