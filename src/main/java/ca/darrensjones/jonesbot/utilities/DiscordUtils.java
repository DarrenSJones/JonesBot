package ca.darrensjones.jonesbot.utilities;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-18
 * @since   1.1.1 2020-12-29
 */
public class DiscordUtils {

	/**
	 * Returns the custom emoji code for the given guild, as custom emoji codes are guild-specific.
	 * 
	 * @param  jda     Bot's connection to Discord.
	 * @param  guildId Guild that contains the custom emoji.
	 * @param  emoji   Name of the emoji.
	 * @return         Custom emoji code, formatted for the given guild.
	 */
	public static String getCustomEmoji(JDA jda, String guildId, String emoji) {
		for (Emote emote : jda.getGuildById(guildId).getEmotes()) {
			if (emote.getName().equals(emoji.replaceAll(":", ""))) {
				return String.format("%s%s", emoji, emote.getId());
			}
		}
		return emoji;
	}

	/**
	 * When a message isn't defined, gets that message.
	 * 
	 * @param  textChannel TextChannel to get the message from.
	 * @param  messageId   Message to retrieve.
	 * @return             The message belonging to the messageId.
	 */
	public static Message getMessage(TextChannel textChannel, String messageId) {
		return textChannel.retrieveMessageById(messageId).complete();
	}

	/**
	 * When a message isn't defined, gets that message.
	 * 
	 * @param  jda         Bot's connection to Discord.
	 * @param  textChannel TextChannel to get the message from.
	 * @param  messageId   Message to retrieve.
	 * @return             The message belonging to the messageId.
	 */
	public static Message getMessage(JDA jda, String textChannelId, String messageId) {
		return jda.getTextChannelById(textChannelId).retrieveMessageById(messageId).complete();
	}

	/**
	 * When a user isn't defined, gets that user.
	 * 
	 * @param  jda    Bot's connection to Discord.
	 * @param  userId User to retrieve.
	 * @return        The user belonging to the userId.
	 */
	public static User getUser(JDA jda, String userId) {
		return jda.retrieveUserById(userId).complete();
	}

	/**
	 * When a user isn't defined, checks if the userId belongs to a bot.
	 * 
	 * @param  jda    Bot's connection to Discord.
	 * @param  userId User to check.
	 * @return        True if the user is a bot, false otherwise.
	 */
	public static boolean isBot(JDA jda, String userId) {
		if (jda.retrieveUserById(userId).complete().isBot()) return false;
		return true;
	}
}