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

import java.util.Collection;

/**
 * Result of operations with a generified type (not generic)
 * Used in the {@link com.zenika.vertx.lib.asongo.then.DocumentThenTemplate}
 * //TODO should discuss about 1 result type different 1 class different
 *
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
