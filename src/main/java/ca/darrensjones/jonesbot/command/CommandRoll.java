package ca.darrensjones.jonesbot.command;

import java.util.ArrayList;
import java.util.List;
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
 * @version 1.1.0 2020-12-27
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
		return "**" + bot.config.BOT_PREFIX + "roll** Rolls a 6-sided die.";
	}

	@Override
	public void execute(Message message) {
		String output = process(message.getContentDisplay());
		Reporter.info(output);
		message.getChannel().sendMessage(output).queue();
	}

	public String process(String content) {
		String output = "";
		String diceMatch = "\\d+(d|D)\\d+";
		String maxMatch = "\\s+\\d+$";
		if (Pattern.compile(diceMatch).matcher(content).find()) {
			Matcher m = Pattern.compile(diceMatch).matcher(content);
			int total = 0;
			while (m.find()) {
				String s = m.group();
				int amount = Integer.parseInt(s.split("(d|D)")[0]);
				int sides = Integer.parseInt(s.split("(d|D)")[1]);
				List<Integer> t = new ArrayList<Integer>();
				for (int i = 0; i < amount; i++) {
					int r = roll(sides);
					t.add(r);
					total += r;
				}
				output += String.format(" %sd%s:%s", amount, sides, t.toString());
			}
			output = String.format("Roll Total:[%s]%s", total, output);
		} else if (Pattern.compile(maxMatch).matcher(content).find()) {
			Matcher m = Pattern.compile(maxMatch).matcher(content);
			if (m.find()) {
				int max = Integer.parseInt(m.group().trim());
				output = String.format("Roll 1d%s:[%s]", max, roll(max));
			}
		} else {
			int max = 6;
			output = String.format("Roll 1d%s:[%s]", max, roll(max));
		}
		return output;
	}

	public int roll(int max) throws IllegalArgumentException {
		return roll(max, 1);
	}

	public int roll(int max, int min) throws IllegalArgumentException {
		return rand.nextInt(max - (min - 1)) + min;
	}
}