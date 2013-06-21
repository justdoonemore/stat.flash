/** 
 *  Copyright (C) 2013  Just Do One More
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jdom.stat.flash.model;

import java.util.Collections;
import java.util.List;

import com.jdom.database.api.DaoException;
import com.jdom.logging.api.LogFactory;
import com.jdom.logging.api.Logger;
import com.jdom.stat.flash.dao.DecksDao;
import com.jdom.stat.flash.domain.Deck;
import com.jdom.stat.flash.view.DecksView;

/**
 * @author djohnson
 * 
 */
public class DecksModel {

	private final Logger log = LogFactory.getLogger(DecksModel.class);

	private final DecksDao decksDao;

	private final DecksView view;

	private final Runnable deckSelectedAction = new Runnable() {
		public void run() {
			deckSelected();
		}
	};

	public DecksModel(DecksDao decksDao, DecksView view) {
		this.decksDao = decksDao;
		this.view = view;
	}

	/**
	 * @param string
	 */
	public void createDeck(String deckName) {

		Deck deck = new Deck(deckName);

		try {
			decksDao.store(deck);

			log.info("Deck [" + deckName + "] created.");
		} catch (DaoException e) {
			log.error("Unable to create the deck.", e);
		}
	}

	/**
	 * @param string
	 */
	public void deleteDeck(String deckName) {

		Deck deck = new Deck(deckName);

		try {
			decksDao.delete(deck);

			log.info("Deck [" + deckName + "] deleted.");
		} catch (DaoException e) {
			log.error("Unable to delete the deck.", e);
		}
	}

	/**
	 * @return
	 */
	public List<Deck> getDecks() {
		try {
			return decksDao.getAll();
		} catch (DaoException e) {
			log.error("Unable to retrieve decks.", e);
			return Collections.emptyList();
		}
	}

	/**
	 * @param selectedDeck
	 */
	public void deckSelected() {
		final String selectedDeck = view.getSelectedDeck();
		view.showMessage("Deck", "Opening deck " + selectedDeck);
	}

	/**
	 * 
	 */
	public void init() {
		view.setDeckSelectedAction(deckSelectedAction);
		view.setDecks(getDecks());
	}
}
