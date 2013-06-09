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
package com.jdom.stat.flash.domain;

import com.jdom.database.api.DatabaseUtil;

/**
 * @author djohnson
 * 
 */
public class Deck {

	private long id = DatabaseUtil.UNSET;
	private final String name;
	private final int version;

	public Deck(String name) {
		this(name, 1);
	}

	public Deck(String name, int version) {
		this(DatabaseUtil.UNSET, name, version);
	}

	public Deck(long id, String name, int version) {
		this.id = id;
		this.name = name;
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public int getVersion() {
		return version;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
