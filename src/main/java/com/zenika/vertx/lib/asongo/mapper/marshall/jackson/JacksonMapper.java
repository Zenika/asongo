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

package com.zenika.vertx.lib.asongo.mapper.marshall.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zenika.vertx.lib.asongo.mapper.Mapper;
import com.zenika.vertx.lib.asongo.mapper.marshall.Marshaller;
import com.zenika.vertx.lib.asongo.domain.result.DocumentResult;
import com.zenika.vertx.lib.asongo.mapper.marshall.Unmarshaller;
import org.jongo.ObjectIdUpdater;
import org.jongo.ReflectiveObjectIdUpdater;
import org.jongo.marshall.MarshallingException;
import org.jongo.marshall.jackson.JacksonIdFieldSelector;
import org.vertx.java.core.json.JsonObject;

import java.io.IOException;

/**
 * @author M. Labusquière
 */
public class JacksonMapper implements Mapper {

	//TODO Seems useless
	private final ObjectIdUpdater idUpdater = new ReflectiveObjectIdUpdater(new JacksonIdFieldSelector());
	private final ObjectMapper mapper = MapperFactory.getMapper();

	@Override
	public Marshaller getMarshaller() {
		return new Marshaller() {
			@Override
			public JsonObject marshall(Object pojo) throws MarshallingException {
				try {
					return new JsonObject(mapper.writeValueAsString(pojo));
				} catch (JsonProcessingException e) {
					throw new MarshallingException("Error durring the marshalling ", e);
				}
			}
		};
	}

	@Override
	public Unmarshaller getUnmarshaller() {
		return new Unmarshaller() {
			@Override
			public <T> T unmarshall(String jsonDocument, Class<T> clazz) throws MarshallingException {

				if(jsonDocument == null || jsonDocument.isEmpty() )
					return null;

				try {
					return mapper.readValue(jsonDocument, clazz);
				} catch (IOException e) {
					throw new MarshallingException("Error durring the unmarshalling ", e);
				}
			}

			@Override
			public <T> T unmarshall(String jsonDocument, Class<T> clazz, Class... generic) throws MarshallingException {

				if(jsonDocument == null || jsonDocument.isEmpty() )
					return null;

				try {
					JavaType typedClass = mapper.getTypeFactory().constructParametricType(DocumentResult.class, generic);
					return mapper.readValue(jsonDocument, typedClass);

				} catch (IOException e) {
					throw new MarshallingException("Error durring the unmarshalling ", e);
				}
			}
		};
	}

	@Override
	public ObjectIdUpdater getObjectIdUpdater() {
		return idUpdater;
	}
}
