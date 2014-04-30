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
import com.zenika.vertx.lib.asongo.then.DocumentThenTemplate;
import com.zenika.vertx.lib.asongo.then.Then;
import org.vertx.java.core.json.JsonObject;

import java.util.Collection;

/**
 * @author M. Labusquière
 */
public class Find<T> extends DocumentThenTemplate<Collection<T>,T> {

	private final static DocumentMongoOperator OPERATOR = DocumentMongoOperator.FIND;

	private final String collection;
	private final JsonObject query;

	private JsonObject command = new JsonObject();
	private JsonObject hint = new JsonObject();
	private JsonObject projection = new JsonObject();
	private int skip = -1;
	private int limit = -1;
	private Class<T> clazz ;

	public Find(AsongoConfiguration configuration, String collection, JsonObject query) {

		super(OPERATOR, configuration);

		if (collection == null || collection.isEmpty()) {
			throw new IllegalArgumentException("A collection is required");
		}

		this.collection = collection;
		this.query = query;
	}


	public Find skip(int skip) {
		this.skip = skip;
		return this;
	}

	public Find limit(int limit) {
		this.limit = limit;
		return this;
	}

	public Find hint(String name) {
		hint.putNumber(name, 1);
		return this;
	}

	public Find hint(JsonObject jsonProjection) {
		hint = jsonProjection;
		return this;
	}

	public Find projection(JsonObject jsonProjection) {
		this.projection = jsonProjection;
		return this;
	}

	public Find projection(String... projections) {
		JsonObject jsonProjection = new JsonObject();
		for(String projection :  projections)	{
			jsonProjection.putNumber(projection,1);
		}
		this.projection = jsonProjection;
		return this;
	}

	public Then as(Class clazz)	{

		if (collection == null || collection.isEmpty()) {
			throw new IllegalArgumentException("A collection is required");
		}

		command.putString("collection", collection);
		command.putString("action", OPERATOR.fieldName());
		command.putObject("matcher", query);
		if(projection.size() != 0)
			command.putObject("projection", projection);
		if(skip > 0)
			command.putNumber("skip", skip);
		if(limit > 0)
			command.putNumber("limit", limit);
		if(hint.size() > 0)
			command.putObject("hint", hint);
		this.clazz = clazz;
		return this;
	}

	@Override
	protected JsonObject getCommand() {
		return command;
	}

	@Override
	protected Class<T> getClazz() {
		return clazz;
	}

}
