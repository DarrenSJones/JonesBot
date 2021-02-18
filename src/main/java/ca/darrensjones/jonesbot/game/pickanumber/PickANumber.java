package ca.darrensjones.jonesbot.game.pickanumber;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.game.meta.AbstractGame;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-18
 * @since   1.1.2 2020-12-29
 */
public class PickANumber extends AbstractGame {

	/*-
	 * The Bot selects a number from 1-10, at random.
	 * The Bot posts an embed, asking the viewer to pick a number.
	 * The numbers will be represented by reaction emoji that the Bot will react to the embed with.
	 * Users can click on a number, casting their vote. The reaction will then be removed.
	 * Users can change their vote by selecting a different emoji reaction.
	 * After the time is up (or the initiator clicks the checkmark emoji), the results are shown.
	 * Winners are announced, losers are also announced to show the amount of players.
	 */

	private String[] numbers = { "one", "two", "three", "four", "five", "six", "seven", "eight",
			"nine", "ten" };

	public PickANumber(Bot bot) {
		super(bot);
	}

	@Override
	public String getName() {
		return "Pick A Number";
	}

	@Override
	public String getDescription() {
		return "Guess the number picked by the Bot!";
	}

	@Override
	public String[] getTriggers() {
		return new String[] { "pickanumber", "pick", "number" };
	}

	@Override
	public String help() {
		return String.format("Choose a number between %s and %s.", numbers[0],
				numbers[numbers.length - 1]);
	}
}