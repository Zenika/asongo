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

package com.zenika.vertx.lib.asongo.operation;

import com.zenika.vertx.lib.asongo.AsongoConfiguration;
import com.zenika.vertx.lib.asongo.then.BasicThenTemplate;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.core.logging.impl.LoggerFactory;

/**
 * @author M. Labusquière
 */
public class Delete extends BasicThenTemplate<Integer> {

	private final static MongoOperator OPERATOR = MongoOperator.DELETE;

	private final String collection;
	private final JsonObject query;

	public Delete(AsongoConfiguration configuration, String collection, JsonObject query) {

		super(OPERATOR, configuration);
		if (collection == null || collection.isEmpty()) {
			throw new IllegalArgumentException("A collection is required");
		}

		this.collection = collection;
		this.query = query;
	}

	@Override
	protected JsonObject getCommand() {
		JsonObject command = new JsonObject();
		command.putString("action", OPERATOR.fieldName());
		command.putString("collection",collection);
		command.putObject("matcher",query);
		return command;
	}

}
