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

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdom.database.api.DatabaseUtil;
import com.jdom.stat.flash.dao.StatFlashDb;

/**
 * @author djohnson
 * 
 */
public class TestSqliteDatabase {

	private static Connection connection;

	public static Connection start() throws Exception {
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite::memory:");

		createSchema();

		return connection;
	}

	public static void shutdown() throws SQLException {
		DatabaseUtil.close(new Closeable() {
			public void close() throws IOException {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new IOException(e);
				}
			}
		});
	}

	private static void createSchema() throws SQLException {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			stmt.execute(StatFlashDb.SQL_CREATE_ENTRIES);
		} finally {
			DatabaseUtil.close(stmt);
		}
	}
}
