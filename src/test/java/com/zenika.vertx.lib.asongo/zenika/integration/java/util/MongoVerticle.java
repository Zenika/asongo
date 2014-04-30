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

package com.zenika.vertx.lib.asongo.zenika.integration.java.util;

import com.zenika.vertx.lib.asongo.Asongo;
import com.zenika.vertx.lib.asongo.AsongoCollection;
import com.zenika.vertx.lib.asongo.zenika.integration.java.model.Model;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.Handler;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.core.logging.impl.LoggerFactory;
import org.vertx.testtools.TestVerticle;


/**
 * @author M. Labusquière
 */
public class MongoVerticle extends TestVerticle {

	public static final String ASONGO_TEST_COLLECTION = "asongotest";
	private static final Logger LOGGER = LoggerFactory.getLogger(MongoVerticle.class);
	private Asongo asongo;

	protected void deployFongoPersistorAndRemoveCollection(final Handler<Void> handler) {
		container.deployModule("io.vertx~mod-mongo-persistor~2.1.0", new JsonObject("{\"fake\":true}"), new Handler<AsyncResult<String>>() {
			@Override
			public void handle(AsyncResult<String> event) {
				asongo = new Asongo(vertx.eventBus());
				asongo.dropCollection(ASONGO_TEST_COLLECTION).then(new Handler<Void>() {
					@Override
					public void handle(Void event) {
						LOGGER.info("Collection " + ASONGO_TEST_COLLECTION + " had been dropped");
						handler.handle(null);
					}
				});
			}
		});
	}

	protected void insertData(final int dataNumber, final Handler<String> handler) {
		getAsongoTestCollection().save(new Model()).then(new Handler<String>() {
			@Override
			public void handle(String id) {
				if (dataNumber > 1)
					insertData(dataNumber - 1, handler);
				else
					handler.handle(id);
			}
		});
	}

	protected AsongoCollection getAsongoTestCollection() {
		return getAsongo().getCollection(ASONGO_TEST_COLLECTION);
	}

	protected Asongo getAsongo() {
		return asongo;
	}
}