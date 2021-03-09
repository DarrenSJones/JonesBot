package ca.darrensjones.jonesbot.test.command;

import ca.darrensjones.jonesbot.command.CommandRoll;
import ca.darrensjones.jonesbot.testcore.TBot;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-03-09
 * @since   1.0.2 2020-12-22
 */
public class CommandRollTest {

	@Test
	public void process() {
		CommandRoll c = new CommandRoll(TBot.getBot());

		// No parameters
		Assert.assertTrue(c.process("!roll").matches("^Roll Total:\\[[1-6]\\], 1d6:\\[[1-6]\\]$"));

		// Parameter - Single die maximum
		Assert.assertTrue(c.process("!roll 1").matches("^Roll Total:\\[1\\], 1d1:\\[1\\]$"));
		Assert.assertTrue(
				c.process("!roll 9").matches("^Roll Total:\\[[1-9]\\], 1d9:\\[[1-9]\\]$"));
		Assert.assertTrue(
				c.process("!roll 10").matches("^Roll Total:\\[1?\\d\\], 1d10:\\[1?\\d\\]$"));
		Assert.assertTrue(
				c.process("!roll 99").matches("^Roll Total:\\[\\d?\\d\\], 1d99:\\[\\d?\\d\\]$"));
		Assert.assertTrue(c.process("!roll 100")
				.matches("^Roll Total:\\[1?\\d?\\d\\], 1d100:\\[1?\\d?\\d\\]$"));

		// Parameter - Defined die
		Assert.assertEquals(c.process("!roll 1d1"), "Roll Total:[1], 1d1:[1]");
		Assert.assertEquals(c.process("!roll 9d1"),
				"Roll Total:[9], 9d1:[1, 1, 1, 1, 1, 1, 1, 1, 1]");
		Assert.assertTrue(c.process("!roll 1d1").matches("^Roll Total:\\[1\\], 1d1:\\[1\\]$"));
		Assert.assertTrue(c.process("!roll 2d1").matches("^Roll Total:\\[2\\], 2d1:\\[1, 1\\]$"));
		Assert.assertTrue(c.process("!roll 9d1")
				.matches("^Roll Total:\\[9\\], 9d1:\\[1, 1, 1, 1, 1, 1, 1, 1, 1\\]$"));
		Assert.assertTrue(c.process("!roll 10d1")
				.matches("^Roll Total:\\[10\\], 10d1:\\[1, 1, 1, 1, 1, 1, 1, 1, 1, 1\\]$"));
		Assert.assertTrue(
				c.process("!roll 1d9").matches("^Roll Total:\\[[1-9]\\], 1d9:\\[[1-9]\\]$"));
		Assert.assertTrue(c.process("!roll 9d9").matches(
				"^Roll Total:\\[[1-8]?\\d\\], 9d9:\\[\\d, \\d, \\d, \\d, \\d, \\d, \\d, \\d, \\d\\]$"));
		Assert.assertTrue(c.process("!roll 1d100")
				.matches("^Roll Total:\\[1?\\d?\\d\\], 1d100:\\[1?\\d?\\d\\]$"));

		// Parameter - Defined dice
		Assert.assertTrue(c.process("!roll 1d1 1d2 1d3").matches(
				"^Roll Total:\\[[3-6]\\], 1d1:\\[1\\], 1d2:\\[[1-2]\\], 1d3:\\[[1-3]\\]$"));
		Assert.assertTrue(c.process("!roll 3d1 3d2").matches(
				"^Roll Total:\\[[6-9]\\], 3d1:\\[1, 1, 1\\], 3d2:\\[[1-2], [1-2], [1-2]\\]$"));
	}

	@Test(dependsOnMethods = "process", alwaysRun = true)
	public void roll() {
		CommandRoll c = new CommandRoll(TBot.getBot());

		Assert.assertEquals(c.roll(1, 1), 1);
		Assert.assertEquals(c.roll(5, 5), 5);
		Assert.assertEquals(c.roll(10, 10), 10);

		Assert.assertTrue(Integer.toString(c.roll(1)).matches("^1$"));
		Assert.assertTrue(Integer.toString(c.roll(9)).matches("^[1-9]$"));
		Assert.assertTrue(Integer.toString(c.roll(10)).matches("^1?\\d$"));
		Assert.assertTrue(Integer.toString(c.roll(99)).matches("^\\d?\\d$"));
		Assert.assertTrue(Integer.toString(c.roll(100)).matches("^1?\\d?\\d$"));

		// Exception is thrown if max < min
		Assert.assertNotNull(c.roll(0, 1));
		try {
			c.roll(1, 0);
			Assert.assertTrue(false); // This will fail if the exception isn't thrown
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
	}
}