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

package com.zenika.vertx.lib.asongo.operation;

import com.zenika.vertx.lib.asongo.domain.result.DocumentResult;

/**
 * @author M. Labusquière
 */
public enum DocumentMongoOperator {
	FIND("find") {
		@Override
		public <T,K> T getResult(DocumentResult<K> presult) {
			return (T) presult.getResults();
		}
	},
	FINDONE("findone") {
		@Override
		public <T,K> T getResult(DocumentResult<K> presult) {
			return (T) presult.getResult();
		}
	},
	FIND_AND_MODIFY("find_and_modify") {
		@Override
		public <T,K> T getResult(DocumentResult<K> presult) {
			return (T) presult.getResult();
		}
	};

	private final String operator;

	private DocumentMongoOperator(String operator) {
			this.operator = operator;
	}

	public String fieldName() {
		return operator;
	}

	public abstract <T,K> T getResult(DocumentResult<K> presult);
}
