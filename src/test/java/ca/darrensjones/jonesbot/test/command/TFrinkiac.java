package ca.darrensjones.jonesbot.test.command;

import java.awt.Color;
import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.command.utilities.Frinkiac;
import ca.darrensjones.jonesbot.db.controller.CFrinkiacSaved;
import ca.darrensjones.jonesbot.db.model.OFrinkiacSaved;
import ca.darrensjones.jonesbot.testcore.Mock;
import ca.darrensjones.jonesbot.testcore.TBot;
import ca.darrensjones.jonesbot.testcore.TestUtils;
import net.dv8tion.jda.api.entities.MessageEmbed;

/**
 * @author Darren Jones
 * @version 1.1.3 2021-01-14
 * @since 1.0.0 2020-12-09
 */
public class TFrinkiac {

	@Test
	public void process() {
		Mock.reset();

		String path = "src/test/resources/mock/frinkiac/";
		String prefix = TBot.getPrefix();
		Color color = new Color(123, 123, 123);
		String host = TBot.getConfig().HOST_SIMPSONS;
		List<OFrinkiacSaved> saved = TBot.getBot().dataHandler.simpsonsSaved;
		HashMap<String, String[]> last = TBot.getBot().dataHandler.simpsonsLast;

		// Valid Timestamp
		Mock.setExpectation("GET", "/api/caption?e=S06E04&t=741423", 200, new File(path + "timestamp_bort.json"));
		MessageEmbed m1 = Frinkiac.process("1", "!s !d S06E04 741423\ntestencode", prefix, color, host, saved, last).build();
		Assert.assertEquals(m1.getImage().getUrl(), "http://localhost:1080/meme/S06E04/741423.jpg?b64lines=dGVzdGVuY29kZQ==");
		Assert.assertEquals(m1.getTitle(), "Timestamp: \"S06E04 741423\"");
		Assert.assertEquals(m1.getUrl(), "http://localhost:1080/caption/S06E04/741423");
		Assert.assertEquals(m1.getDescription(), "\"Itchy & Scratchy Land\"");
		Assert.assertEquals(m1.getFields().size(), 1);
		Assert.assertEquals(m1.getFields().get(0).getName(), "Season 6 / Episode 4 (12:21)");
		Assert.assertEquals(m1.getFields().get(0).getValue(), "\u200BMommy, buy me a license plate!\nNo. Come along, Bort.\nAre you talking to me?");

		// Valid Query
		Mock.setExpectation("GET", "/api/search?q=bort", 200, new File(path + "query_bort.json"));
		MessageEmbed m2 = Frinkiac.process("1", "!s !d bort\ntestencode", prefix, color, host, saved, last).build();
		Assert.assertEquals(m2.getImage().getUrl(), "http://localhost:1080/meme/S06E04/741423.jpg?b64lines=dGVzdGVuY29kZQ==");
		Assert.assertEquals(m2.getTitle(), "Search: \"bort\"");
		Assert.assertEquals(m2.getUrl(), "http://localhost:1080/caption/S06E04/741423");
		Assert.assertEquals(m2.getDescription(), "\"Itchy & Scratchy Land\"");
		Assert.assertEquals(m2.getFields().size(), 1);
		Assert.assertEquals(m2.getFields().get(0).getName(), "Season 6 / Episode 4 (12:21)");
		Assert.assertEquals(m2.getFields().get(0).getValue(), "\u200BMommy, buy me a license plate!\nNo. Come along, Bort.\nAre you talking to me?");

		// Valid Query Saved
		Mock.setExpectation("GET", "/api/caption?e=S03E22&t=937738", 200, new File(path + "timestamp_trash.json"));
		MessageEmbed m3 = Frinkiac.process("1", "!s !d trash\ntraaash", prefix, color, host, saved, last).build();
		Assert.assertEquals(m3.getImage().getUrl(), "http://localhost:1080/meme/S03E22/937738.jpg?b64lines=dHJhYWFzaA==");
		Assert.assertEquals(m3.getTitle(), "Saved: \"Trash\"");
		Assert.assertEquals(m3.getUrl(), "http://localhost:1080/caption/S03E22/937738");
		Assert.assertEquals(m3.getDescription(), "\"The Otto Show\"");
		Assert.assertEquals(m3.getFields().size(), 1);
		Assert.assertEquals(m3.getFields().get(0).getName(), "Season 3 / Episode 22 (15:37)");
		Assert.assertEquals(m3.getFields().get(0).getValue(), "\u200BSomebody up there likes me.\n( Otto groaning)");

		// Timestamp Not Found
		Mock.setExpectation("GET", "/api/caption?e=S06E04&t=123456", 200, new File(path + "timestamp_not_found.json"));
		MessageEmbed m4 = Frinkiac.process("1", "!s !d S06E04 123456\ntestencode", prefix, color, host, saved, last).build();
		Assert.assertEquals(m4.getImage(), null);
		Assert.assertEquals(m4.getTitle(), "Error: Timestamp: \"S06E04 123456\"");
		Assert.assertEquals(m4.getUrl(), "http://localhost:1080");
		Assert.assertEquals(m4.getDescription(), "Error parsing response, please contact your administrator.");
		Assert.assertEquals(m4.getFields().size(), 0);

		// Query Not Found
		Mock.setExpectation("GET", "/api/search?q=fakequery", 200, new File(path + "query_not_found.json"));
		MessageEmbed m5 = Frinkiac.process("1", "!s !d fakequery\ntestencode", prefix, color, host, saved, last).build();
		Assert.assertEquals(m5.getImage(), null);
		Assert.assertEquals(m5.getTitle(), "Search: \"fakequery\"");
		Assert.assertEquals(m5.getUrl(), "http://localhost:1080");
		Assert.assertEquals(m5.getDescription(), "Response not found, try another search.");
		Assert.assertEquals(m5.getFields().size(), 0);

		// Special Characters
		Mock.setExpectation("GET", "/api/caption?e=Movie&t=999999", 200, new File(path + "timestamp_special_characters.json"));
		MessageEmbed m6 = Frinkiac.process("1", "!s !d Movie 999999", prefix, color, host, saved, last).build();
		Assert.assertEquals(m6.getImage().getUrl(), "http://localhost:1080/meme/Movie/999999.jpg");
		Assert.assertEquals(m6.getTitle(), "Timestamp: \"Movie 999999\"");
		Assert.assertEquals(m6.getUrl(), "http://localhost:1080/caption/Movie/999999");
		Assert.assertEquals(m6.getDescription(), "\".,:'-?!#$%&\\�()\"");
		Assert.assertEquals(m6.getFields().size(), 1);
		Assert.assertEquals(m6.getFields().get(0).getName(), "Season 0 / Episode 0 (16:39)");
		Assert.assertEquals(m6.getFields().get(0).getValue(), "​.,:'-?!$%�[]?��");
	}

