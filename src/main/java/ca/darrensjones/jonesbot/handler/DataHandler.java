package ca.darrensjones.jonesbot.handler;

import java.util.HashMap;
import java.util.List;

import ca.darrensjones.jonesbot.db.controller.CFrinkiacSaved;
import ca.darrensjones.jonesbot.db.model.OFrinkiacSaved;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-12-08
 * @since 1.0.0 2020-12-08
 */
public class DataHandler {

	// Saved Key/Timestamp from the DB
	public List<OFrinkiacSaved> simpsonsSaved;
	public List<OFrinkiacSaved> futuramaSaved;
	public List<OFrinkiacSaved> rickMortySaved;

	// Last Title and Response, stored by channel
	public HashMap<String, String[]> simpsonsLast;
	public HashMap<String, String[]> futuramaLast;
	public HashMap<String, String[]> rickMortyLast;

	public DataHandler() {
		setSaved();
		simpsonsLast = new HashMap<String, String[]>();
		futuramaLast = new HashMap<String, String[]>();
		rickMortyLast = new HashMap<String, String[]>();
	}

	public void setSaved() {
		simpsonsSaved = CFrinkiacSaved.getById("1");
		futuramaSaved = CFrinkiacSaved.getById("2");
		rickMortySaved = CFrinkiacSaved.getById("3");
	}

	public static void setLast(HashMap<String, String[]> last, String messageChannel, String title, String response) {
		last.remove(messageChannel);
		last.put(messageChannel, new String[] { title, response });
	}
}