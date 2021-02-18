package ca.darrensjones.jonesbot.main;

import ca.darrensjones.jonesbot.bot.Bot;
//import ca.darrensjones.jonesbot.utilities.DiscordUtils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-18
 * @since   1.0.0 2020-11-18
 */
public class EventListener extends ListenerAdapter {

	private final Bot bot;

	public EventListener(Bot bot) {
		this.bot = bot;
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.getAuthor().isBot()) return;

		if (bot.commandHandler.isCommand(event.getMessage().getContentDisplay())) {
			bot.commandHandler.process(event.getMessage());
		}

		bot.autoResponseHandler.process(event.getMessage());
	}

	@Override
	public void onMessageReactionAdd(MessageReactionAddEvent event) {
//		if (DiscordUtils.isBot(bot.jda, event.getUserId())) return;
//		bot.reactionHandler.process(event, true);
	}

	@Override
	public void onMessageReactionRemove(MessageReactionRemoveEvent event) {
//		if (DiscordUtils.isBot(bot.jda, event.getUserId())) return;
//		bot.reactionHandler.process(event, false);
	}
}