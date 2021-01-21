package ca.darrensjones.jonesbot.db.model;

import ca.darrensjones.jonesbot.db.AbstractModel;

/**
 * @author Darren Jones
 * @version 1.1.4 2021-01-21
 * @since 1.1.4 2021-01-21
 */
public class OVersion extends AbstractModel {
	public int major;
	public int minor;
	public int patch;

	public String getName() {
		return new String(major + "." + minor + "." + patch);
	}
}