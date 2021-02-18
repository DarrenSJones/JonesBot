package ca.darrensjones.jonesbot.db.model;

import ca.darrensjones.jonesbot.db.AbstractModel;
import java.time.LocalDate;

/**
 * @author  Darren Jones
 * @version 1.2.0 2021-02-12
 * @since   1.2.0 2021-02-11
 */
public class OSimpleSchedule extends AbstractModel {
	public int id;
	public LocalDate event_date;
	public String guildId;
	public String channelId;
	public String event_time;
	public String event_value;
}