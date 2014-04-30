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

package com.zenika.vertx.lib.asongo.then;

import com.mongodb.MongoException;
import com.zenika.vertx.lib.asongo.AsongoConfiguration;
import com.zenika.vertx.lib.asongo.domain.result.PersistorResult;
import com.zenika.vertx.lib.asongo.mapper.marshall.Unmarshaller;
import com.zenika.vertx.lib.asongo.operation.MongoOperator;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.core.logging.impl.LoggerFactory;

/**
 * This implementation is convenient for any determined return type
 * T is the return type
 * @author M. Labusquière
 */
public abstract class BasicThenTemplate<T> implements Then<T> {

	private final static Logger LOGGER = LoggerFactory.getLogger(BasicThenTemplate.class);

	@Override
	public void then(final Handler<T> handler) {

		final JsonObject command = getCommand();
		final AsongoConfiguration configuration = getConfiguration();

		if(LOGGER.isDebugEnabled())
			LOGGER.debug("The command " + command + ", is send to " + configuration.getMongoPersistorAdress());

		configuration.getEventBus().send(configuration.getMongoPersistorAdress(), command, new Handler<Message<JsonObject>>() {

			@Override
			public void handle(final Message<JsonObject> message) {

				Unmarshaller unmarshaller = configuration.getMapper().getUnmarshaller();

				final PersistorResult presult = unmarshaller.unmarshall(message.body().toString(), PersistorResult.class);

				if(LOGGER.isDebugEnabled())
					LOGGER.debug("The result is " + presult);

				if (presult.isNotError()) {
					handler.handle(getOperator().<T>getResult(presult));
				} else {
					String errorMsg = presult.getMessage();
					LOGGER.error("Bad request to mongo " + errorMsg + " with the command " + command);
					throw new MongoException("Bad request to mongo " + errorMsg + " with the command " + command);
				}

			}
		});
	}

	//TODO force the configuration and operator by a constructor in the aim to put less code in the Operator implementation
	protected abstract JsonObject getCommand();

	protected abstract AsongoConfiguration getConfiguration();

	protected abstract MongoOperator getOperator();


}
