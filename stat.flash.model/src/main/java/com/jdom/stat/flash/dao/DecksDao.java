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
package com.jdom.stat.flash.dao;

import java.util.List;

import com.jdom.database.api.DaoException;
import com.jdom.stat.flash.domain.Deck;

/**
 * @author djohnson
 * 
 */
public interface DecksDao {

	/**
	 * Create a deck.
	 * 
	 * @param deck
	 *            the deck to create
	 * @throws DaoException
	 *             if the deck was not able to be created
	 */
	void createDeck(Deck deck) throws DaoException;

	/**
	 * Get all decks.
	 * 
	 * @return the decks
	 * @throws DaoException
	 *             if the decks were not able to be retrieved
	 */
	List<Deck> getDecks() throws DaoException;
}
