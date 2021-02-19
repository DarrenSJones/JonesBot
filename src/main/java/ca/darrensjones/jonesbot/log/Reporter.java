package ca.darrensjones.jonesbot.log;

import org.apache.logging.log4j.Logger;

import net.dv8tion.jda.api.entities.Message;

import org.apache.logging.log4j.LogManager;

/**
 * @author  Darren Jones
 * @version 1.1.4 2021-02-02
 * @since   1.0.0 2020-11-18
 */
public class Reporter {

	private static final Logger trace = LogManager.getLogger("TraceLogger");
	private static final Logger console = LogManager.getLogger("ConsoleLogger");

	public static void info(String message) {
		trace.info(message);
		console.info(message);
	}

	public static void error(String message) {
		trace.error(message);
		console.error(message);
	}

	public static void error(String message, Exception e) {
		trace.error(message);
		console.error(message);
		e.printStackTrace();
	}

	public static void debug(String message) {
		trace.debug(message);
		console.debug(message);
	}

	public static void warn(String message) {
		trace.warn(message);
		console.warn(message);
	}

	public static void fatal(String message, Exception e) {
		message = String.format("%s Exception:[%s]", message, e.getMessage());
		trace.fatal(message);
		console.fatal(message);
		e.printStackTrace();
	}

	/**
	 * Logs Message information into the Console and Trace Log.
	 * 
	 * @param message Discord Message
	 * @param msg     Reason the message is being logged
	 */
	public static void logMessage(Message message, String msg) {
		String authorName, authorId;
		String guildName, guildId;
		String channelName, channelId;
		String messageContent, messageId;

		switch (message.getChannelType()) {
		case PRIVATE:
			authorName = message.getAuthor().getName();
			authorId = message.getAuthor().getId();
			guildName = "none";
			guildId = "none";
			channelName = "private";
			channelId = "private";
			messageContent = message.getContentDisplay();
			messageId = message.getId();
			break;

		case GROUP:
			authorName = message.getAuthor().getName();
			authorId = message.getAuthor().getId();
			guildName = "none";
			guildId = "none";
			channelName = "group";
			channelId = "group";
			messageContent = message.getContentDisplay();
			messageId = message.getId();
			break;

		case TEXT:
			authorName = message.getAuthor().getName();
			authorId = message.getAuthor().getId();
			guildName = message.getGuild().getName();
			guildId = message.getGuild().getId();
			channelName = message.getChannel().getName();
			channelId = message.getChannel().getId();
			messageContent = message.getContentDisplay();
			messageId = message.getId();
			break;

		default:
			authorName = "";
			authorId = "";
			guildName = "";
			guildId = "";
			channelName = "";
			channelId = "";
			messageContent = "";
			messageId = "";
			Reporter.error(String.format("logMessage ChannelType Invalid:[%s]",
					message.getChannelType().toString()));
		}

		Reporter.info(String.format("%s Author:[%s] Guild:[%s] Channel:[%s] MessageContent:[%s]",
				msg, authorName, guildName, channelName, messageContent));
		Reporter.debug(String.format("AuthorId:[%s] GuildId:[%s] ChannelId:[%s] MessageId:[%s]",
				authorId, guildId, channelId, messageId));
	}
}
