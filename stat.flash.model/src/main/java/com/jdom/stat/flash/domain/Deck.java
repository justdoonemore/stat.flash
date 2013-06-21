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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Deck) {
			Deck other = (Deck) obj;

			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.name, other.name);
			builder.append(this.version, other.version);
			return builder.isEquals();
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(this.name);
		builder.append(this.version);
		return builder.toHashCode();
	}
}
