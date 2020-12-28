package ca.darrensjones.jonesbot.handler;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.log.Reporter;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.react.GenericMessageReactionEvent;

/**
 * @author Darren Jones
 * @version 1.1.0 2020-12-28
 * @since 1.1.0 2020-12-27
 */
public class ReactionHandler {

	private final Bot bot;

	public ReactionHandler(Bot bot) {
		this.bot = bot;
	}

	public void process(GenericMessageReactionEvent event, String userId, boolean add) {
		User user = bot.jda.retrieveUserById(userId).complete(); // Remove Reaction doesn't return User information
		if (user.isBot()) return;

		if (add) {
			Reporter.info(String.format("Reaction Added! Guild:[%s] Channel:[%s] Message:[%s] User:[%s] Emote:[%s]", event.getGuild().getName(),
					event.getChannel().getName(), event.getMessageId(), user.getName(), event.getReactionEmote().getEmoji()), false);
		} else {
			Reporter.info(String.format("Reaction Removed! Guild:[%s] Channel:[%s] Message:[%s] User:[%s] Emote:[%s]", event.getGuild().getName(),
					event.getChannel().getName(), event.getMessageId(), user.getName(), event.getReactionEmote().getEmoji()), false);
		}
	}
}