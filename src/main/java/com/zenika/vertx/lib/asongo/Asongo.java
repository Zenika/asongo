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

import com.zenika.vertx.lib.asongo.mapper.Mapper;
import com.zenika.vertx.lib.asongo.operation.DropCollection;
import com.zenika.vertx.lib.asongo.operation.GetCollections;
import com.zenika.vertx.lib.asongo.operation.Command;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.json.JsonObject;

/**
 * Initialise a configuration and offer operations on database
 *
 * @Immutable
 * @author M. Labusquière
 */
public class Asongo {

	private AsongoConfiguration configuration;

	public Asongo(EventBus eventBus)	{
		this(new AsongoConfiguration(eventBus));
	}

	public Asongo(EventBus eventBus,Mapper mapper)	{
		this(new AsongoConfiguration(eventBus,mapper));
	}

	private Asongo(AsongoConfiguration configuration) {
		this.configuration = configuration;
	}

	public final AsongoCollection getCollection(String collection) {
		return new AsongoCollection(configuration,collection);
	}

	public GetCollections getCollections()	{
		return new GetCollections(configuration);
	}

	public Command execute(JsonObject command)	{
		return new Command(configuration,command);
	}

	public Command execute(String command)	{
		return execute(new JsonObject(command));
	}

	public Command execute(String command, String... args)	{
		return execute(MatcherBuilder.getMatcher(command,args));
	}

	public DropCollection dropCollection(String collection)	{
		return new DropCollection(configuration,collection);
	}

}
