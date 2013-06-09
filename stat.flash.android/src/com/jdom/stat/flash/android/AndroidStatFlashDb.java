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

import java.sql.SQLException;

import android.database.sqlite.SQLiteOpenHelper;

import com.jdom.database.android.AndroidConnectionWrapper;
import com.jdom.database.rawsql.ConnectionWrapper;
import com.jdom.stat.flash.dao.StatFlashDb;

/**
 * @author djohnson
 * 
 */
public class AndroidStatFlashDb extends StatFlashDb {

	private final SQLiteOpenHelper dbHelper;

	public AndroidStatFlashDb(SQLiteOpenHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.jdom.database.rawsql.RawDatabase#getConnection()
	 */
	public ConnectionWrapper getConnection() throws SQLException {
		return new AndroidConnectionWrapper(dbHelper);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.jdom.database.rawsql.BaseRawDatabase#startInternal()
	 */
	@Override
	protected void startInternal() throws Exception {
	}

}
