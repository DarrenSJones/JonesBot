package ca.darrensjones.jonesbot.test.command;

import java.awt.Color;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.command.utilities.Frinkiac;
import ca.darrensjones.jonesbot.db.controller.CFrinkiacSaved;
import ca.darrensjones.jonesbot.db.model.OFrinkiacSaved;
import ca.darrensjones.jonesbot.testcore.TestUtils;
import net.dv8tion.jda.api.entities.MessageEmbed;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-12-13
 * @since 1.0.0 2020-12-09
 */
public class TFrinkiac {

	@Test
	public void getHelp() {
		Assert.assertEquals(Frinkiac.getHelp("!"),
				"Blank commands will display a random image." + "\nEntering a quote will return an image associated with that quote."
						+ "\n\"S00E00 0000\" can also be used to search for a specific episode and timestamp."
						+ "\n**!s {text-search}**: Display an image using the search." + "\n**!s !d**: Display a random image, with detail."
						+ "\n**!s !d {text-search}**: Display an image using the search, with detail."
						+ "\n**!s !l**: Displays details for the last image posted." + "\n**!s !saved**: Displays a list of saved images with links."
						+ "\n**!s !regex**: Displays a list of saved images with the regex that matches them."
						+ "\nAny text entered on a new line will be added to the image as a caption.");
	}

