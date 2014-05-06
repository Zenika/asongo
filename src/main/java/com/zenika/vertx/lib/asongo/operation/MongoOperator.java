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

import com.zenika.vertx.lib.asongo.domain.result.PersistorResult;

/**
 * This class link the operation with this return type
 * And method call on the result.
 * It also hold the key world for the field action.
 *
 * @author M. Labusquière
 */
public enum MongoOperator {
	SAVE("save") {
		@Override
		public <T> T getResult(PersistorResult presult) {
			return (T) presult.getIdCreated();
		}
	},
	UPDATE("update") {
		@Override
		public <T> T getResult(PersistorResult presult) {
			return null;
		}
	},

	COUNT("count") {
		@Override
		public <T> T getResult(PersistorResult presult) {
			return (T) presult.getCount();
		}
	},
	DELETE("delete") {
		@Override
		public <T> T getResult(PersistorResult presult) {
			return (T) presult.getDeletedDocumentSize();
		}
	},
	GET_COLLECTIONS("get_collections") {
		@Override
		public <T> T getResult(PersistorResult presult) {
			return (T) presult.getCollections();
		}
	},
	DB_STATS("collection_stats") {
		@Override
		public <T> T getResult(PersistorResult presult) {
			return (T) presult.getStats();
		}
	},
	COMMAND("command") {
		@Override
		public <T> T getResult(PersistorResult presult) {
			return (T) presult.getResultCommand();
		}
	},
	DROP_COLLECTION("drop_collection") {
		@Override
		public <T> T getResult(PersistorResult presult) {
			return null;
		}
	},
	/**
	 * Since the mongo persistor is not supporting Map Reduce we use command
	 */
	MAP_REDUCE("command") {
		@Override
		public <T> T getResult(PersistorResult presult) {
			return (T) presult.getResultCommand();
		}
	};

	private final String operator;

	private MongoOperator(String operator) {
		this.operator = operator;
	}

	public String fieldName() {
		return operator;
	}

	public abstract <T> T getResult(PersistorResult presult);
}
