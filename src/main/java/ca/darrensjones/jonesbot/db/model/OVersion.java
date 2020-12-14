package ca.darrensjones.jonesbot.db.model;

import java.time.LocalDate;

import ca.darrensjones.jonesbot.db.AbstractModel;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-12-14
 * @since 1.0.0 2020-12-14
 */
public class OVersion extends AbstractModel {
	public int major;
	public int minor;
	public int patch;
	public LocalDate date;
	public String changes;
}