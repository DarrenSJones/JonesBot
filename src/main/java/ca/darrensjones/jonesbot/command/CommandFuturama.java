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
 * @since 1.0.0 2020-12-08
 */
public class CommandFuturama extends AbstractCommand {

	public CommandFuturama(Bot bot) {
		super(bot);
	}

	@Override
	public String getName() {
		return "Futurama";
	}

	@Override
	public String getDescription() {
		return "Returns an image from http://morbotron.com";
	}

	@Override
	public String[] getTriggers() {
		return new String[] { "futurama", "f" };
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
		Color color = new Color(112, 227, 162);
		String host = bot.getConfig().HOST_FUTURAMA;
		List<OFrinkiacSaved> saved = bot.dataHandler.futuramaSaved;
		HashMap<String, String[]> last = bot.dataHandler.futuramaLast;

		message.getChannel().sendMessage(Frinkiac.process(message, prefix, color, host, saved, last).build()).queue();
	}
}