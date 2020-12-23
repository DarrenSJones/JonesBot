package ca.darrensjones.jonesbot.command;

import java.awt.Color;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.0.2 2020-12-22
 * @since 1.0.2 2020-12-22
 */
public class CommandChangeLog extends AbstractCommand {

	public CommandChangeLog(Bot bot) {
		super(bot);
	}

	@Override
	public String getName() {
		return "Change Log";
	}

	@Override
	public String getDescription() {
		return "JonesBot Change Log Information";
	}

	@Override
	public String[] getTriggers() {
		return new String[] { "changelog" };
	}

	@Override
	public CommandVisibility visibility() {
		return CommandVisibility.HIDDEN;
	}

	@Override
	public String getHelp() {
		return "**" + bot.config.BOT_PREFIX + "changelog** Displays change log link list.";
	}

	@Override
	public void execute(Message message) {
		String current = bot.config.BOT_VERSION;
		String description = "The change log is linked above";
//		for (String v : Version.getVersions(false)) description += String.format("%n%s/blob/v%s/CHANGELOG.md", bot.config.BOT_GITHUB_REPO, v);
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle(String.format("Current Version: %s", current), String.format("%s/blob/main/CHANGELOG.md", bot.config.BOT_GITHUB_REPO));
		eb.setDescription(description);
		eb.setColor(new Color(0, 153, 255));
		message.getChannel().sendMessage(eb.build()).queue();
	}
}