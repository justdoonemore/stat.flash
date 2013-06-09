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

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.jdom.database.javasql.JavaSqlDatabase;
import com.jdom.database.rawsql.ConnectionWrapper;
import com.jdom.stat.flash.dao.StatFlashDb;

/**
 * @author djohnson
 * 
 */
public class TestSqliteDatabase extends JavaSqlDatabase {

	private ConnectionWrapper connection;

	@Override
	protected List<String> getSchemaDdl() {
		return Arrays.asList(StatFlashDb.SQL_CREATE_ENTRIES);
	}

	@Override
	public ConnectionWrapper getConnection() throws SQLException {
		if (this.connection == null) {
			this.connection = super.getConnection();
		}
		return this.connection;
	}

	public long getSoftwareVersion() {
		return 1;
	}

	@Override
	protected String getJdbcUrl() {
		return "jdbc:sqlite::memory:";
	}

	@Override
	protected String getDriverClass() {
		return "org.sqlite.JDBC";
	}

	@Override
	protected List<String> getUpgradeDeltaScripts(long i) {
		return Collections.emptyList();
	}
}
