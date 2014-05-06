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

import com.zenika.vertx.lib.asongo.operation.*;
import com.zenika.vertx.lib.asongo.then.Then;
import org.vertx.java.core.json.JsonObject;

import static com.zenika.vertx.lib.asongo.MatcherBuilder.getMatcher;

/**
 * All operations on a mongo collection
 *
 * @author M. Labusquière
 * @Immutable
 */
public class AsongoCollection {

	private String collection;
	private AsongoConfiguration configuration;

	public AsongoCollection(AsongoConfiguration configuration, String collection) {
		this.configuration = configuration;
		this.collection = collection;
	}

	public Then<String> save(Object o) {
		return new Save(configuration, collection, configuration.getMapper().getMarshaller().marshall(o));
	}

	public Find find(JsonObject matcher) {
		return new Find(configuration, collection, matcher);
	}

	public Find find(String jsonMatcher) {
		return find(new JsonObject(jsonMatcher));
	}

	public Find find(String jsonMatcher, String... args) {
		return find(new JsonObject(getMatcher(jsonMatcher, args)));
	}

	public Find find() {
		return find(new JsonObject());
	}

	public FindOne findOne(JsonObject matcher) {
		return new FindOne(configuration, collection, matcher);
	}

	public FindOne findOne() {
		return findOne(new JsonObject());
	}

	public FindOne findOne(String jsonMatcher) {
		return findOne(new JsonObject(jsonMatcher));
	}

	public FindOne findOne(String jsonMatcher, String... args) {
		return findOne(new JsonObject(getMatcher(jsonMatcher, args)));
	}

	public Update update(JsonObject query) {
		return new Update(configuration, collection, query);
	}

	public Update update(String query) {
		return update(new JsonObject(query));
	}

	public Update update(String query, String... args) {
		return update(new JsonObject(getMatcher(query, args)));
	}

	public Count count() {
		return new Count(configuration, collection, new JsonObject());
	}

	public Count count(JsonObject query) {
		return new Count(configuration, collection, query);
	}

	public Count count(String query) {
		return count(new JsonObject(query));
	}

	public Count count(String query, String... args) {
		return count(new JsonObject(getMatcher(query, args)));
	}

	public Delete delete(JsonObject query) {
		return new Delete(configuration, collection, query);
	}

	public Delete delete(String query) {
		return delete(new JsonObject(query));
	}

	public Delete delete(String query, String... args) {
		return delete(new JsonObject(getMatcher(query, args)));
	}

	public Stats getCollectionStats() {
		return new Stats(configuration, collection);
	}

}
