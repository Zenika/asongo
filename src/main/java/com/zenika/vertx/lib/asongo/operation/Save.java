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
public class Save  extends BasicThenTemplate<String> {

	private final static MongoOperator OPERATOR = MongoOperator.SAVE;

	private final String collection;
	private final JsonObject document;

	public Save(AsongoConfiguration configuration, String collection, JsonObject document) {

		super(OPERATOR, configuration);
		if (collection == null || collection.isEmpty()) {
			throw new IllegalArgumentException("A collection is required");
		}

		this.collection = collection;
		this.document = document;

	}

	@Override
	protected JsonObject getCommand() {
		JsonObject command = new JsonObject();

		command.putString("collection", collection);
		command.putString("action", OPERATOR.fieldName());
		command.putObject("document",document);
		return command;
	}

}