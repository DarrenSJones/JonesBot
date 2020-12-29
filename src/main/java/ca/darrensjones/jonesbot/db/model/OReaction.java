package ca.darrensjones.jonesbot.db.model;

import ca.darrensjones.jonesbot.db.AbstractModel;

/**
 * @author Darren Jones
 * @version 1.1.1 2020-12-29
 * @since 1.0.0 2020-11-18
 */
public class OReaction extends AbstractModel {
	public int id;
	public String shortcode;
	public String unicode;
	public String regex;

	public boolean isCustom() {
		if (unicode.startsWith(":") && unicode.endsWith(":")) return true;
		return false;
	}
}