	@Test(dependsOnMethods = "process", alwaysRun = true)
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
		String response = TestUtils.readFile("src/test/resources/mock/frinkiac/timestamp_bort.json");

		// Image and Detail
		MessageEmbed m1 = Frinkiac.buildEmbed(true, true, new Color(123, 123, 123), "http://localhost:1080", "Title", response, "testencode").build();
		Assert.assertEquals(m1.getImage().getUrl(), "http://localhost:1080/meme/S06E04/741423.jpg?b64lines=dGVzdGVuY29kZQ==");
		Assert.assertEquals(m1.getTitle(), "Title");
		Assert.assertEquals(m1.getUrl(), "http://localhost:1080/caption/S06E04/741423");
		Assert.assertEquals(m1.getDescription(), "\"Itchy & Scratchy Land\"");
		Assert.assertEquals(m1.getFields().size(), 1);
		Assert.assertEquals(m1.getFields().get(0).getName(), "Season 6 / Episode 4 (12:21)");
		Assert.assertEquals(m1.getFields().get(0).getValue(), "\u200BMommy, buy me a license plate!\nNo. Come along, Bort.\nAre you talking to me?");

		// Image, No Detail
		MessageEmbed m2 = Frinkiac.buildEmbed(true, false, new Color(123, 123, 123), "http://localhost:1080", "Title", response, "testencode").build();
		Assert.assertEquals(m2.getImage().getUrl(), "http://localhost:1080/meme/S06E04/741423.jpg?b64lines=dGVzdGVuY29kZQ==");
		Assert.assertEquals(m2.getTitle(), null);
		Assert.assertEquals(m2.getUrl(), null);
		Assert.assertEquals(m2.getDescription(), null);
		Assert.assertEquals(m2.getFields().size(), 0);

