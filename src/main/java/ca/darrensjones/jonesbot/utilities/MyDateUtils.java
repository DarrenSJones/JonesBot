package ca.darrensjones.jonesbot.utilities;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author Darren Jones
 * @version 1.0.1 2020-12-15
 * @since 1.0.0 2020-11-28
 */
public class MyDateUtils {

	public static ZonedDateTime longToZDT(String epochMilliString) {
		long epochMilli = Long.parseLong(epochMilliString);
		return ZonedDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault());
	}
}