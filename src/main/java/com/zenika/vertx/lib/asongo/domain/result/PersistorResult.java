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

package com.zenika.vertx.lib.asongo.domain.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zenika.vertx.lib.asongo.domain.MongoStats;
import org.vertx.java.core.json.JsonObject;

import java.util.Collection;
import java.util.HashMap;

/**
 * Result of operations with a predefined type (not generic)
 * Used in the {@link com.zenika.vertx.lib.asongo.then.BasicThenTemplate}
 * //TODO should discuss about 1 result type different 1 class different
 *
 * @author M. Labusquière
 */
public class PersistorResult extends ErrorResult {

	/**
	 * Operation : delete
	 * Number of dropped file
	 */
	private int number;
	/**
	 * Operation : save (When insertion)
	 * Id when the mongo persitor generate an id
	 */
	@JsonProperty("_id")
	private String idCreated;
	/**
	 * Operation : count
	 * Number of file matching the query
	 */
	private int count;
	/**
	 * Operation : get_collections
	 * Name of collections in the db
	 */
	private Collection<String> collections;
	/**
	 * Operation : db_stats
	 * Statistique on the db
	 */
	private MongoStats stats;
	/**
	 * Operation : command
	 * Result of the command operation
	 * TODO check if it should not be a string
	 */
	@JsonProperty("result")
	private JsonObject resultCommand;

	public void setResultCommand(HashMap result) {
		this.resultCommand = new JsonObject(result);
	}

	public JsonObject getResultCommand() {
		return resultCommand;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getIdCreated() {
		return idCreated;
	}

	public void setIdCreated(String idCreated) {
		this.idCreated = idCreated;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setCollections(Collection<String> collections) {
		this.collections = collections;
	}

	public MongoStats getStats() {
		return stats;
	}

	public void setStats(MongoStats stats) {
		this.stats = stats;
	}

	public Integer getDeletedDocumentSize() {
		return number;
	}

	public Collection<String> getCollections() {
		return collections;
	}

	@Override
	public String toString() {
		return "PersistorResult{" +
				"number=" + number +
				", idCreated='" + idCreated + '\'' +
				", count=" + count +
				", collections=" + collections +
				", stats=" + stats +
				", resultCommand=" + resultCommand +
				'}';
	}
}
