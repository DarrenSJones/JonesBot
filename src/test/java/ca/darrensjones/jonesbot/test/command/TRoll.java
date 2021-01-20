package ca.darrensjones.jonesbot.test.command;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.command.CommandRoll;
import ca.darrensjones.jonesbot.testcore.TBot;

/**
 * @author Darren Jones
 * @version 1.1.3 2021-01-19
 * @since 1.0.2 2020-12-22
 */
public class TRoll {

	@Test
	public void process() {
		CommandRoll command = new CommandRoll(TBot.getBot());

		// No Parameters
		Assert.assertTrue(command.process("!roll").matches("^Roll Total:\\[[1-6]\\], 1d6:\\[[1-6]\\]$"));

		// Parameter - Single Die Maximum
		Assert.assertTrue(command.process("!roll 1").matches("^Roll Total:\\[1\\], 1d1:\\[1\\]$"));
		Assert.assertTrue(command.process("!roll 9").matches("^Roll Total:\\[[1-9]\\], 1d9:\\[[1-9]\\]$"));
		Assert.assertTrue(command.process("!roll 10").matches("^Roll Total:\\[1?\\d\\], 1d10:\\[1?\\d\\]$"));
		Assert.assertTrue(command.process("!roll 99").matches("^Roll Total:\\[\\d?\\d\\], 1d99:\\[\\d?\\d\\]$"));
		Assert.assertTrue(command.process("!roll 100").matches("^Roll Total:\\[1?\\d?\\d\\], 1d100:\\[1?\\d?\\d\\]$"));

		// Parameter - Defined Die
		Assert.assertEquals(command.process("!roll 1d1"), "Roll Total:[1], 1d1:[1]");
		Assert.assertEquals(command.process("!roll 9d1"), "Roll Total:[9], 9d1:[1, 1, 1, 1, 1, 1, 1, 1, 1]");
		Assert.assertTrue(command.process("!roll 1d1").matches("^Roll Total:\\[1\\], 1d1:\\[1\\]$"));
		Assert.assertTrue(command.process("!roll 2d1").matches("^Roll Total:\\[2\\], 2d1:\\[1, 1\\]$"));
		Assert.assertTrue(command.process("!roll 9d1").matches("^Roll Total:\\[9\\], 9d1:\\[1, 1, 1, 1, 1, 1, 1, 1, 1\\]$"));
		Assert.assertTrue(command.process("!roll 1d9").matches("^Roll Total:\\[[1-9]\\], 1d9:\\[[1-9]\\]$"));
		Assert.assertTrue(command.process("!roll 9d9").matches("^Roll Total:\\[[1-8]?\\d\\], 9d9:\\[\\d, \\d, \\d, \\d, \\d, \\d, \\d, \\d, \\d\\]$"));
		Assert.assertTrue(command.process("!roll 1d100").matches("^Roll Total:\\[1?\\d?\\d\\], 1d100:\\[1?\\d?\\d\\]$"));

		// Parameter - Defined Dice
		Assert.assertTrue(command.process("!roll 1d1 1d2 1d3").matches("^Roll Total:\\[[3-6]\\], 1d1:\\[1\\], 1d2:\\[[1-2]\\], 1d3:\\[[1-3]\\]$"));
		Assert.assertTrue(command.process("!roll 3d1 3d2").matches("^Roll Total:\\[[6-9]\\], 3d1:\\[1, 1, 1\\], 3d2:\\[[1-2], [1-2], [1-2]\\]$"));
	}

	@Test(dependsOnMethods = "process", alwaysRun = true)
	public void roll() {
		CommandRoll command = new CommandRoll(TBot.getBot());

		Assert.assertEquals(command.roll(1, 1), 1);
		Assert.assertEquals(command.roll(5, 5), 5);
		Assert.assertEquals(command.roll(10, 10), 10);

		Assert.assertTrue(Integer.toString(command.roll(1)).matches("^1$"));
		Assert.assertTrue(Integer.toString(command.roll(9)).matches("^[1-9]$"));
		Assert.assertTrue(Integer.toString(command.roll(10)).matches("^1?\\d$"));
		Assert.assertTrue(Integer.toString(command.roll(99)).matches("^\\d?\\d$"));
		Assert.assertTrue(Integer.toString(command.roll(100)).matches("^1?\\d?\\d$"));

		// Exception is thrown if Max < Min
		Assert.assertNotNull(command.roll(0, 1));
		try {
			command.roll(1, 0);
			Assert.assertTrue(false); // This will fail if the exception isn't thrown
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
	}
}