package ca.darrensjones.jonesbot.testcore;

import java.io.File;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

import ca.darrensjones.jonesbot.log.Reporter;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-28
 * @since 1.0.0 2020-11-28
 */
public class TestUtils {

	public static String readFile(String filePath) {
		String output = "";
		try {
			output = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		} catch (Exception e) {
			Reporter.fatal(e.getMessage());
		}
		return output;
	}
}