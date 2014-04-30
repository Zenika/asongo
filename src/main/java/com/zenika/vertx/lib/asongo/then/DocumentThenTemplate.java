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
import com.zenika.vertx.lib.asongo.operation.DocumentMongoOperator;
import com.zenika.vertx.lib.asongo.domain.result.DocumentResult;
import com.zenika.vertx.lib.asongo.mapper.marshall.Unmarshaller;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.core.logging.impl.LoggerFactory;

/**
 * This implementation is convenient for any generified return type
 * T is the return type
 * K is the type of document
 * @author M. Labusquière
 */
public abstract class DocumentThenTemplate<T,K> implements Then<T> {

	private final static Logger LOGGER = LoggerFactory.getLogger(DocumentThenTemplate.class);

	protected final DocumentMongoOperator operator;
	protected final AsongoConfiguration configuration;

	protected DocumentThenTemplate(DocumentMongoOperator operator, AsongoConfiguration configuration) {

		if (operator == null || configuration == null) {
			throw new IllegalArgumentException("An operator and a configuration is required");
		}

		this.operator = operator;
		this.configuration = configuration;
	}

	@Override
	public void then(final Handler<T> handler) {

		final JsonObject command = getCommand();
		final Class <K> clazz = getClazz();

		if(LOGGER.isDebugEnabled())
			LOGGER.debug("The command " + command + ", is send to " + configuration.getMongoPersistorAdress());

		configuration.getEventBus().send(configuration.getMongoPersistorAdress(), command, new Handler<Message<JsonObject>>() {

			@Override
			public void handle(final Message<JsonObject> message) {

				Unmarshaller unmarshaller = configuration.getMapper().getUnmarshaller();

				final DocumentResult<K> presult = unmarshaller.unmarshall(message.body().toString(), DocumentResult.class, clazz);

				if(LOGGER.isDebugEnabled())
					LOGGER.debug("The result is " + presult);

				if (presult.isNotError()) {
					handler.handle(operator.<T, K>getResult(presult));
				} else {
					String errorMsg = presult.getMessage();
					LOGGER.error("Bad request to mongo " + errorMsg + " with the command " + command);
					throw new MongoException("Bad request to mongo " + errorMsg + " with the command " + command);
				}

			}
		});
	}

	protected abstract JsonObject getCommand();

	protected abstract Class<K> getClazz();

}
