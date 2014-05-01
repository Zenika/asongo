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
