/*
 * The MIT License (MIT)
 *
 * Copyright (c)  2014 Zenika
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author M. Labusquière
 */

package com.zenika.vertx.lib.asongo;

import org.vertx.java.core.logging.Logger;
import org.vertx.java.core.logging.impl.LoggerFactory;


/**
 * Builder to make matcher and query
 * Only string are supported.
 * TODO improve it by supporting other types
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
