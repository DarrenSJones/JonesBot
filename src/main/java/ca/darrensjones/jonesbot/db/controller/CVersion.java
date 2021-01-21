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
 * @version 1.1.4 2021-01-21
 * @since 1.1.4 2021-01-21
 */
public class CVersion {

	private static OVersion setRecord(String versionName) {
		String[] v = versionName.split("\\.");
		OVersion record = new OVersion();
		record.major = Integer.parseInt(v[0]);
		record.minor = Integer.parseInt(v[1]);
		record.patch = Integer.parseInt(v[2]);
		return record;
	}

	/** Reads the Change Log and not DB, but same process otherwise. */
	public static List<OVersion> getVersionList() {
		List<OVersion> versionList = new ArrayList<OVersion>();
		String[] versions = readChangelog().split("\n##\\s");
		for (int i = versions.length - 1; i > 0; i--) {
			String entry = versions[i].split("\n###\\s")[0];
			versionList.add(setRecord(entry.substring(1).split("]")[0]));
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