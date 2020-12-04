package ca.darrensjones.jonesbot.handler;

import java.util.List;

import ca.darrensjones.jonesbot.db.controller.CFrinkiacSaved;
import ca.darrensjones.jonesbot.db.controller.CReaction;
import ca.darrensjones.jonesbot.db.model.OFrinkiacSaved;
import ca.darrensjones.jonesbot.db.model.OReaction;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-12-03
 * @since 1.0.0 2020-12-03
 */
public class ListHandler {

	public List<OReaction> reactions;
	public List<OFrinkiacSaved> savedSimpsons;

	public ListHandler() {
		setLists();
	}

	public void setLists() {
		this.reactions = CReaction.getAll();
		this.savedSimpsons = CFrinkiacSaved.getAll();
	}
}