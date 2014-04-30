package com.zenika.vertx.lib.asongo.zenika.integration.java;

import com.zenika.vertx.lib.asongo.Asongo;
import com.zenika.vertx.lib.asongo.zenika.integration.java.util.MongoVerticle;
import org.junit.Test;
import org.vertx.java.core.Handler;
import org.vertx.java.core.json.JsonObject;

import static org.vertx.testtools.VertxAssert.assertNotNull;
import static org.vertx.testtools.VertxAssert.testComplete;

/**
 * @author M. Labusquière
 */
public class MapReduceTest extends MongoVerticle {

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
						testComplete();
						/*getAsongoTestCollection().map("function() {emit(this.cust_id, this.price);};")
								.reduce("function(keyCustId, valuesPrices) {return Array.sum(valuesPrices);};").then(new Handler<JsonObject>() {
									@Override
									public void handle(JsonObject event) {
										System.out.println(event);
									}
								});*/
					}
				});
			}
		});
	}

}
