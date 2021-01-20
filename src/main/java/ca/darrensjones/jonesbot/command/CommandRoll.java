package ca.darrensjones.jonesbot.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import ca.darrensjones.jonesbot.log.Reporter;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.1.3 2021-01-19
 * @since 1.1.0 2020-12-22
 */
public class CommandRoll extends AbstractCommand {

	Random rand = new Random();

	public CommandRoll(Bot bot) {
		super(bot);
	}

	@Override
	public String getName() {
		return "Roll";
	}

	@Override
	public String getDescription() {
		return "Roll some dice!";
	}

	@Override
	public String[] getTriggers() {
		return new String[] { "roll", "r" };
	}

	@Override
	public CommandVisibility visibility() {
		return CommandVisibility.PUBLIC;
	}

	@Override
	public String getHelp() {
		String p = bot.getPrefix();
		String output = "**" + p + "roll** Rolls a 6-sided die.";
		output += "\n**" + p + "roll {sides}** Rolls a die with the given number of sides.";
		output += "\n**" + p + "roll {amount}d{sides}** Rolls a given die the given amount (eg. 2d6).";
		return output;
	}

	@Override
	public void execute(Message message) {
		String output = process(message.getContentDisplay());
		Reporter.info(output);
		message.getChannel().sendMessage(output).queue();
	}

	public String process(String content) {

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(); // Dice faces, amount to roll
		HashMap<Integer, List<Integer>> results = new HashMap<Integer, List<Integer>>(); // Dice faces, roll results

		Pattern pDice = Pattern.compile("\\d+(d|D)\\d+");
		Matcher mMaxSides = Pattern.compile("\\s+\\d+$").matcher(content);

		// HashMap contains all dice to roll, and the amount of times to roll for each
		try {
			if (pDice.matcher(content).find()) {
				Matcher mDice = pDice.matcher(content);
				while (mDice.find()) {
					int amount = Integer.parseInt(mDice.group().split("(d|D)")[0]);
					int sides = Integer.parseInt(mDice.group().split("(d|D)")[1]);
					if (map.containsKey(sides)) amount += map.get(sides);
					map.put(sides, amount);
				}

			} else if (mMaxSides.find()) {
				map.put(Integer.parseInt(mMaxSides.group().trim()), 1);

			} else {
				map.put(6, 1);
			}

		} catch (IllegalArgumentException e) {
			return "Roll: Maximum must be greater than Minimum.";
		}

		// Iterates through the HashMap for each die
		int total = 0;
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			List<Integer> list = new ArrayList<Integer>();
			int sides = entry.getKey();
			for (int i = 0; i < entry.getValue(); i++) {
				int roll = roll(sides);
				list.add(roll);
				total += roll;
			}
			results.put(sides, list);
		}

		String output = String.format("Roll Total:[%s]", total);
		for (Map.Entry<Integer, List<Integer>> entry : results.entrySet()) {
			output += String.format(", %sd%s:%s", entry.getValue().size(), entry.getKey(), entry.getValue().toString());
		}

		return output;
	}

	/**
	 * @param max A positive number
	 * @return A random number between 1 and 'max'
	 * @throws IllegalArgumentException If 'max' is less than 1
	 */
	public int roll(int max) throws IllegalArgumentException {
		return roll(1, max);
	}

	/**
	 * @param min Must be less than 'max'
	 * @param max Must be greater than 'min'
	 * @return A random number between 1 and 'max'
	 * @throws IllegalArgumentException If 'max' is less than 1
	 */
	public int roll(int min, int max) throws IllegalArgumentException {
		return rand.nextInt(max - (min - 1)) + min;
	}
}