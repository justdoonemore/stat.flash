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
package com.jdom.stat.flash.android;

import java.util.List;

import android.app.Activity;

import com.jdom.database.android.AndroidConnectionWrapper;
import com.jdom.database.rawsql.RawSqlDbStrategy;
import com.jdom.logging.api.LogFactory;
import com.jdom.logging.api.Logger;
import com.jdom.stat.flash.dao.impl.SqlDecksDao;
import com.jdom.stat.flash.domain.Deck;
import com.jdom.stat.flash.model.DecksModel;

/**
 * @author djohnson
 * 
 */
public class StatFlashActivity extends Activity {

	private final Logger log = LogFactory.getLogger(StatFlashActivity.class);

	private StatFlashDbHelper dbHelper;

	private DecksModel decksModel;

	@Override
	protected void onResume() {
		super.onResume();

		dbHelper = new StatFlashDbHelper(getBaseContext());
		AndroidStatFlashDb db = new AndroidStatFlashDb(dbHelper);
		try {
			db.start();
		} catch (Exception e) {
			throw new RuntimeException("Unable to start the database!", e);
		}

		decksModel = new DecksModel(new SqlDecksDao(new RawSqlDbStrategy(
				new AndroidConnectionWrapper(dbHelper))));

		decksModel.createDeck("deck2");

		List<Deck> decks = decksModel.getDecks();

		log.info("Found " + decks.size() + " decks");
		for (Deck deck : decks) {
			log.info("Deck: " + deck.getName());
		}
	}
}