	@Test(dependsOnMethods = "getHelp", alwaysRun = true)
	public void buildEmbed() {

		// Image and Detail
		String response1 = TestUtils.readFile("src/test/resources/mock/frinkiac/timestamp_blank.json");
		MessageEmbed me1 = Frinkiac.buildEmbed(true, true, new Color(123, 123, 123), "http://host.dom", "Title", response1, "testencode").build();
		Assert.assertEquals(me1.getImage().getUrl(), "http://host.dom/meme/S00E00/0.jpg?b64lines=dGVzdGVuY29kZQ==");
		Assert.assertEquals(me1.getTitle(), "Title");
		Assert.assertEquals(me1.getUrl(), "http://host.dom/caption/S00E00/0");
		Assert.assertEquals(me1.getDescription(), "\"x\"");
		Assert.assertEquals(me1.getFields().size(), 1);
		Assert.assertEquals(me1.getFields().get(0).getName(), "Season 0 / Episode 0 (00:00)");
		Assert.assertEquals(me1.getFields().get(0).getValue(), "\u200Bx");

		// Image, no Detail
		String response2 = TestUtils.readFile("src/test/resources/mock/frinkiac/timestamp_blank.json");
		MessageEmbed me2 = Frinkiac.buildEmbed(true, false, new Color(123, 123, 123), "http://host.dom", "Title", response2, "testencode").build();
		Assert.assertEquals(me2.getImage().getUrl(), "http://host.dom/meme/S00E00/0.jpg?b64lines=dGVzdGVuY29kZQ==");
		Assert.assertEquals(me2.getTitle(), null);
		Assert.assertEquals(me2.getUrl(), null);
		Assert.assertEquals(me2.getDescription(), null);
		Assert.assertEquals(me2.getFields().size(), 0);

		// Detail, no Image
		String response3 = TestUtils.readFile("src/test/resources/mock/frinkiac/timestamp_blank.json");
		MessageEmbed me3 = Frinkiac.buildEmbed(false, true, new Color(123, 123, 123), "http://host.dom", "Title", response3, "testencode").build();
		Assert.assertEquals(me3.getImage(), null);
		Assert.assertEquals(me3.getTitle(), "Title");
		Assert.assertEquals(me3.getUrl(), "http://host.dom/caption/S00E00/0");
		Assert.assertEquals(me3.getDescription(), "\"x\"");
		Assert.assertEquals(me3.getFields().size(), 1);
		Assert.assertEquals(me3.getFields().get(0).getName(), "Season 0 / Episode 0 (00:00)");
		Assert.assertEquals(me3.getFields().get(0).getValue(), "\u200Bx");

		// No Image, no Detail - Should never happen
		String response4 = TestUtils.readFile("src/test/resources/mock/frinkiac/timestamp_blank.json");
		MessageEmbed me4 = Frinkiac.buildEmbed(false, false, new Color(123, 123, 123), "http://host.dom", "Title", response4, "testencode").build();
		Assert.assertEquals(me4.getImage(), null);
		Assert.assertEquals(me4.getTitle(), null);
		Assert.assertEquals(me4.getUrl(), null);
		Assert.assertEquals(me4.getDescription(), null);
		Assert.assertEquals(me4.getFields().size(), 0);

		// No Subtitles
		String response5 = TestUtils.readFile("src/test/resources/mock/frinkiac/timestamp_blank_no_subtitles.json");
		MessageEmbed me5 = Frinkiac.buildEmbed(true, true, new Color(123, 123, 123), "http://host.dom", "Title", response5, "testencode").build();
		Assert.assertEquals(me5.getImage().getUrl(), "http://host.dom/meme/S00E00/0.jpg?b64lines=dGVzdGVuY29kZQ==");
		Assert.assertEquals(me5.getTitle(), "Title");
		Assert.assertEquals(me5.getUrl(), "http://host.dom/caption/S00E00/0");
		Assert.assertEquals(me5.getDescription(), "\"x\"");
		Assert.assertEquals(me5.getFields().size(), 1);
		Assert.assertEquals(me5.getFields().get(0).getName(), "Season 0 / Episode 0 (00:00)");
		Assert.assertEquals(me5.getFields().get(0).getValue(), "\u200B");

		// Large Response
		String response6 = TestUtils.readFile("src/test/resources/mock/frinkiac/timestamp_large.json");
		MessageEmbed me6 = Frinkiac.buildEmbed(true, true, new Color(123, 123, 123), "http://host.dom", "Title", response6, "testencode").build();
		Assert.assertEquals(me6.getImage().getUrl(), "http://host.dom/meme/S99E99/9999999.jpg?b64lines=dGVzdGVuY29kZQ==");
		Assert.assertEquals(me6.getTitle(), "Title");
		Assert.assertEquals(me6.getUrl(), "http://host.dom/caption/S99E99/9999999");
		Assert.assertEquals(me6.getDescription(), "\"abcdefghijklmnopqretuvwxyzabcdefghijklmnopqretuvwxyzabcdefghijklmnopqretuvwxyzabc\"");
		Assert.assertEquals(me6.getFields().size(), 1);
		Assert.assertEquals(me6.getFields().get(0).getName(), "Season 99 / Episode 99 (166:39)");
		Assert.assertEquals(me6.getFields().get(0).getValue(),
				"\u200Babcdefghijklmnopqretuvwxyzabcdefghijklmnopqretuvwxyzabcdefghijklmnopqretuvwxyzabcdefghijkl"
						+ "\nabcdefghijklmnopqretuvwxyzabcdefghijklmnopqretuvwxyzabcdefghijklmnopqretuvwxyzabcdefghijkl"
						+ "\nabcdefghijklmnopqretuvwxyzabcdefghijklmnopqretuvwxyzabcdefghijklmnopqretuvwxyzabcdefghijkl"
						+ "\nabcdefghijklmnopqretuvwxyzabcdefghijklmnopqretuvwxyzabcdefghijklmnopqretuvwxyzabcdefghijkl"
						+ "\nabcdefghijklmnopqretuvwxyzabcdefghijklmnopqretuvwxyzabcdefghijklmnopqretuvwxyzabcdefghijkl"
						+ "\nabcdefghijklmnopqretuvwxyzabcdefghijklmnopqretuvwxyzabcdefghijklmnopqretuvwxyzabcdefghijkl"
						+ "\nabcdefghijklmnopqretuvwxyzabcdefghijklmnopqretuvwxyzabcdefghijklmnopqretuvwxyzabcdefghijkl");

		// Special Characters
		String response7 = TestUtils.readFile("src/test/resources/mock/frinkiac/timestamp_special.json");
		MessageEmbed me7 = Frinkiac.buildEmbed(true, true, new Color(123, 123, 123), "http://host.dom", "Title", response7, "testencode").build();
		Assert.assertEquals(me7.getImage().getUrl(), "http://host.dom/meme/Movie/0.jpg?b64lines=dGVzdGVuY29kZQ==");
		Assert.assertEquals(me7.getTitle(), "Title");
		Assert.assertEquals(me7.getUrl(), "http://host.dom/caption/Movie/0");
		Assert.assertEquals(me7.getDescription(), "\".,:'-?!#$%&\\ö()\"");
		Assert.assertEquals(me7.getFields().size(), 1);
		Assert.assertEquals(me7.getFields().get(0).getName(), "Season 0 / Episode 0 (00:00)");
		Assert.assertEquals(me7.getFields().get(0).getValue(), "\u200B.,:'-?!$%ö[]♪áé");
	}

