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

package com.zenika.vertx.lib.asongo.operation;

import com.zenika.vertx.lib.asongo.AsongoConfiguration;
import com.zenika.vertx.lib.asongo.then.Then;
import com.zenika.vertx.lib.asongo.then.DocumentThenTemplate;
import org.vertx.java.core.json.JsonObject;

/**
 * This class is only working with a checkout of the latest mongo-persistor version
 * TODO We can immagine a findAndModify(query,update) or find(query).AndModify(update)
 * TODO handle better the remove option by stopping the api (Creating VoidThen?)
 * http://docs.mongodb.org/manual/reference/command/findAndModify/
 * @author M. Labusquière
 */
@Deprecated
public class FindAndModify<T> extends DocumentThenTemplate<T,T> {

	private final static DocumentMongoOperator OPERATOR = DocumentMongoOperator.FIND_AND_MODIFY;

	private final String collection;
	private final JsonObject query;

	private JsonObject newDocument = new JsonObject();
	private JsonObject sort = new JsonObject();;
	private JsonObject projection = new JsonObject();;
	private boolean upsert = false;
	private boolean remove= false;

	private boolean sendBackNewDocument = false;
	private Class<T> clazz;

	public FindAndModify(AsongoConfiguration configuration, String collection, JsonObject query) {

		super(OPERATOR, configuration);
		if (collection == null || collection.isEmpty()) {
			throw new IllegalArgumentException("A collection is required");
		}

		this.collection = collection;
		this.query = query;
	}

	public FindAndModify with(JsonObject newDocument) {
		this.newDocument = newDocument;
		return this;
	}

	public FindAndModify with(String newDocument) {
		return with(new JsonObject(newDocument));
	}

	public FindAndModify sort(JsonObject sort) {
		this.sort = sort;
		return this;
	}

	public FindAndModify sort(String sort) {
		return sort(new JsonObject(sort));
	}

	public FindAndModify projection(JsonObject fields) {
		this.projection = fields;
		return this;
	}

	public FindAndModify projection(String... projections) {
		JsonObject jsonProjection = new JsonObject();
		for(String projection :  projections)	{
			jsonProjection.putNumber(projection,1);
		}
		this.projection = jsonProjection;
		return this;
	}

	public FindAndModify upsert(boolean upsert) {
		this.upsert = upsert;
		return this;
	}

	public FindAndModify remove(boolean remove) {
		//TODO should return a then there is no "as" because no result
		this.remove = remove;
		return this;
	}

	public FindAndModify getNewDocument(boolean sendBackNewDocument) {
		this.sendBackNewDocument = sendBackNewDocument;
		return this;
	}

	public Then as(Class clazz)	{
		this.clazz = clazz;
		return this;
	}

	@Override
	protected JsonObject getCommand() {

		if (collection == null || collection.isEmpty()) {
			throw new IllegalArgumentException("A collection is required");
		}

		if (newDocument.size() != 0 && remove == true) { //Should be handled by forcing the api
			throw new IllegalArgumentException("You can't remove and update a document at the same time");
		}

		JsonObject command = new JsonObject();
		command.putString("collection", collection);
		command.putString("action", OPERATOR.fieldName());
		command.putObject("matcher", query);
		if(projection.size() != 0)
			command.putObject("projection", projection);
		if(newDocument.size() != 0 )
			command.putObject("update", newDocument);
		if(sort.size() != 0)
			command.putObject("sort", sort);
		if(upsert)
			command.putBoolean("upsert", upsert);
		if(remove)
			command.putBoolean("remove", remove);
		return command;
	}

	@Override
	protected Class<T> getClazz() {
		return clazz;
	}

}