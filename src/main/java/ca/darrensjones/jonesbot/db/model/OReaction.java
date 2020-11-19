package ca.darrensjones.jonesbot.db.model;

import ca.darrensjones.jonesbot.db.AbstractModel;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-19
 * @since 1.0.0 2020-11-18
 */
public class OReaction extends AbstractModel {
	public int id;
	public String shortcode;
	public String unicode;
	public String regex;

	public String toLog() {
		return String.format("id:[%s] shortcode:[%s] unicode:[%s] regex:[%s]", id, shortcode, unicode, regex);
	}
}