	@Test(dependsOnMethods = "buildEmbed", alwaysRun = true)
	public void buildEmbedSaved() {
		MessageEmbed embed = Frinkiac.buildEmbedSaved(new Color(123, 123, 123), "host", CFrinkiacSaved.getById("1")).build();
		Assert.assertEquals(embed.getTitle(), "Saved List");
		Assert.assertEquals(embed.getDescription(),
				"Contact your Admin for additions:" + "\nTrash [host/caption/S03E22/937738]" + "\nFlag [host/caption/S14E03/883966]"
						+ "\nPig [host/caption/Movie/1321236]" + "\nCatholic Church [host/caption/S10E12/927876]" + "\nThe Anvil [host/caption/S08E15/856087]");
		Assert.assertEquals(embed.getColor(), new Color(123, 123, 123));
	}

	@Test(dependsOnMethods = "buildEmbedSaved", alwaysRun = true)
	public void buildEmbedRegex() {
		MessageEmbed embed = Frinkiac.buildEmbedRegex(new Color(123, 123, 123), "host", CFrinkiacSaved.getById("1")).build();
		Assert.assertEquals(embed.getTitle(), "Saved List: Regex");
		Assert.assertEquals(embed.getDescription(),
				"Contact your Admin for additions:" + "\nTrash: trash" + "\nFlag: flags?" + "\nPig: (spider )?pig"
						+ "\nCatholic Church: (catholic( church)?)|(we'?ve made a few changes)"
						+ "\nThe Anvil: (the anvil)|(gay steel mill)|(we work hard\\,? we play hard)");
		Assert.assertEquals(embed.getColor(), new Color(123, 123, 123));
	}

