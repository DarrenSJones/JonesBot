package ca.darrensjones.jonesbot.db.model;

import java.time.LocalDate;

import ca.darrensjones.jonesbot.db.AbstractModel;

/**
 * @author Darren Jones
 * @version 1.2.0 2021-02-11
 * @since 1.2.0 2021-02-11
 */
public class OSimpleSchedule extends AbstractModel {
	public int id;
	public LocalDate date;
	public int dayOfWeek;
	public String guildId;
	public String channelId;
	public String time;
	public String value;
}