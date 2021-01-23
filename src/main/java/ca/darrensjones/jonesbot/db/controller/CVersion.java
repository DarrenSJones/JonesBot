package ca.darrensjones.jonesbot.db.controller;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import ca.darrensjones.jonesbot.db.model.OVersion;
import ca.darrensjones.jonesbot.log.Reporter;

/**
 * @author Darren Jones
 * @version 1.1.4 2021-01-22
 * @since 1.1.4 2021-01-22
 */
public class CVersion {

	private static OVersion setRecord(String changelogRow) {
		String[] s = changelogRow.split("\\s-\\s");
		String[] version = s[0].substring(1).split("]")[0].split("\\.");
		OVersion record = new OVersion();
		record.major = Integer.parseInt(version[0]);
		record.minor = Integer.parseInt(version[1]);
		record.patch = Integer.parseInt(version[2]);
		record.date = s[1];
		record.description = s[2];
		return record;
	}

	/** Reads the Change Log and not DB, but same process otherwise. */
	public static List<OVersion> getVersionList() {
		List<OVersion> versionList = new ArrayList<OVersion>();
		String[] versions = readChangelog().split("\n##\\s");
		for (int i = versions.length - 1; i > 0; i--) {
			String row = versions[i].split("\n###\\s")[0];
			versionList.add(setRecord(row));
		}
		return versionList;
	}

	private static String readChangelog() {
		try {
			return FileUtils.readFileToString(new File("CHANGELOG.MD"), StandardCharsets.UTF_8);
		} catch (Exception e) {
			Reporter.fatal("ReadChangelog Error.\n" + e.getMessage());
			return null;
		}
	}
}