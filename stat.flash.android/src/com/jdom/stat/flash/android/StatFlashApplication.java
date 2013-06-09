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

import android.app.Application;

import com.jdom.logging.android.AndroidLoggerFactory;
import com.jdom.logging.api.LogFactory;
import com.jdom.logging.api.Logger;

/**
 * @author djohnson
 * 
 */
public class StatFlashApplication extends Application {
	private final Logger log;
	{
		LogFactory.setFactory(AndroidLoggerFactory.class);
		log = LogFactory.getLogger(StatFlashApplication.class);
	}

	@Override
	public void onCreate() {
		super.onCreate();

		log.info("onCreate() called");
	}
}
