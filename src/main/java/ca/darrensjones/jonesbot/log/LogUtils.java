package ca.darrensjones.jonesbot.log;

import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.1.1 2020-12-29
 * @since 1.0.0 2020-11-18
 */
public class LogUtils {

	public static String logMessage(Message message) {
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

		case GROUP:
			author = message.getAuthor().getName();
			guild = "none";
			channel = "group";
			content = message.getContentDisplay();
			break;

		case TEXT:
			author = message.getAuthor().getName();
			guild = message.getGuild().getName();
			channel = message.getChannel().getName();
			content = message.getContentDisplay();
			break;

		default:
			Reporter.warn(String.format("logMessage ChannelType Invalid:[%s]", message.getChannelType().toString()));
		}

		return String.format("Author:[%s] Guild:[%s] Channel:[%s] Content:[%s]", author, guild, channel, content);
	}
}