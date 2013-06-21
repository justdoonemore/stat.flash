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

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.common.collect.Lists;
import com.jdom.database.android.AndroidConnectionWrapper;
import com.jdom.database.rawsql.RawSqlDbStrategy;
import com.jdom.logging.api.LogFactory;
import com.jdom.logging.api.Logger;
import com.jdom.stat.flash.dao.impl.SqlDecksDao;
import com.jdom.stat.flash.domain.Deck;
import com.jdom.stat.flash.model.DecksModel;
import com.jdom.stat.flash.view.DecksView;

/**
 * @author djohnson
 * 
 */
public class StatFlashActivity extends BaseActivity implements DecksView {

	private static final int LIST_ITEM_TYPE = android.R.layout.simple_list_item_1;

	private final Logger log = LogFactory.getLogger(StatFlashActivity.class);

	private StatFlashDbHelper dbHelper;

	private DecksModel decksModel;

	private String selectedDeck;

	private Runnable deckSelectedAction;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.decks);
	}

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

		SqlDecksDao decksDao = new SqlDecksDao(new RawSqlDbStrategy(
				new AndroidConnectionWrapper(dbHelper)));
		decksModel = new DecksModel(decksDao, this);

		decksModel.createDeck("someDeck");
		decksModel.createDeck("someDeck2");
		decksModel.createDeck("anotherDeck");

		decksModel.init();
	}

	/**
	 * @param decks
	 */
	public void setDecks(List<Deck> decks) {
		final ListView listview = (ListView) findViewById(R.id.decksList);
		final List<String> deckNames = Lists.newArrayList();
		for (Deck deck : decks) {
			deckNames.add(deck.getName());
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				LIST_ITEM_TYPE, deckNames);

		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				StatFlashActivity.this.selectedDeck = deckNames.get(arg2);
				deckSelectedAction.run();
			}
		});

		adapter.notifyDataSetChanged();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.jdom.stat.flash.view.DecksView#getSelectedDeck()
	 */
	public String getSelectedDeck() {
		return selectedDeck;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.jdom.stat.flash.view.DecksView#setDeckSelectedAction(java.lang.Runnable)
	 */
	public void setDeckSelectedAction(Runnable deckSelectedAction) {
		this.deckSelectedAction = deckSelectedAction;
	}
}