		// Detail, No Image
		MessageEmbed m3 = Frinkiac.buildEmbed(false, true, new Color(123, 123, 123), "http://localhost:1080", "Title", response, "testencode").build();
		Assert.assertEquals(m3.getImage(), null);
		Assert.assertEquals(m3.getTitle(), "Title");
		Assert.assertEquals(m3.getUrl(), "http://localhost:1080/caption/S06E04/741423");
		Assert.assertEquals(m3.getDescription(), "\"Itchy & Scratchy Land\"");
		Assert.assertEquals(m3.getFields().size(), 1);
		Assert.assertEquals(m3.getFields().get(0).getName(), "Season 6 / Episode 4 (12:21)");
		Assert.assertEquals(m3.getFields().get(0).getValue(), "\u200BMommy, buy me a license plate!\nNo. Come along, Bort.\nAre you talking to me?");

		// No Image or Detail (should never be used)
		MessageEmbed m4 = Frinkiac.buildEmbed(false, false, new Color(123, 123, 123), "http://localhost:1080", "Title", response, "testencode").build();
		Assert.assertEquals(m4.getImage(), null);
		Assert.assertEquals(m4.getTitle(), null);
		Assert.assertEquals(m4.getUrl(), null);
		Assert.assertEquals(m4.getDescription(), null);
		Assert.assertEquals(m4.getFields().size(), 0);
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
	public void getResponse() {
		Mock.reset();
		String host = TBot.getConfig().HOST_SIMPSONS;

		String random = "src/test/resources/mock/frinkiac/timestamp_blank.json";
		Mock.setExpectation("GET", "/api/random", 200, new File(random));
		Assert.assertEquals(Frinkiac.getResponse(host, host + "/api/random"), TestUtils.readFile(random));

		String timestamp1 = "src/test/resources/mock/frinkiac/timestamp_blank.json";
		String timestamp2 = "src/test/resources/mock/frinkiac/timestamp_large.json";
		String timestamp3 = "src/test/resources/mock/frinkiac/timestamp_not_found.json";
		Mock.setExpectation("GET", "/api/caption?e=S00E00&t=0", 200, new File(timestamp1));
		Mock.setExpectation("GET", "/api/caption?e=S99E99&t=9999999", 200, new File(timestamp2));
		Mock.setExpectation("GET", "/api/caption?e=S00E00&t=1", 200, new File(timestamp3));
		Assert.assertEquals(Frinkiac.getResponse(host, host + "/api/caption?e=S00E00&t=0"), TestUtils.readFile(timestamp1));
		Assert.assertEquals(Frinkiac.getResponse(host, host + "/api/caption?e=S99E99&t=9999999"), TestUtils.readFile(timestamp2));
		Assert.assertEquals(Frinkiac.getResponse(host, host + "/api/caption?e=S00E00&t=1"), TestUtils.readFile(timestamp3));

		String query1 = "src/test/resources/mock/frinkiac/query.json";
		String query2 = "src/test/resources/mock/frinkiac/query_large.json";
		String query3 = "src/test/resources/mock/frinkiac/query_not_found.json";
		Mock.setExpectation("GET", "/api/search?q=test1", 200, new File(query1));
		Mock.setExpectation("GET", "/api/search?q=test2", 200, new File(query2));
		Mock.setExpectation("GET", "/api/search?q=test3", 200, new File(query3));
		Assert.assertEquals(Frinkiac.getResponse(host, host + "/api/search?q=test1"), TestUtils.readFile(timestamp1));
		Assert.assertEquals(Frinkiac.getResponse(host, host + "/api/search?q=test2"), TestUtils.readFile(timestamp2));
		Assert.assertEquals(Frinkiac.getResponse(host, host + "/api/search?q=test3"), "");
	}

	@Test(dependsOnMethods = "getResponse", alwaysRun = true)
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