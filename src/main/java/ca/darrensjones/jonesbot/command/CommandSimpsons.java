package ca.darrensjones.jonesbot.command;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import ca.darrensjones.jonesbot.command.utilities.Frinkiac;
import ca.darrensjones.jonesbot.db.model.OFrinkiacSaved;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.1.3 2021-01-14
 * @since 1.0.0 2020-11-28
 */
public class CommandSimpsons extends AbstractCommand {

	public CommandSimpsons(Bot bot) {
		super(bot);
	}

	@Override
	public String getName() {
		return "Simpsons";
	}

	@Override
	public String getDescription() {
		return "Returns an image from http://frinkiac.com";
	}

	@Override
	public String[] getTriggers() {
		return new String[] { "simpsons", "s" };
	}

	@Override
	public CommandVisibility visibility() {
		return CommandVisibility.PUBLIC;
	}

	@Override
	public String getHelp() {
		return Frinkiac.getHelp(bot.getPrefix());
	}

	@Override
	public void execute(Message message) {
		String prefix = bot.getPrefix();
		Color color = new Color(254, 217, 15);
		String host = bot.getConfig().HOST_SIMPSONS;
		List<OFrinkiacSaved> saved = bot.dataHandler.simpsonsSaved;
		HashMap<String, String[]> last = bot.dataHandler.simpsonsLast;

		message.getChannel().sendMessage(Frinkiac.process(message, prefix, color, host, saved, last).build()).queue();
	}
}