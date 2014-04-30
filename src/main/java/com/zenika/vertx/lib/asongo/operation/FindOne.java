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

package com.zenika.vertx.lib.asongo.operation;

import com.zenika.vertx.lib.asongo.AsongoConfiguration;
import com.zenika.vertx.lib.asongo.then.DocumentThenTemplate;
import com.zenika.vertx.lib.asongo.then.Then;
import org.vertx.java.core.json.JsonObject;

/**
 * @author M. Labusquière
 */
public class FindOne<T> extends DocumentThenTemplate<T,T> {

	private final static DocumentMongoOperator OPERATOR = DocumentMongoOperator.FINDONE;

	private AsongoConfiguration configuration;
	private final String collection;
	private final JsonObject query;

	private JsonObject projection = new JsonObject();
	private Class<T> clazz;

	public FindOne(AsongoConfiguration configuration, String collection, JsonObject query) {

		if (collection == null || collection.isEmpty()) {
			throw new IllegalArgumentException("A collection is required");
		}

		this.configuration = configuration;
		this.collection = collection;
		this.query = query;
	}


	public FindOne projection(JsonObject jsonProjection) {
		this.projection = jsonProjection;
		return this;
	}

	public Then as(Class clazz)	{
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
		if(projection.size() != 0)
			command.putObject("projection",projection);
		return command;
	}

	@Override
	protected AsongoConfiguration getConfiguration() {
		return configuration;
	}

	@Override
	protected Class<T> getClazz() {
		return clazz;
	}

	@Override
	protected DocumentMongoOperator getOperator() {
		return OPERATOR;
	}
}
