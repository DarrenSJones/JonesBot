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
 * @version 1.2.1 2021-02-22
 * @since   1.0.0 2020-11-18
 */
public class AutoResponseHandler {

	public final Bot bot;

	public AutoResponseHandler(Bot bot) {
		this.bot = bot;
	}

	public void process(Message message) {
		if (hasReaction(message.getContentDisplay())) {
			Reporter.info("Start AutoResponse reaction.");
			Reporter.log(message);
			for (OAutoResponseReaction reaction : getReactions(message.getContentDisplay())) {
				String emoji = reaction.unicode;
				if (reaction.isCustom()) { // Custom varies by guild
					emoji = DiscordUtils.getCustomEmoji(bot.jda, message.getGuild().getId(),
							reaction.unicode);
				}
				Reporter.info(String.format("Posting AutoResponse reaction. emoji:[%s] regex:[%s]",
						emoji, reaction.regex));
				message.addReaction(emoji).queue();
			}
			Reporter.info("End AutoResponse reaction.");
		}
	}

	/**
	 * @param  content Message content.
	 * @return         True if content contains one or more AutoResponseReaction regex matches,
	 *                 false otherwise.
	 */
	public boolean hasReaction(String content) {
		for (OAutoResponseReaction reaction : bot.dataHandler.autoResponseReactions) {
			Pattern pattern = Pattern.compile("(?=(\\W|^)(" + reaction.regex + ")(\\W|$))");
			if (pattern.matcher(content.toLowerCase()).find()) return true;
		}
		return false;
	}

	/**
	 * @param  content Message content.
	 * @return         List containing all reaction matches within 'content', in order.
	 */
	public List<OAutoResponseReaction> getReactions(String content) {
		ArrayList<Object[]> ordered = new ArrayList<Object[]>(); // <index, OReaction>
		for (OAutoResponseReaction reaction : bot.dataHandler.autoResponseReactions) {
			Pattern pattern = Pattern.compile("(?=(\\W|^)(" + reaction.regex + ")(\\W|$))");
			Matcher matcher = pattern.matcher(content.toLowerCase());
			if (matcher.find()) {
				int index = 0;
				for (Object[] obj : ordered) {
					if (matcher.start() <= (Integer) obj[0]) break;
					index++;
				}
				ordered.add(index, new Object[] { matcher.start(), reaction });
			}
		}
		List<OAutoResponseReaction> list = new ArrayList<OAutoResponseReaction>();
		for (Object[] obj : ordered) {
			list.add((OAutoResponseReaction) obj[1]);
		}
		return list;
	}
}