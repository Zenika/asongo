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

package com.zenika.vertx.lib.asongo;

import com.zenika.vertx.lib.asongo.mapper.Mapper;
import com.zenika.vertx.lib.asongo.operation.Command;
import com.zenika.vertx.lib.asongo.operation.DropCollection;
import com.zenika.vertx.lib.asongo.operation.GetCollections;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.json.JsonObject;

/**
 * Initialise a configuration and offer operations on database
 *
 * @author M. Labusquière
 */
public class Asongo {

	private AsongoConfiguration configuration;

	public Asongo(EventBus eventBus) {
		this(new AsongoConfiguration(eventBus));
	}

	public Asongo(EventBus eventBus, Mapper mapper) {
		this(new AsongoConfiguration(eventBus, mapper));
	}

	private Asongo(AsongoConfiguration configuration) {
		this.configuration = configuration;
	}

	public final AsongoCollection getCollection(String collection) {
		return new AsongoCollection(configuration, collection);
	}

	public GetCollections getCollections() {
		return new GetCollections(configuration);
	}

	public Command execute(JsonObject command) {
		return new Command(configuration, command);
	}

	public Command execute(String command) {
		return execute(new JsonObject(command));
	}

	public Command execute(String command, String... args) {
		return execute(MatcherBuilder.getMatcher(command, args));
	}

	public DropCollection dropCollection(String collection) {
		return new DropCollection(configuration, collection);
	}

}
