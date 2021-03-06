package ca.darrensjones.jonesbot.command.meta;

import ca.darrensjones.jonesbot.bot.Bot;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-27
 * @since 1.0.0 2020-11-23
 */
public abstract class AbstractCommand {

	protected final Bot bot;

	public AbstractCommand(Bot bot) {
		this.bot = bot;
	}

	/**
	 * The name of the Command
	 * 
	 * @return name
	 */
	public abstract String getName();

	/**
	 * A short description of the Command
	 * 
	 * @return description
	 */
	public abstract String getDescription();

	/**
	 * Commands can have multiple triggers besides the name
	 * 
	 * @return triggers
	 */
	public abstract String[] getTriggers();

	/**
	 * Whether the Command is listed in Help or not
	 * 
	 * @return True if in Help list, False otherwise
	 */
	public abstract CommandVisibility visibility();

	/**
	 * Help specific to the Command
	 * 
	 * @return Command Help
	 */
	public abstract String getHelp();

	/**
	 * Executes the Command
	 * 
	 * @param message The Message that triggered the Command
	 */
	public abstract void execute(Message message);
}