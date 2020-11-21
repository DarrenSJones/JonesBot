package ca.darrensjones.jonesbot.test.db.controller;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.db.controller.CReaction;
import ca.darrensjones.jonesbot.db.model.OReaction;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-21
 * @since 1.0.0 2020-11-21
 */
public class TCReaction {

	@Test
	public void getAll() {
		List<OReaction> l = CReaction.getAll();
		Assert.assertEquals(l.size(), 5);

		Assert.assertEquals(l.get(0).id, 1);
		Assert.assertEquals(l.get(0).shortcode, ":lacrosse:");
		Assert.assertEquals(l.get(0).unicode, "🥍");
		Assert.assertEquals(l.get(0).regex, "lacrosse");

		Assert.assertEquals(l.get(1).id, 2);
		Assert.assertEquals(l.get(1).shortcode, ":tophat:");
		Assert.assertEquals(l.get(1).unicode, "🎩");
		Assert.assertEquals(l.get(1).regex, "top hat");

		Assert.assertEquals(l.get(2).id, 3);
		Assert.assertEquals(l.get(2).shortcode, ":sandwich:");
		Assert.assertEquals(l.get(2).unicode, "🥪");
		Assert.assertEquals(l.get(2).regex, "sandwich(es)?");

		Assert.assertEquals(l.get(3).id, 4);
		Assert.assertEquals(l.get(3).shortcode, ":man_mage:");
		Assert.assertEquals(l.get(3).unicode, "🧙‍♂️");
		Assert.assertEquals(l.get(3).regex, "(mages?|wizards?)");

		Assert.assertEquals(l.get(4).id, 5);
		Assert.assertEquals(l.get(4).shortcode, ":flag_ca:");
		Assert.assertEquals(l.get(4).unicode, "🇨🇦");
		Assert.assertEquals(l.get(4).regex, "canada");
	}
}