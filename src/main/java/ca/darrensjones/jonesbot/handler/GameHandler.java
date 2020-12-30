package ca.darrensjones.jonesbot.handler;

//import java.util.ArrayList;
//import java.util.List;

import ca.darrensjones.jonesbot.bot.Bot;
//import ca.darrensjones.jonesbot.game.meta.AbstractGame;
//import ca.darrensjones.jonesbot.game.pickanumber.PickANumber;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.2.0 2020-12-29
 * @since 1.2.0 2020-12-29
 */
public class GameHandler {

	private final Bot bot;
//	private static List<AbstractGame> games;

	public GameHandler(Bot bot) {
		this.bot = bot;
		setGames();
	}

	public void setGames() {
//		games = new ArrayList<AbstractGame>();
//		games.add(new PickANumber(bot));
	}

	public void process(Message message) {

	}
}