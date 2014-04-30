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

package com.zenika.vertx.lib.asongo.mapper;

import com.zenika.vertx.lib.asongo.mapper.marshall.Marshaller;
import com.zenika.vertx.lib.asongo.mapper.marshall.Unmarshaller;
import org.jongo.ObjectIdUpdater;

/**
 * This interface should be implemented to override the default mapper
 * TODO make a builder to directly inject your own jackson mapper
 * @author M. Labusquière
 */
public interface Mapper {

	Marshaller getMarshaller();

	Unmarshaller getUnmarshaller();

	ObjectIdUpdater getObjectIdUpdater();

}
