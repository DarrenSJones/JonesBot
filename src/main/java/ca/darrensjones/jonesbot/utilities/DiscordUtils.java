package ca.darrensjones.jonesbot.utilities;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

/**
 * @author Darren Jones
 * @version 1.1.3 2021-01-19
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

	public static Message getMessage(JDA jda, String textChannelId, String messageId) {
		return jda.getTextChannelById(textChannelId).retrieveMessageById(messageId).complete();
	}

	public static Message getMessage(TextChannel textChannel, String messageId) {
		return textChannel.retrieveMessageById(messageId).complete();
	}

	/** Used when the User isn't defined and has to be retrieved. */
	public static User getUser(JDA jda, String userId) {
		return jda.retrieveUserById(userId).complete();
	}

	/** Used when the User isn't defined and has to be retrieved. */
	public static boolean isBot(JDA jda, String userId) {
		if (jda.retrieveUserById(userId).complete().isBot()) return false;
		return true;
	}
}