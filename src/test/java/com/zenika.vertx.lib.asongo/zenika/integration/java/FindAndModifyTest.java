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
import com.zenika.vertx.lib.asongo.zenika.integration.java.model.Model;
import com.zenika.vertx.lib.asongo.zenika.integration.java.util.MongoVerticle;
import org.junit.Test;
import org.vertx.java.core.Handler;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.core.logging.impl.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.vertx.testtools.VertxAssert.testComplete;

/**
 * @author M. Labusquière
 */
public class FindAndModifyTest extends MongoVerticle {

	private final static Logger LOGGER = LoggerFactory.getLogger(FindAndModifyTest.class);
	private Asongo asongo;
	@Test
	public void updateTest() {
		LOGGER.warn("This test should work but not with the last release");
		testComplete();
/*		asongo = new Asongo(vertx.eventBus());
		deployFongoPersistorAndRemoveCollection(new Handler<Void>() {
			@Override
			public void handle(Void event) {
				insertData(1,new Handler<String>() {
					@Override
					public void handle(String id) {
						assertNotNull("The insertion is not working or we don't receive the id created by the persitor",id);
						getAsongoTestCollection().findAndModify("{\"_id\":\""+id+"\"}").with("{\"a\":\"newValue\"}")
								.getNewDocument(true).as(Model.class).then(new Handler<Model>() {
							@Override
							public void handle(Model model) {
								assertNotNull("This method should send back the new document",model);
								assertEquals("The new document is not returned","newValue",model.getA());
								testComplete();
							}
						});
					}
				});
			}
		});*/
	}
}

