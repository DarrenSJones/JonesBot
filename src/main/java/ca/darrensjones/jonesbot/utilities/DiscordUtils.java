package ca.darrensjones.jonesbot.utilities;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.User;

/**
 * @author Darren Jones
 * @version 1.1.1 2020-12-29
 * @since 1.1.1 2020-12-29
 */
public class DiscordUtils {

	/** Custom Emoji are guild-specific. */
	public static String getCustomEmoji(JDA jda, String guildId, String emoji) {
		for (Emote emote : jda.getGuildById(guildId).getEmotes()) {
			if (emote.getName().equals(emoji.replaceAll(":", ""))) {
				return String.format("%s%s", emoji, emote.getId());
			}
		}
		return emoji;
	}

	public static User getUser(JDA jda, String userId) {
		return jda.retrieveUserById(userId).complete();
	}
}