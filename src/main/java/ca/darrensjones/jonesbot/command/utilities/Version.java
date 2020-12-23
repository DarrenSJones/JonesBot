package ca.darrensjones.jonesbot.command.utilities;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import ca.darrensjones.jonesbot.log.Reporter;

/**
 * @author Darren Jones
 * @version 1.0.2 2020-12-22
 * @since 1.0.2 2020-12-22
 */
public class Version {

	public static List<String> getVersions(boolean ascending) {
		List<String> versions = new ArrayList<String>();
		String[] changesByVersion = readChangelog().split("\n## ");
		if (ascending) {
			for (int i = changesByVersion.length - 1; i > 0; i--) {
				String entry = changesByVersion[i].split("\n### ")[0];
				versions.add(entry.substring(1).split("]")[0]);
			}
		} else {
			for (int i = 1; i < changesByVersion.length; i++) {
				String entry = changesByVersion[i].split("\n### ")[0];
				versions.add(entry.substring(1).split("]")[0]);
			}
		}
		return versions;
	}

	public static List<String> getVersionEntry(boolean ascending) {
		List<String> versions = new ArrayList<String>();
		String[] changesByVersion = readChangelog().split("\n## ");
		if (ascending) {
			for (int i = changesByVersion.length - 1; i > 0; i--) {
				String entry = changesByVersion[i].split("\n### ")[0];
				versions.add(entry.substring(entry.indexOf("]") + 1));
			}
		} else {
			for (int i = 1; i < changesByVersion.length; i++) {
				String entry = changesByVersion[i].split("\n### ")[0];
				versions.add(entry.substring(entry.indexOf("]") + 1));
			}
		}
		return versions;
	}

	private static String readChangelog() {
		try {
			return FileUtils.readFileToString(new File("CHANGELOG.MD"), StandardCharsets.UTF_8);
		} catch (Exception e) {
			Reporter.fatal("ReadChangelog Error.\n" + e.getMessage());
			return null;
		}
	}

	public static String readToDo() {
		try {
			return FileUtils.readFileToString(new File("todo.txt"), StandardCharsets.UTF_8);
		} catch (Exception e) {
			Reporter.fatal("ReadChangelog Error.\n" + e.getMessage());
			return null;
		}
	}
}