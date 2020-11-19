package ca.darrensjones.jonesbot.log;

import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-18
 * @since 1.0.0 2020-11-18
 */
public class LogUtils {

	public static String getMessageInfo(Message message) {
		String author = "";
		String guild = "";
		String channel = "";
		String content = "";

		switch (message.getChannelType()) {
		case PRIVATE:
			author = message.getAuthor().getName();
			guild = "none";
			channel = "private";
			content = message.getContentDisplay();
			break;

		case TEXT:
			author = message.getAuthor().getName();
			guild = message.getGuild().getName();
			channel = message.getChannel().getName();
			content = message.getContentDisplay();
			break;

		default:
			Reporter.warn("MessageReceivedEvent ChannelType Invalid: [" + message.getChannelType().toString() + "]", true);
		}

		return String.format("Author:[%s] Guild:[%s] Channel:[%s] Content:[%s]", author, guild, channel, content);
	}
}