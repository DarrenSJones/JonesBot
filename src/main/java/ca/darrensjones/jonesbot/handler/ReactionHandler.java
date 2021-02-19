package ca.darrensjones.jonesbot.handler;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.log.Reporter;
import ca.darrensjones.jonesbot.utilities.DiscordUtils;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageReaction.ReactionEmote;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.react.GenericMessageReactionEvent;

/**
 * @author  Darren Jones
 * @version 1.1.2 2021-01-13
 * @since   1.1.0 2020-12-27
 */
public class ReactionHandler {

	private final Bot bot;

	public ReactionHandler(Bot bot) {
		this.bot = bot;
	}

	// Action (added/removed)
	// Message ID
	// User
	// Reaction Emote

	public void process(GenericMessageReactionEvent event, boolean add) {

		String action = "Removed";
		if (add) action = "Added";

		TextChannel textChannel = event.getTextChannel();
		Message message = DiscordUtils.getMessage(textChannel, event.getMessageId());
		User user = DiscordUtils.getUser(bot.jda, event.getUserId());
		ReactionEmote reactionEmote = event.getReactionEmote();

		Reporter.debug(String.format(
				"Reaction %s! Guild:[%s] Channel:[%s] MessageId:[%s] User:[%s] Emote:[%s]", action,
				textChannel.getGuild().getName(), textChannel.getName(), message.getId(),
				user.getName(), reactionEmote.getEmoji()));
	}
}