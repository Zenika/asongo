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
public class Count extends BasicThenTemplate<Integer> {

	private static final MongoOperator OPERATOR = MongoOperator.COUNT;
	private static final Logger LOGGER = LoggerFactory.getLogger(Count.class);

	private final AsongoConfiguration configuration;
	private final String collection;
	private final JsonObject query;

	public Count(AsongoConfiguration configuration, String collection, JsonObject query) {

		if (collection == null || collection.isEmpty()) {
			throw new IllegalArgumentException("A collection is required");
		}

		this.configuration = configuration;
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

	@Override
	protected AsongoConfiguration getConfiguration() {
		return configuration;
	}

	@Override
	protected MongoOperator getOperator() {
		return OPERATOR;
	}
}
