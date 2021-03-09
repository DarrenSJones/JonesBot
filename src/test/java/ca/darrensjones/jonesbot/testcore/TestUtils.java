package ca.darrensjones.jonesbot.testcore;

import ca.darrensjones.jonesbot.log.Reporter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-03-09
 * @since   1.0.0 2020-11-28
 */
public class TestUtils {

	public static String readFile(String filePath) {
		String output = "";
		try {
			output = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		} catch (Exception e) {
			Reporter.error("TestUtils readFile.", e);
		}
		return output;
	}
}