	@Test(dependsOnMethods = "buildEmbedRegex", alwaysRun = true)
	public void hasSaved() {
		List<OFrinkiacSaved> list = CFrinkiacSaved.getById("1");

		// Casing
		Assert.assertTrue(Frinkiac.hasSaved(list, "flag"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "flags"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "Flag"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "Flags"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "FlaG"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "FlagS"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "FLAG"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "FLAGS"));

		// Spacing
		Assert.assertTrue(Frinkiac.hasSaved(list, "flag "));
		Assert.assertTrue(Frinkiac.hasSaved(list, " flag"));
		Assert.assertTrue(Frinkiac.hasSaved(list, " flag "));
		Assert.assertTrue(Frinkiac.hasSaved(list, "flag a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a flag"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a flag a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "flags "));
		Assert.assertTrue(Frinkiac.hasSaved(list, " flags"));
		Assert.assertTrue(Frinkiac.hasSaved(list, " flags "));
		Assert.assertTrue(Frinkiac.hasSaved(list, "flags a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a flags"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a flags a"));

		// Special Characters
		Assert.assertTrue(Frinkiac.hasSaved(list, "!flag!"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a!flag!a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "@flag@"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a@flag@a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "#flag#"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a#flag#a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "$flag$"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a$flag$a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "%flag%"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a%flag%a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "^flag^"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a^flag^a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "&flag&"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a&flag&a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "*flag*"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a*flag*a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "(flag("));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a(flag(a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, ")flag)"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a)flag)a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "-flag-"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a-flag-a"));
		Assert.assertFalse(Frinkiac.hasSaved(list, "_flag_"));
		Assert.assertFalse(Frinkiac.hasSaved(list, "a_flag_a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "=flag="));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a=flag=a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "+flag+"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a+flag+a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "[flag["));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a[flag[a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "]flag]"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a]flag]a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "{flag{"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a{flag{a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "}flag}"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a}flag}a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "\\flag\\"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a\\flag\\a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "|flag|"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a|flag|a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, ";flag;"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a;flag;a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, ":flag:"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a:flag:a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "'flag'"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a'flag'a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "\"flag\""));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a\"flag\"a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, ",flag,"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a,flag,a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "<flag<"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a<flag<a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, ".flag."));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a.flag.a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, ">flag>"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a>flag>a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "/flag/"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a/flag/a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "?flag?"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a?flag?a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "😊flag😊"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a😊flag😊a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "!flags!"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a!flags!a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "@flags@"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a@flags@a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "#flags#"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a#flags#a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "$flags$"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a$flags$a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "%flags%"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a%flags%a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "^flags^"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a^flags^a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "&flags&"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a&flags&a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "*flags*"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a*flags*a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "(flags("));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a(flags(a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, ")flags)"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a)flags)a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "-flags-"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a-flags-a"));
		Assert.assertFalse(Frinkiac.hasSaved(list, "_flags_"));
		Assert.assertFalse(Frinkiac.hasSaved(list, "a_flags_a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "=flags="));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a=flags=a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "+flags+"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a+flags+a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "[flags["));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a[flags[a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "]flags]"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a]flags]a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "{flags{"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a{flags{a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "}flags}"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a}flags}a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "\\flags\\"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a\\flags\\a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "|flags|"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a|flags|a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, ";flags;"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a;flags;a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, ":flags:"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a:flags:a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "'flags'"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a'flags'a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "\"flags\""));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a\"flags\"a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, ",flags,"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a,flags,a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "<flags<"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a<flags<a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, ".flags."));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a.flags.a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, ">flags>"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a>flags>a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "/flags/"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a/flags/a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "?flags?"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a?flags?a"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "😊flags😊"));
		Assert.assertTrue(Frinkiac.hasSaved(list, "a😊flags😊a"));

		// Negative Tests
		Assert.assertFalse(Frinkiac.hasSaved(list, "test"));
		Assert.assertFalse(Frinkiac.hasSaved(list, "s"));
		Assert.assertFalse(Frinkiac.hasSaved(list, "sandwic"));
		Assert.assertFalse(Frinkiac.hasSaved(list, "sandwiche"));
		Assert.assertFalse(Frinkiac.hasSaved(list, "asandwich"));
		Assert.assertFalse(Frinkiac.hasSaved(list, "sandwicha"));
		Assert.assertFalse(Frinkiac.hasSaved(list, "asandwicha"));
		Assert.assertFalse(Frinkiac.hasSaved(list, "asandwiches"));
		Assert.assertFalse(Frinkiac.hasSaved(list, "sandwichesa"));
		Assert.assertFalse(Frinkiac.hasSaved(list, "asandwichesa"));
		Assert.assertFalse(Frinkiac.hasSaved(list, "0sandwich"));
		Assert.assertFalse(Frinkiac.hasSaved(list, "sandwich0"));
		Assert.assertFalse(Frinkiac.hasSaved(list, "0sandwich0"));
		Assert.assertFalse(Frinkiac.hasSaved(list, "0sandwiches"));
		Assert.assertFalse(Frinkiac.hasSaved(list, "sandwiches0"));
		Assert.assertFalse(Frinkiac.hasSaved(list, "0sandwiches0"));
	}

	@Test(dependsOnMethods = "hasSaved", alwaysRun = true)
	public void getSaved() {
		List<OFrinkiacSaved> list = CFrinkiacSaved.getById("1");

		// No Regex
		Assert.assertEquals(Frinkiac.getSaved(list, "trash").name, "Trash");
		Assert.assertEquals(Frinkiac.getSaved(list, "trash").regex, "trash");
		Assert.assertEquals(Frinkiac.getSaved(list, "trash").key, "S03E22");
		Assert.assertEquals(Frinkiac.getSaved(list, "trash").timestamp, "937738");

		// No Regex Movie
		Assert.assertEquals(Frinkiac.getSaved(list, "pig").name, "Pig");
		Assert.assertEquals(Frinkiac.getSaved(list, "pig").regex, "(spider )?pig");
		Assert.assertEquals(Frinkiac.getSaved(list, "pig").key, "Movie");
		Assert.assertEquals(Frinkiac.getSaved(list, "pig").timestamp, "1321236");

		// Simple Regex
		Assert.assertEquals(Frinkiac.getSaved(list, "flag").name, "Flag");
		Assert.assertEquals(Frinkiac.getSaved(list, "flag").regex, "flags?");
		Assert.assertEquals(Frinkiac.getSaved(list, "flag").key, "S14E03");
		Assert.assertEquals(Frinkiac.getSaved(list, "flag").timestamp, "883966");

		// Complex Regex
		Assert.assertEquals(Frinkiac.getSaved(list, "catholic").name, "Catholic Church");
		Assert.assertEquals(Frinkiac.getSaved(list, "catholic").regex, "(catholic( church)?)|(we'?ve made a few changes)");
		Assert.assertEquals(Frinkiac.getSaved(list, "catholic").key, "S10E12");
		Assert.assertEquals(Frinkiac.getSaved(list, "catholic").timestamp, "927876");
		Assert.assertEquals(Frinkiac.getSaved(list, "catholic church").name, "Catholic Church");
		Assert.assertEquals(Frinkiac.getSaved(list, "catholic     church").name, "Catholic Church");
		Assert.assertEquals(Frinkiac.getSaved(list, "weve made a few changes").name, "Catholic Church");
	}

	@Test(dependsOnMethods = "getSaved", alwaysRun = true)
	public void isKeyTimestamp() {
		Assert.assertTrue(Frinkiac.isKeyTimestamp("s1e1 1"));
		Assert.assertTrue(Frinkiac.isKeyTimestamp("s1E1 1"));
		Assert.assertTrue(Frinkiac.isKeyTimestamp("S1e1 1"));
		Assert.assertTrue(Frinkiac.isKeyTimestamp("S1E1 1"));
		Assert.assertTrue(Frinkiac.isKeyTimestamp("s01e1 1"));
		Assert.assertTrue(Frinkiac.isKeyTimestamp("s01E1 1"));
		Assert.assertTrue(Frinkiac.isKeyTimestamp("S01e1 1"));
		Assert.assertTrue(Frinkiac.isKeyTimestamp("S01E1 1"));
		Assert.assertTrue(Frinkiac.isKeyTimestamp("s1e01 1"));
		Assert.assertTrue(Frinkiac.isKeyTimestamp("s1E01 1"));
		Assert.assertTrue(Frinkiac.isKeyTimestamp("S1e01 1"));
		Assert.assertTrue(Frinkiac.isKeyTimestamp("S1E01 1"));
		Assert.assertTrue(Frinkiac.isKeyTimestamp("s01e01 1"));
		Assert.assertTrue(Frinkiac.isKeyTimestamp("s01E01 1"));
		Assert.assertTrue(Frinkiac.isKeyTimestamp("S01e01 1"));
		Assert.assertTrue(Frinkiac.isKeyTimestamp("S01E01 1"));
		Assert.assertTrue(Frinkiac.isKeyTimestamp("S99E99 9999999"));
		Assert.assertTrue(Frinkiac.isKeyTimestamp("movie 1"));
		Assert.assertTrue(Frinkiac.isKeyTimestamp("Movie 1"));
		Assert.assertTrue(Frinkiac.isKeyTimestamp("moviE 1"));
		Assert.assertTrue(Frinkiac.isKeyTimestamp("MOVIE 1"));
		Assert.assertTrue(Frinkiac.isKeyTimestamp("movie 9999999"));
		Assert.assertTrue(Frinkiac.isKeyTimestamp("Movie 9999999"));
		Assert.assertTrue(Frinkiac.isKeyTimestamp("moviE 9999999"));
		Assert.assertTrue(Frinkiac.isKeyTimestamp("MOVIE 9999999"));
		Assert.assertFalse(Frinkiac.isKeyTimestamp("z1e1 1"));
		Assert.assertFalse(Frinkiac.isKeyTimestamp("s1C1 1"));
		Assert.assertFalse(Frinkiac.isKeyTimestamp("s1e1 l"));
		Assert.assertFalse(Frinkiac.isKeyTimestamp("s1 e1 1"));
		Assert.assertFalse(Frinkiac.isKeyTimestamp("s1e1"));
		Assert.assertFalse(Frinkiac.isKeyTimestamp("movie"));
	}

	@Test(dependsOnMethods = "isKeyTimestamp", alwaysRun = true)
	public void buildRequestUrlKeyTimestamp() {
		Assert.assertEquals(Frinkiac.buildRequestUrlKeyTimestamp("host", "s1e1", "1"), "host/api/caption?e=S01E01&t=1");
		Assert.assertEquals(Frinkiac.buildRequestUrlKeyTimestamp("host", "s1E1", "1"), "host/api/caption?e=S01E01&t=1");
		Assert.assertEquals(Frinkiac.buildRequestUrlKeyTimestamp("host", "S1e1", "1"), "host/api/caption?e=S01E01&t=1");
		Assert.assertEquals(Frinkiac.buildRequestUrlKeyTimestamp("host", "S1E1", "1"), "host/api/caption?e=S01E01&t=1");
		Assert.assertEquals(Frinkiac.buildRequestUrlKeyTimestamp("host", "s01e1", "1"), "host/api/caption?e=S01E01&t=1");
		Assert.assertEquals(Frinkiac.buildRequestUrlKeyTimestamp("host", "s01E1", "1"), "host/api/caption?e=S01E01&t=1");
		Assert.assertEquals(Frinkiac.buildRequestUrlKeyTimestamp("host", "S01e1", "1"), "host/api/caption?e=S01E01&t=1");
		Assert.assertEquals(Frinkiac.buildRequestUrlKeyTimestamp("host", "S01E1", "1"), "host/api/caption?e=S01E01&t=1");
		Assert.assertEquals(Frinkiac.buildRequestUrlKeyTimestamp("host", "s1e01", "1"), "host/api/caption?e=S01E01&t=1");
		Assert.assertEquals(Frinkiac.buildRequestUrlKeyTimestamp("host", "s1E01", "1"), "host/api/caption?e=S01E01&t=1");
		Assert.assertEquals(Frinkiac.buildRequestUrlKeyTimestamp("host", "S1e01", "1"), "host/api/caption?e=S01E01&t=1");
		Assert.assertEquals(Frinkiac.buildRequestUrlKeyTimestamp("host", "S1E01", "1"), "host/api/caption?e=S01E01&t=1");
		Assert.assertEquals(Frinkiac.buildRequestUrlKeyTimestamp("host", "s01e01", "1"), "host/api/caption?e=S01E01&t=1");
		Assert.assertEquals(Frinkiac.buildRequestUrlKeyTimestamp("host", "s01E01", "1"), "host/api/caption?e=S01E01&t=1");
		Assert.assertEquals(Frinkiac.buildRequestUrlKeyTimestamp("host", "S01e01", "1"), "host/api/caption?e=S01E01&t=1");
		Assert.assertEquals(Frinkiac.buildRequestUrlKeyTimestamp("host", "S01E01", "1"), "host/api/caption?e=S01E01&t=1");
		Assert.assertEquals(Frinkiac.buildRequestUrlKeyTimestamp("host", "S99E99", "9999999"), "host/api/caption?e=S99E99&t=9999999");
		Assert.assertEquals(Frinkiac.buildRequestUrlKeyTimestamp("host", "movie", "1"), "host/api/caption?e=Movie&t=1");
		Assert.assertEquals(Frinkiac.buildRequestUrlKeyTimestamp("host", "Movie", "1"), "host/api/caption?e=Movie&t=1");
		Assert.assertEquals(Frinkiac.buildRequestUrlKeyTimestamp("host", "moviE", "1"), "host/api/caption?e=Movie&t=1");
		Assert.assertEquals(Frinkiac.buildRequestUrlKeyTimestamp("host", "MOVIE", "1"), "host/api/caption?e=Movie&t=1");
		Assert.assertEquals(Frinkiac.buildRequestUrlKeyTimestamp("host", "movie", "9999999"), "host/api/caption?e=Movie&t=9999999");
	}
}