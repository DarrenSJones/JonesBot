package ca.darrensjones.jonesbot.db.model;

import java.time.LocalDate;

import ca.darrensjones.jonesbot.db.AbstractModel;

/**
 * @author Darren Jones
 * @version 1.2.0 2021-02-12
 * @since 1.2.0 2021-02-11
 */
public class OSimpleSchedule extends AbstractModel {
	public int id;
	public LocalDate event_date;
	public String guildId;
	public String channelId;
	public String event_time;
	public String event_value;
}