package ca.darrensjones.jonesbot.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.db.model.OReaction;
import ca.darrensjones.jonesbot.log.LogUtils;
import ca.darrensjones.jonesbot.log.Reporter;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.1.1 2020-12-29
 * @since 1.0.0 2020-11-18
 */
public class AutoResponseHandler {

	public final Bot bot;

	public AutoResponseHandler(Bot bot) {
		this.bot = bot;
	}

	public void process(Message message) {
		if (hasReaction(message.getContentDisplay())) {
			Reporter.info("Start Reaction. " + LogUtils.logMessage(message));
			for (OReaction reaction : getReactions(message.getContentDisplay())) {
				if (reaction.isCustom()) {
					for (Emote emote : bot.jda.getGuildById(message.getGuild().getId()).getEmotes()) {
						if (emote.getName().equals(reaction.unicode.replaceAll(":", ""))) {
							Reporter.info("Reaction Match. " + reaction.toLog(), true);
							message.addReaction(reaction.unicode + emote.getId()).queue();
							break;
						}
					}
				} else {
					Reporter.info("Reaction Match. " + reaction.toLog(), true);
					message.addReaction(reaction.unicode).queue();
				}
			}
			Reporter.info("End Reaction.");
		}
	}

	public boolean hasReaction(String content) {
		for (OReaction reaction : bot.dataHandler.autoResponseReactions) {
			Pattern pattern = Pattern.compile("(?=(\\W|^)" + reaction.regex + "(\\W|$))");
			if (pattern.matcher(content.toLowerCase()).find()) return true;
		}
		return false;
	}

	public List<OReaction> getReactions(String content) {
		ArrayList<Object[]> a = new ArrayList<Object[]>(); // <index, OReaction>
		for (OReaction reaction : bot.dataHandler.autoResponseReactions) {
			Pattern pattern = Pattern.compile("(?=(\\W|^)" + reaction.regex + "(\\W|$))");
			Matcher matcher = pattern.matcher(content.toLowerCase());
			if (matcher.find()) {
				int index = 0;
				for (Object[] obj : a) {
					if (matcher.start() > (Integer) obj[0]) index++;
					else break;
				}
				a.add(index, new Object[] { matcher.start(), reaction });
			}
		}
		List<OReaction> l = new ArrayList<OReaction>();
		for (Object[] obj : a) l.add((OReaction) obj[1]);
		return l;
	}
}