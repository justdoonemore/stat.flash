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

/**
 * @author djohnson
 * 
 */
public final class StatFlashDb {

	// If you change the database schema, you must increment the database
	// version.
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "StatFlash.db";
	public static final String DECK_TABLE_NAME = "decks";
	public static final String ID = "id";
	public static final String DECK_NAME = "name";
	public static final String DECK_VERSION = "version";

	public static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
			+ DECK_TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY," + DECK_NAME
			+ " TEXT," + DECK_VERSION + " INTEGER)";

	private StatFlashDb() {
	}

}
