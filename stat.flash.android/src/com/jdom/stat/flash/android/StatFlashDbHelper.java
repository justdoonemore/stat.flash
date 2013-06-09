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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jdom.logging.api.LogFactory;
import com.jdom.logging.api.Logger;
import com.jdom.stat.flash.dao.StatFlashDb;

/**
 * @author djohnson
 * 
 */
public class StatFlashDbHelper extends SQLiteOpenHelper {

	private final Logger log = LogFactory.getLogger(StatFlashDbHelper.class);

	public StatFlashDbHelper(Context context) {
		super(context, StatFlashDb.DATABASE_NAME, null,
				StatFlashDb.DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		log.info("Executing SQL: " + StatFlashDb.SQL_CREATE_ENTRIES);

		db.execSQL(StatFlashDb.SQL_CREATE_ENTRIES);
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO: Do something on upgrade
	}

	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO: Do something on downgrade
	}

}
