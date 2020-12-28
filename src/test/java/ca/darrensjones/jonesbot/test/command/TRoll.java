package ca.darrensjones.jonesbot.test.command;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.command.CommandRoll;
import ca.darrensjones.jonesbot.testcore.BotTest;

/**
 * @author Darren Jones
 * @version 1.1.0 2020-12-27
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
		Assert.assertEquals(command.process("!roll 9d1"), "Roll Total:[9] 9d1:[1, 1, 1, 1, 1, 1, 1, 1, 1]");
		Assert.assertTrue(command.process("!roll 1d1").matches("^Roll Total:\\[[1]\\] [1]d[1]:\\[[1]\\]$"));
		Assert.assertTrue(command.process("!roll 2d1").matches("^Roll Total:\\[[2]\\] [2]d[1]:\\[[1], [1]\\]$"));
		Assert.assertTrue(command.process("!roll 9d1").matches("^Roll Total:\\[[9]\\] [9]d[1]:\\[[1], [1], [1], [1], [1], [1], [1], [1], [1]\\]$"));
		Assert.assertTrue(command.process("!roll 1d9").matches("^Roll Total:\\[[1-9]\\] [1]d[9]:\\[[1-9]\\]$"));
		Assert.assertTrue(command.process("!roll 9d9").matches("^Roll Total:\\[[1-8]?[0-9]\\] [9]d[9]:\\[\\d, \\d, \\d, \\d, \\d, \\d, \\d, \\d, \\d\\]$"));
		Assert.assertTrue(command.process("!roll 1d100").matches("^Roll Total:\\[1?[0-9]?[0-9]\\] [1]d[1][0][0]:\\[1?[0-9]?[0-9]\\]$"));

		// Parameter - Many Dice
		Assert.assertTrue(command.process("!roll 1d1 1d2 1d3").matches("^Roll Total:\\[[3-6]\\] [1]d[1]:\\[[1]\\] [1]d[2]:\\[[1-2]\\] [1]d[3]:\\[[1-3]\\]$"));
		Assert.assertTrue(command.process("!roll 3d1 3d2").matches("^Roll Total:\\[[6-9]\\] [3]d[1]:\\[[1], [1], [1]\\] [3]d[2]:\\[[1-2], [1-2], [1-2]\\]$"));
	}

	@Test(dependsOnMethods = "process", alwaysRun = true)
	public void roll() {
		CommandRoll command = new CommandRoll(BotTest.get());

		Assert.assertEquals(command.roll(1, 1), 1);
		Assert.assertEquals(command.roll(5, 5), 5);
		Assert.assertEquals(command.roll(10, 10), 10);

		Assert.assertTrue(Integer.toString(command.roll(1)).matches("^[1]$"));
		Assert.assertTrue(Integer.toString(command.roll(9)).matches("^[1-9]$"));
		Assert.assertTrue(Integer.toString(command.roll(10)).matches("^[1]?[0-9]?$"));
		Assert.assertTrue(Integer.toString(command.roll(99)).matches("^[0-9]?[0-9]?$"));
		Assert.assertTrue(Integer.toString(command.roll(100)).matches("^1?[0-9]?[0-9]?$"));

		// Exception is thrown if Max < Min
		Assert.assertNotNull(command.roll(1, 0));
		try {
			command.roll(0, 1);
			Assert.assertTrue(false);
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
	}
}