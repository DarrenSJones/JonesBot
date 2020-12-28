package ca.darrensjones.jonesbot.test.command;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.command.CommandRoll;
import ca.darrensjones.jonesbot.testcore.BotTest;

/**
 * @author Darren Jones
 * @version 1.2.0 2020-12-27
 * @since 1.0.2 2020-12-22
 */
public class TRoll {

	@Test
	public void process() {

		CommandRoll command = new CommandRoll(BotTest.get());

		// No Parameters
		Assert.assertTrue(command.process("!roll").matches("^Roll 1d6:\\[[1-6]\\]$"));

		// Parameter - Maximum
		Assert.assertTrue(command.process("!roll 1").matches("^Roll 1d1:\\[[1]\\]$"));
		Assert.assertTrue(command.process("!roll 9").matches("^Roll 1d9:\\[[1-9]\\]$"));
		Assert.assertTrue(command.process("!roll 10").matches("^Roll 1d10:\\[[1]?[0-9]\\]$"));
		Assert.assertTrue(command.process("!roll 99").matches("^Roll 1d99:\\[[0-9]?[0-9]\\]$"));
		Assert.assertTrue(command.process("!roll 100").matches("^Roll 1d100:\\[1?[0-9]?[0-9]\\]$"));

		// Parameter - Dice
		Assert.assertEquals(command.process("!roll 1d1"), "Roll Total:[1] 1d1:[1]");
		Assert.assertTrue(command.process("!roll 1d1").matches("^Roll Total:\\[[1]\\] [1]d[1]:\\[[1]\\]$"));
		Assert.assertTrue(command.process("!roll 2d1").matches("^Roll Total:\\[[2]\\] [2]d[1]:\\[[1], [1]\\]$"));
		Assert.assertEquals(command.process("!roll 9d1"), "Roll Total:[9] 9d1:[1, 1, 1, 1, 1, 1, 1, 1, 1]");
		Assert.assertTrue(command.process("!roll 9d1").matches("^Roll Total:\\[[9]\\] [9]d[1]:\\[[1], [1], [1], [1], [1], [1], [1], [1], [1]\\]$"));
		Assert.assertTrue(command.process("!roll 1d9").matches("^Roll Total:\\[[1-9]\\] [1]d[9]:\\[[1-9]\\]$"));
		Assert.assertTrue(command.process("!roll 9d9").matches("^Roll Total:\\[[1-8]?[0-9]\\] [9]d[9]:\\[\\d, \\d, \\d, \\d, \\d, \\d, \\d, \\d, \\d\\]$"));
		Assert.assertTrue(command.process("!roll 1d100").matches("^Roll Total:\\[1?[0-9]?[0-9]\\] [1]d[1][0][0]:\\[1?[0-9]?[0-9]\\]$"));

		// Parameter - Many Dice
		Assert.assertTrue(command.process("!roll 1d1 1d2 1d3").matches("^Roll Total:\\[[3-6]\\] [1]d[1]:\\[[1]\\] [1]d[2]:\\[[1-2]\\] [1]d[3]:\\[[1-3]\\]$"));
	}
}