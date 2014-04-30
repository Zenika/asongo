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

import java.util.Collection;

/**
 * Result of operations with a generified type (not generic)
 * Used in the {@link com.zenika.vertx.lib.asongo.then.DocumentThenTemplate}
 * //TODO should discuss about 1 result type different 1 class different
 * @author M. Labusquière
 */
public class DocumentResult<T> extends ErrorResult {
	/**
	 * Operation : find
	 * Number of results (useless in java)
	 */
	private int number;
	/**
	 * Operation : find
	 * Results with the type T specified in a as method
	 */
	private Collection<T> results; // When the response can have many result
	/**
	 * Result with type T specified in a as method
	 */
	private T result; // When the response can have an unique result

	public DocumentResult() {
	}

	public Collection<T> getResults() {
		return results;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setResults(Collection<T> results) {
		this.results = results;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public T getResult() {
		return result;
	}


	public Integer getDeletedDocumentSize() {
		return number;
	}

	@Override
	public String toString() {
		return "PersistorResult{" +
				"number=" + number +
				", results=" + results +
				", result=" + result +
				'}';
	}

}
