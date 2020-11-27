package ca.darrensjones.jonesbot.main;

import ca.darrensjones.jonesbot.bot.Bot;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-26
 * @since 1.0.0 2020-11-18
 */
public class EventListener extends ListenerAdapter {

	private final Bot bot;

	public EventListener(Bot bot) {
		this.bot = bot;
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.getAuthor().isBot()) return;

		if (bot.commandHandler.isCommand(event.getMessage().getContentDisplay())) bot.commandHandler.process(event.getMessage());

		bot.autoResponseHandler.process(event.getMessage());
	}
}