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

package com.zenika.vertx.lib.asongo;

import org.bson.types.ObjectId;
import org.vertx.java.core.json.JsonObject;

/**
 * Util class to query with an objectId as Id
 * TODO make marshaller supporting bson
 * Useless for the moment
 * @author M. Labusquière
 */
public class IdUtil {

	public static final String oid = "$oid";

	public static JsonObject byObjectId(String idName, ObjectId id) {
		return new JsonObject().putObject(idName, new JsonObject().putString("$oid", id.toString()));
	}
}
