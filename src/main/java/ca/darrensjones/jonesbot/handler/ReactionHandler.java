package ca.darrensjones.jonesbot.handler;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.log.Reporter;
import ca.darrensjones.jonesbot.utilities.DiscordUtils;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.react.GenericMessageReactionEvent;

/**
 * @author Darren Jones
 * @version 1.1.1 2020-12-29
 * @since 1.1.0 2020-12-27
 */
public class ReactionHandler {

	private final Bot bot;

	public ReactionHandler(Bot bot) {
		this.bot = bot;
	}

	public void process(GenericMessageReactionEvent event, String userId, boolean add) {
		User user = DiscordUtils.getUser(bot.jda, userId);
		if (user.isBot()) return;

		String action = "Removed";
		if (add) action = "Added";

		Reporter.info(String.format("Reaction %s! Guild:[%s] Channel:[%s] Message:[%s] User:[%s] Emote:[%s]", action, event.getGuild().getName(),
				event.getChannel().getName(), event.getMessageId(), user.getName(), event.getReactionEmote().getEmoji()), false);
	}
}