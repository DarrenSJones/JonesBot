package ca.darrensjones.jonesbot.command;

import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-23
 * @since 1.0.0 2020-11-23
 */
public abstract class AbstractCommand {

	public AbstractCommand() {

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
	 * Whether the Command is listed in Help or not
	 * 
	 * @return True if in Help list, False otherwise
	 */
	public abstract boolean isVisible();

	/**
	 * Help specific to the Command
	 * 
	 * @return Command Help
	 */
	public abstract void help(Message message);

	/**
	 * Executes the Command
	 * 
	 * @param message The Message that triggered the Command
	 */
	public abstract void execute(Message message);
}