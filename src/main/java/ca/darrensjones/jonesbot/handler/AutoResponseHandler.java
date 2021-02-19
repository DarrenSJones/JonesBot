package ca.darrensjones.jonesbot.handler;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.db.model.OAutoResponseReaction;
import ca.darrensjones.jonesbot.log.Reporter;
import ca.darrensjones.jonesbot.utilities.DiscordUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-18
 * @since   1.0.0 2020-11-18
 */
public class AutoResponseHandler {

	public final Bot bot;

	public AutoResponseHandler(Bot bot) {
		this.bot = bot;
	}

	public void process(Message message) {
		if (hasReaction(message.getContentDisplay())) {
			Reporter.logMessage(message, "AutoResponse Reaction Start.");
			for (OAutoResponseReaction reaction : getReactions(message.getContentDisplay())) {
				String output = reaction.unicode;
				if (reaction.isCustom()) {
					output = DiscordUtils.getCustomEmoji(bot.jda, message.getGuild().getId(),
							reaction.unicode);
				}
				Reporter.info(String.format("Posting Reaction. id:[%s] output:[%s] regex:[%s]",
						reaction.id, output, reaction.regex));
				message.addReaction(output).queue();
			}
			Reporter.info("AutoResponse Reaction End.");
		}
	}

	/**
	 * @param  content Message Content
	 * @return         True if content contains one or more AutoResponseReaction regex matches,
	 *                 False otherwise
	 */
	public boolean hasReaction(String content) {
		for (OAutoResponseReaction reaction : bot.dataHandler.autoResponseReactions) {
			Pattern pattern = Pattern.compile("(?=(\\W|^)" + reaction.regex + "(\\W|$))");
			if (pattern.matcher(content.toLowerCase()).find()) return true;
		}
		return false;
	}

	/**
	 * @param  content Message Content
	 * @return         List containing all Reaction matches within 'content', in order
	 */
	public List<OAutoResponseReaction> getReactions(String content) {
		ArrayList<Object[]> a = new ArrayList<Object[]>(); // <index, OReaction>
		for (OAutoResponseReaction reaction : bot.dataHandler.autoResponseReactions) {
			Pattern pattern = Pattern.compile("(?=(\\W|^)" + reaction.regex + "(\\W|$))");
			Matcher matcher = pattern.matcher(content.toLowerCase());
			if (matcher.find()) {
				int index = 0;
				for (Object[] obj : a) {
					if (matcher.start() > (Integer) obj[0]) {
						index++;
					} else {
						break;
					}
				}
				a.add(index, new Object[] { matcher.start(), reaction });
			}
		}
		List<OAutoResponseReaction> l = new ArrayList<OAutoResponseReaction>();
		for (Object[] obj : a) {
			l.add((OAutoResponseReaction) obj[1]);
		}
		return l;
	}
}