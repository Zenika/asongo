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
				insertData(1, new Handler<String>() {
					@Override
					public void handle(String id) {
						assertNotNull("The insertion is not working or we don't receive the id created by the persitor", id);
						getAsongoTestCollection().count().then(new Handler<Integer>() {
							@Override
							public void handle(Integer documentNumber) {
								assertTrue("The collection had not be dropped or the insertion is not working (" + documentNumber + " documents)", documentNumber == 1);
								testComplete();
							}
						});
					}
				});
			}
		});
	}

}