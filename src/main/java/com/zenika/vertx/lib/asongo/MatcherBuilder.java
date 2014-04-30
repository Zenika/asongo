/*
 * Copyright 2014 Zenika
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author M. Labusquière
 */

package com.zenika.vertx.lib.asongo;

import org.vertx.java.core.logging.Logger;
import org.vertx.java.core.logging.impl.LoggerFactory;


/**
 * In the aim to handle the quote around string value
 * @author M. Labusquière
 */
public class MatcherBuilder {

	private final static Logger LOGGER = LoggerFactory.getLogger(MatcherBuilder.class);
	private final static String DEFAULT_SYMBOL = "#";

	public static String getMatcher(String jsonMatcher, String... args) {

		if(args == null)
			throw new NullPointerException("Args can't be null");

		for(String arg: args)	{
			if(arg == null)
				throw new NullPointerException("An arg can't be null");
			jsonMatcher = jsonMatcher.replaceFirst(DEFAULT_SYMBOL,"\"" + arg + "\"");
		}

		if(jsonMatcher.contains(DEFAULT_SYMBOL))	{
			LOGGER.error("We build a matcher still containing the symbol to replace");
			throw new IllegalStateException("Not enought args to build the matcher");
		}

		return jsonMatcher;
	}
}
