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
package com.jdom.stat.flash.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.jdom.database.api.DaoException;
import com.jdom.database.rawsql.DbStrategy;
import com.jdom.database.rawsql.ResultSetResolver;
import com.jdom.database.rawsql.ResultSetWrapper;
import com.jdom.stat.flash.dao.DecksDao;
import com.jdom.stat.flash.dao.StatFlashDb;
import com.jdom.stat.flash.domain.Deck;

/**
 * @author djohnson
 * 
 */
public class SqlDecksDao implements DecksDao, ResultSetResolver<Deck> {

	private final String insertSql = "INSERT INTO "
			+ StatFlashDb.DECK_TABLE_NAME + " (" + StatFlashDb.DECK_NAME + ", "
			+ StatFlashDb.DECK_VERSION + ") VALUES (?, ?);";

	private final String GET_DECKS_SQL = "SELECT * FROM "
			+ StatFlashDb.DECK_TABLE_NAME;

	private final DbStrategy dbStrategy;

	public SqlDecksDao(DbStrategy dbStrategy) {
		this.dbStrategy = dbStrategy;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.jdom.stat.flash.dao.DecksDao#createDeck(com.jdom.stat.flash.domain.Deck)
	 */
	public void createDeck(Deck deck) throws DaoException {
		try {
			dbStrategy.executeSql(insertSql, deck.getName(), deck.getVersion());
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	/**
	 * @return
	 */
	public List<Deck> getDecks() throws DaoException {
		try {
			return dbStrategy.retrieveSql(GET_DECKS_SQL, this);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.jdom.database.api.ResultSetResolver#resolveResultSet(java.sql.ResultSet)
	 */
	public Deck resolveResultSet(ResultSetWrapper resultSet)
			throws SQLException {
		return new Deck(resultSet.getLong(1), resultSet.getString(2),
				resultSet.getInt(3));
	}
}
