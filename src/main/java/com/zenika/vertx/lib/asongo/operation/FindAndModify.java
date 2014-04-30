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
	private final AsongoConfiguration configuration;

	private JsonObject newDocument = new JsonObject();
	private JsonObject sort = new JsonObject();;
	private JsonObject projection = new JsonObject();;
	private boolean upsert = false;
	private boolean remove= false;

	private boolean sendBackNewDocument = false;
	private Class<T> clazz;

	public FindAndModify(AsongoConfiguration configuration, String collection, JsonObject query) {

		if (collection == null || collection.isEmpty()) {
			throw new IllegalArgumentException("A collection is required");
		}

		this.configuration = configuration;
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
	protected AsongoConfiguration getConfiguration() {
		return configuration;
	}

	@Override
	protected Class<T> getClazz() {
		return clazz;
	}

	@Override
	protected DocumentMongoOperator getOperator() {
		return OPERATOR;
	}
}
