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
