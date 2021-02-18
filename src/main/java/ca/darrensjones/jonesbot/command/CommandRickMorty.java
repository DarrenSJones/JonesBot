package ca.darrensjones.jonesbot.command;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import ca.darrensjones.jonesbot.command.utilities.Frinkiac;
import ca.darrensjones.jonesbot.db.model.OFrinkiacSaved;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-18
 * @since   1.0.0 2020-12-08
 */
public class CommandRickMorty extends AbstractCommand {

	public CommandRickMorty(Bot bot) {
		super(bot);
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
		return Frinkiac.getHelp(bot.getPrefix());
	}

	@Override
	public void execute(Message message) {
		String prefix = bot.getPrefix();
		Color color = new Color(207, 219, 219);
		String host = bot.getConfig().HOST_RICKMORTY;
		List<OFrinkiacSaved> saved = bot.dataHandler.rickMortySaved;
		HashMap<String, String[]> last = bot.dataHandler.rickMortyLast;
		EmbedBuilder eb = Frinkiac.process(message, prefix, color, host, saved, last);

		message.getChannel().sendMessage(eb.build()).queue();
	}
}