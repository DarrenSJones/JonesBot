package ca.darrensjones.jonesbot.handler;

import ca.darrensjones.jonesbot.db.controller.CAutoResponseReaction;
import ca.darrensjones.jonesbot.db.controller.CFrinkiacSaved;
import ca.darrensjones.jonesbot.db.model.OAutoResponseReaction;
import ca.darrensjones.jonesbot.db.model.OFrinkiacSaved;
import java.util.HashMap;
import java.util.List;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-18
 * @since   1.0.0 2020-12-08
 */
public class DataHandler {

	// Auto-Response
	public List<OAutoResponseReaction> autoResponseReactions;

	// Saved Key/Timestamp from the DB
	public List<OFrinkiacSaved> simpsonsSaved;
	public List<OFrinkiacSaved> futuramaSaved;
	public List<OFrinkiacSaved> rickMortySaved;

	// Last Title and Response, stored by channel
	public HashMap<String, String[]> simpsonsLast;
	public HashMap<String, String[]> futuramaLast;
	public HashMap<String, String[]> rickMortyLast;

	public DataHandler() {
		reloadSQL();

		simpsonsLast = new HashMap<String, String[]>();
		futuramaLast = new HashMap<String, String[]>();
		rickMortyLast = new HashMap<String, String[]>();
	}

	public void reloadSQL() {
		autoResponseReactions = CAutoResponseReaction.getAll();

		simpsonsSaved = CFrinkiacSaved.getById("1");
		futuramaSaved = CFrinkiacSaved.getById("2");
		rickMortySaved = CFrinkiacSaved.getById("3");
	}

	/** Used by Frinkiac to store the most recent response by channel, overwriting any existing. */
	public static void setLast(HashMap<String, String[]> last, String messageChannel, String title,
			String response) {
		last.remove(messageChannel);
		last.put(messageChannel, new String[] { title, response });
	}
}