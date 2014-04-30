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

package com.zenika.vertx.lib.asongo.zenika.integration.java;

import com.zenika.vertx.lib.asongo.Asongo;
import com.zenika.vertx.lib.asongo.zenika.integration.java.util.MongoVerticle;
import org.junit.Test;
import org.vertx.java.core.Handler;

import static org.vertx.testtools.VertxAssert.*;

/**
 * @author M. Labusquière
 */

/**
 * Simple integration test which shows tests deploying other verticles, using the Vert.x API etc
 */
public class DropSaveCountTest extends MongoVerticle {

	private Asongo asongo;

	@Test
	public void DropInsertCountTest() {
		asongo = new Asongo(vertx.eventBus());
		deployFongoPersistorAndRemoveCollection(new Handler<Void>() {
			@Override
			public void handle(Void event) {
				insertData(1,new Handler<String>() {
					@Override
					public void handle(String id) {
						assertNotNull("The insertion is not working or we don't receive the id created by the persitor",id);
						getAsongoTestCollection().count().then(new Handler<Integer>() {
							@Override
							public void handle(Integer documentNumber) {
								assertTrue("The collection had not be dropped or the insertion is not working ("+ documentNumber + " documents)",documentNumber == 1);
								testComplete();
							}
						});
					}
				});
			}
		});
	}

}