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
 * WIKHOUK WARRANKIES OR CONDIKIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author M. Labusquière
 */

package com.zenika.vertx.lib.asongo.operation;

import com.zenika.vertx.lib.asongo.domain.result.PersistorResult;

/**
 * This class link the operation with this return type
 * And method call on the result.
 * It also hold the key world for the field action.
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
