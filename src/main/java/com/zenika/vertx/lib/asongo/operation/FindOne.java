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
import com.zenika.vertx.lib.asongo.then.DocumentThenTemplate;
import com.zenika.vertx.lib.asongo.then.Then;
import org.vertx.java.core.json.JsonObject;

/**
 * @author M. Labusquière
 */
public class FindOne<T> extends DocumentThenTemplate<T, T> {

	private final static DocumentMongoOperator OPERATOR = DocumentMongoOperator.FINDONE;

	private final String collection;
	private final JsonObject query;

	private JsonObject projection = new JsonObject();
	private Class<T> clazz;

	public FindOne(AsongoConfiguration configuration, String collection, JsonObject query) {

		super(OPERATOR, configuration);
		if (collection == null || collection.isEmpty()) {
			throw new IllegalArgumentException("A collection is required");
		}

		this.collection = collection;
		this.query = query;
	}


	public FindOne projection(JsonObject jsonProjection) {
		this.projection = jsonProjection;
		return this;
	}

	public Then as(Class clazz) {
		this.clazz = clazz;
		return this;
	}

	@Override
	protected JsonObject getCommand() {

		if (collection == null || collection.isEmpty()) {
			throw new IllegalArgumentException("A collection is required");
		}

		JsonObject command = new JsonObject();

		command.putString("collection", collection);
		command.putString("action", OPERATOR.fieldName());
		command.putObject("matcher", query);
		if (projection.size() != 0)
			command.putObject("projection", projection);
		return command;
	}

	@Override
	protected Class<T> getClazz() {
		return clazz;
	}

}
