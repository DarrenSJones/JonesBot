package ca.darrensjones.jonesbot.command.meta;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-24
 * @since 1.0.0 2020-11-24
 */
public enum CommandVisibility {

	PUBLIC(), HIDDEN(), OWNER();

	public boolean isPublic() {
		return this.equals(PUBLIC);
	}

	public boolean isHidden() {
		return this.equals(HIDDEN);
	}

	public boolean isOwner() {
		return this.equals(OWNER);
	}
}