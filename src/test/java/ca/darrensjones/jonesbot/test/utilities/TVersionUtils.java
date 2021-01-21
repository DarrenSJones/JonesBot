package ca.darrensjones.jonesbot.test.utilities;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.db.controller.CVersion;
import ca.darrensjones.jonesbot.db.model.OVersion;

/**
 * @author Darren Jones
 * @version 1.1.4 2021-01-21
 * @since 1.1.4 2021-01-21
 */
public class TVersionUtils {

	@Test
	public void getVersionList() {
		List<OVersion> versions = CVersion.getVersionList();

		for (OVersion version : versions) {
			Assert.assertTrue(Integer.toString(version.major).matches("\\d"));
			Assert.assertTrue(Integer.toString(version.minor).matches("\\d"));
			Assert.assertTrue(Integer.toString(version.patch).matches("\\d"));
			Assert.assertTrue(version.getName().matches("\\d\\.\\d\\.\\d"));
		}
	}
}