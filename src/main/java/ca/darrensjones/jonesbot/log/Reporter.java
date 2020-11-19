package ca.darrensjones.jonesbot.log;

import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.LogManager;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-18
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
}
