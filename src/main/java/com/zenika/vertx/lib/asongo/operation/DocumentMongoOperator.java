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
