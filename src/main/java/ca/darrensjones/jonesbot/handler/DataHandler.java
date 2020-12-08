package ca.darrensjones.jonesbot.handler;

import java.util.List;

import ca.darrensjones.jonesbot.db.controller.CFrinkiacSaved;
import ca.darrensjones.jonesbot.db.model.OFrinkiacSaved;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-12-08
 * @since 1.0.0 2020-12-08
 */
public class DataHandler {

	public List<OFrinkiacSaved> savedSimpsons;

	public DataHandler() {
		setSaved();
	}

	public void setSaved() {
		savedSimpsons = CFrinkiacSaved.getById("1");
	}
}