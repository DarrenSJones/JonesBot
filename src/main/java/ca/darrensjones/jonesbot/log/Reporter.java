package ca.darrensjones.jonesbot.log;

import org.apache.logging.log4j.Logger;

import net.dv8tion.jda.api.entities.Message;

import org.apache.logging.log4j.LogManager;

/**
 * @author Darren Jones
 * @version 1.1.2 2021-01-13
 * @since 1.0.0 2020-11-18
 */
public class Reporter {

	private static final Logger trace = LogManager.getLogger("TraceLogger");
	private static final Logger console = LogManager.getLogger("ConsoleLogger");

	public static void info(String message) {
		info(message, true);
	}

	public static void info(String message, boolean printToConsole) {
		trace.info(message);
		if (printToConsole) console.info(message);
	}

	public static void error(String message) {
		error(message, true);
	}

	public static void error(String message, boolean printToConsole) {
		trace.error(message);
		if (printToConsole) console.error(message);
	}

	public static void debug(String message) {
		debug(message, true);
	}

	public static void debug(String message, boolean printToConsole) {
		trace.debug(message);
		if (printToConsole) console.debug(message);
	}

	public static void warn(String message) {
		warn(message, true);
	}

	public static void warn(String message, boolean printToConsole) {
		trace.warn(message);
		if (printToConsole) console.warn(message);
	}

	public static void fatal(String message) {
		fatal(message, true);
	}

	public static void fatal(String message, boolean printToConsole) {
		trace.fatal(message);
		if (printToConsole) console.fatal(message);
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
			channelName = "private";
			channelId = "private";
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
			Reporter.error(String.format("logMessage ChannelType Invalid:[%s]", message.getChannelType().toString()));
		}

		Reporter.info(String.format("%s Author:[%s] Guild:[%s] Channel:[%s] MessageContent:[%s]", msg, authorName, guildName, channelName, messageContent));
		Reporter.info(String.format("AuthorId:[%s] GuildId:[%s] ChannelId:[%s] MessageId:[%s]", authorId, guildId, channelId, messageId), false);
	}
}
