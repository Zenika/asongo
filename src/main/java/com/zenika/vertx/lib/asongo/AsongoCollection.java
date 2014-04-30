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

package com.zenika.vertx.lib.asongo;

import com.zenika.vertx.lib.asongo.operation.*;
import com.zenika.vertx.lib.asongo.then.Then;
import org.vertx.java.core.json.JsonObject;

import static com.zenika.vertx.lib.asongo.MatcherBuilder.getMatcher;

/**
 * All operations on a mongo collection
 * @Immutable
 * @author M. Labusquière
 */
public class AsongoCollection {

	private String collection;
	private AsongoConfiguration configuration;

	public AsongoCollection(AsongoConfiguration configuration, String collection) {
		this.configuration = configuration;
		this.collection = collection;
	}

	public Then<String> save(Object o)	{
		return new Save(configuration,collection,configuration.getMapper().getMarshaller().marshall(o));
	}

	public Find find(JsonObject matcher)	{
		return new Find(configuration,collection,matcher);
	}

	public Find find(String jsonMatcher)	{
		return find(new JsonObject(jsonMatcher));
	}

	public Find find(String jsonMatcher, String... args)	{
		return find(new JsonObject(getMatcher(jsonMatcher, args)));
	}	
	public Find find()	{
		return find(new JsonObject());
	}

	public FindOne findOne(JsonObject matcher)	{
		return new FindOne(configuration,collection,matcher);
	}

	public FindOne findOne()	{
		return findOne(new JsonObject());
	}

	public FindOne findOne(String jsonMatcher)	{
		return findOne(new JsonObject(jsonMatcher));
	}

	public FindOne findOne(String jsonMatcher,String... args)	{
		return findOne(new JsonObject(getMatcher(jsonMatcher, args)));
	}

	public Update update(JsonObject query)	{
		return new Update(configuration,collection,query);
	}

	public Update update(String query)	{
		return update(new JsonObject(query));
	}

	public Update update(String query,String... args)	{
		return update(new JsonObject(getMatcher(query,args)));
	}

	public FindAndModify findAndModify(JsonObject query)	{
		return new FindAndModify(configuration,collection,query);
	}

	public FindAndModify findAndModify(String query)	{
		return findAndModify(new JsonObject(query));
	}

	public Count count()	{
		return new Count(configuration,collection,new JsonObject());
	}

	public Count count(JsonObject query)	{
		return new Count(configuration,collection,query);
	}
	public Count count(String query)	{
		return count(new JsonObject(query));
	}

	public Count count(String query, String... args)	{
		return count(new JsonObject(getMatcher(query,args)));
	}
	public Delete delete(JsonObject query)	{
		return new Delete(configuration,collection,query);
	}

	public Delete delete(String query)	{
		return delete(new JsonObject(query));
	}

	public Delete delete(String query, String... args)	{
		return delete(new JsonObject(getMatcher(query,args)));
	}

	public Stats getCollectionStats()	{
		return new Stats(configuration,collection);
	}


}
