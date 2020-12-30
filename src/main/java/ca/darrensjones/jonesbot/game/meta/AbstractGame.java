package ca.darrensjones.jonesbot.game.meta;

import java.util.ArrayList;
import java.util.List;

import ca.darrensjones.jonesbot.bot.Bot;
import net.dv8tion.jda.api.entities.User;

/**
 * @author Darren Jones
 * @version 1.2.0 2020-12-29
 * @since 1.2.0 2020-12-29
 */
public abstract class AbstractGame {

	protected final Bot bot;
	private GameStatus gameStatus;
	private List<User> players;

	public AbstractGame(Bot bot) {
		this.bot = bot;
		reload();
	}

	/**
	 * The name of the Game
	 * 
	 * @return name
	 */
	public abstract String getName();

	/**
	 * A short description of the Game
	 * 
	 * @return description
	 */
	public abstract String getDescription();

	/**
	 * Triggers for the Game
	 * 
	 * @return triggers
	 */
	public abstract String[] getTriggers();

	/**
	 * Returns the current GameStatus
	 * 
	 * @return gameStatus
	 */
	public GameStatus getGameStatus() {
		return gameStatus;
	}

	/**
	 * Returns all Players in the current Game
	 * 
	 * @return players
	 */
	public List<User> getPlayers() {
		return players;
	}

	/**
	 * Posts an embed displaying help for the Game
	 */
	public abstract String help();

	/**
	 * Reloads the game
	 */
	public void reload() {
		gameStatus = GameStatus.OVER;
		players = new ArrayList<User>();
	}
}