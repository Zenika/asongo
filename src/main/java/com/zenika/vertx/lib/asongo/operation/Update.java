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
import com.zenika.vertx.lib.asongo.then.BasicThenTemplate;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.core.logging.impl.LoggerFactory;

/**
 * @author M. Labusquière
 */
public class Update extends BasicThenTemplate<Void> {

	private final static Logger LOGGER = LoggerFactory.getLogger(Update.class);
	private final static MongoOperator OPERATOR = MongoOperator.UPDATE;

	private final String collection;
	private final AsongoConfiguration configuration;

	private JsonObject query;
	private JsonObject newDocument;
	private boolean upsert = false;
	private boolean multi = false;

	public Update(AsongoConfiguration configuration, String collection, JsonObject query) {
		if (collection == null || collection.isEmpty()) {
			throw new IllegalArgumentException("A collection is required");
		}

		this.configuration = configuration;
		this.collection = collection;
		this.query = query;
	}

	public Update with(String newJsonDocument)	{
		return with(new JsonObject(newJsonDocument));
	}

	public Update with(JsonObject newDocument) {
		this.newDocument = newDocument;
		return this;
	}

	public Update upsert(boolean upsert)	{
		this.upsert = upsert;
		return this;
	}

	public Update multi(boolean multi)	{
		this.multi = multi;
		return this;
	}

	@Override
	protected JsonObject getCommand() {

		if(collection == null || collection.isEmpty())
			throw new IllegalArgumentException("A collection is required");

		JsonObject command = new JsonObject();
		command.putString("action", OPERATOR.fieldName());
		command.putString("collection",collection);
		command.putObject("criteria", query);
		command.putObject("objNew", newDocument);
		command.putBoolean("upsert", upsert);
		command.putBoolean("multi",multi);

		return command;
	}

	@Override
	protected AsongoConfiguration getConfiguration() {
		return configuration;
	}

	@Override
	protected MongoOperator getOperator() {
		return OPERATOR;
	}
}
