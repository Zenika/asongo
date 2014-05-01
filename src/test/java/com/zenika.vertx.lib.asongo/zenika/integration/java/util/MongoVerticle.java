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