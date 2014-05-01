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

	protected final MongoOperator operator;
	protected final AsongoConfiguration configuration;

	protected BasicThenTemplate(MongoOperator operator, AsongoConfiguration configuration) {

		if (operator == null || configuration == null) {
			throw new IllegalArgumentException("An operator and a configuration is required");
		}

		this.operator = operator;
		this.configuration = configuration;

	}

	@Override
	public void then(final Handler<T> handler) {

		final JsonObject command = getCommand();

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
					handler.handle(operator.<T>getResult(presult));
				} else {
					String errorMsg = presult.getMessage();
					LOGGER.error("Bad request to mongo " + errorMsg + " with the command " + command);
					throw new MongoException("Bad request to mongo " + errorMsg + " with the command " + command);
				}

			}
		});
	}

	protected abstract JsonObject getCommand();

}
