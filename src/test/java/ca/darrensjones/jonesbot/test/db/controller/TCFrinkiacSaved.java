package ca.darrensjones.jonesbot.test.db.controller;

import ca.darrensjones.jonesbot.db.controller.CFrinkiacSaved;
import ca.darrensjones.jonesbot.db.model.OFrinkiacSaved;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-24
 * @since   1.0.0 2020-12-09
 */
public class TCFrinkiacSaved {

	@Test
	public void getById() {

		// ID = 1: Simpsons
		List<OFrinkiacSaved> s = CFrinkiacSaved.getById("1");
		Assert.assertEquals(s.size(), 5);
		Assert.assertEquals(s.get(0).id, 1);
		Assert.assertEquals(s.get(0).name, "Trash");
		Assert.assertEquals(s.get(0).key, "S03E22");
		Assert.assertEquals(s.get(0).timestamp, "937738");
		Assert.assertEquals(s.get(0).regex, "trash");
		Assert.assertEquals(s.get(1).id, 2);
		Assert.assertEquals(s.get(1).name, "Flag");
		Assert.assertEquals(s.get(1).key, "S14E03");
		Assert.assertEquals(s.get(1).timestamp, "883966");
		Assert.assertEquals(s.get(1).regex, "flags?");
		Assert.assertEquals(s.get(2).id, 3);
		Assert.assertEquals(s.get(2).name, "Pig");
		Assert.assertEquals(s.get(2).key, "Movie");
		Assert.assertEquals(s.get(2).timestamp, "1321236");
		Assert.assertEquals(s.get(2).regex, "(spider )?pig");
		Assert.assertEquals(s.get(3).id, 4);
		Assert.assertEquals(s.get(3).name, "Catholic Church");
		Assert.assertEquals(s.get(3).key, "S10E12");
		Assert.assertEquals(s.get(3).timestamp, "927876");
		Assert.assertEquals(s.get(3).regex, "(catholic( church)?)|(we'?ve made a few changes)");
		Assert.assertEquals(s.get(4).id, 5);
		Assert.assertEquals(s.get(4).name, "The Anvil");
		Assert.assertEquals(s.get(4).key, "S08E15");
		Assert.assertEquals(s.get(4).timestamp, "856087");
		Assert.assertEquals(s.get(4).regex,
				"(the anvil)|(gay steel mill)|(we work hard\\,? we play hard)");

		// ID = 2: Futurama
		List<OFrinkiacSaved> f = CFrinkiacSaved.getById("2");
		Assert.assertEquals(f.size(), 2);
		Assert.assertEquals(f.get(0).id, 6);
		Assert.assertEquals(f.get(0).name, "Ass");
		Assert.assertEquals(f.get(0).key, "S05E15");
		Assert.assertEquals(f.get(0).timestamp, "586936");
		Assert.assertEquals(f.get(0).regex, "ass");
		Assert.assertEquals(f.get(1).id, 7);
		Assert.assertEquals(f.get(1).name, "Lincoln");
		Assert.assertEquals(f.get(1).key, "S03E10");
		Assert.assertEquals(f.get(1).timestamp, "589838");
		Assert.assertEquals(f.get(1).regex, "lincoln|jonesbot");

		// ID = 3: Rick&Morty
		List<OFrinkiacSaved> r = CFrinkiacSaved.getById("3");
		Assert.assertEquals(r.size(), 1);
		Assert.assertEquals(r.get(0).id, 8);
		Assert.assertEquals(r.get(0).name, "TV");
		Assert.assertEquals(r.get(0).key, "S01E08");
		Assert.assertEquals(r.get(0).timestamp, "62479");
		Assert.assertEquals(r.get(0).regex, "tv");
	}
}