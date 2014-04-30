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
public class Command extends BasicThenTemplate<JsonObject> {

private static final Logger LOGGER = LoggerFactory.getLogger(Command.class);

	private final static MongoOperator OPERATOR = MongoOperator.COMMAND;
	private final AsongoConfiguration configuration;
	private final JsonObject command;

	public Command(AsongoConfiguration configuration, JsonObject command) {
		this.configuration = configuration;
		this.command = command;
	}


	@Override
	protected JsonObject getCommand() {
		JsonObject command = new JsonObject();
		command.putString("action", OPERATOR.fieldName());
		command.putString("command",this.command.toString());
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
