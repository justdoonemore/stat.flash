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
package com.jdom.stat.flash.db;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jdom.database.api.DaoException;
import com.jdom.database.api.DatabaseUtil;
import com.jdom.database.rawsql.ConnectionWrapper;
import com.jdom.database.rawsql.RawSqlDbStrategy;
import com.jdom.stat.flash.dao.impl.SqlDecksDao;
import com.jdom.stat.flash.domain.Deck;

/**
 * @author djohnson
 * 
 */
public class SqlDecksDaoTest {

	private ConnectionWrapper c;

	private SqlDecksDao dao;

	private final TestSqliteDatabase db = new TestSqliteDatabase();

	@Before
	public void setUp() throws Exception {
		db.start();

		c = db.getConnection();
		dao = new SqlDecksDao(new RawSqlDbStrategy(c));
	}

	@After
	public void tearDown() throws SQLException {
		db.shutdown();
	}

	@Test
	public void storeAddsToDatabase() throws DaoException {
		Deck deck = new Deck("deck1");
		dao.store(deck);
		List<Deck> decks = dao.getAll();

		assertThat(decks, hasSize(1));
	}

	@Test
	public void storeSetsPrimaryKey() throws DaoException {
		Deck deck = new Deck("deck1");
		dao.store(deck);
		List<Deck> decks = dao.getAll();

		assertThat(decks.iterator().next().getId(), is(not(DatabaseUtil.UNSET)));
	}

	@Test
	public void storeAddsWithCorrectName() throws DaoException {
		Deck deck = new Deck("deck1");
		dao.store(deck);
		List<Deck> decks = dao.getAll();

		assertThat(decks.iterator().next().getName(),
				is(equalTo(deck.getName())));
	}

	@Test
	public void storeAddsWithCorrectVersion() throws DaoException {
		Deck deck = new Deck("deck1");
		dao.store(deck);
		List<Deck> decks = dao.getAll();

		assertThat(decks.iterator().next().getVersion(),
				is(equalTo(deck.getVersion())));
	}

	@Test
	public void getAllReturnsAllDecks() throws DaoException {
		Deck deck1 = new Deck("deck1");
		Deck deck2 = new Deck("deck2");
		Deck deck3 = new Deck("deck3");

		dao.store(deck1);
		dao.store(deck2);
		dao.store(deck3);

		assertThat(dao.getAll(), contains(deck1, deck2, deck3));
	}

	@Test
	public void deleteRemovesDeck() throws DaoException {
		Deck deck1 = new Deck("deck1");
		Deck deck2 = new Deck("deck2");
		Deck deck3 = new Deck("deck3");

		dao.store(deck1);
		dao.store(deck2);
		dao.store(deck3);

		List<Deck> all = dao.getAll();
		assertThat(all, hasSize(3));

		dao.delete(deck2);

		all = dao.getAll();
		assertThat(all, hasSize(2));
		assertThat(all, contains(deck1, deck3));
	}
}
