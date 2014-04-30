
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

import com.zenika.vertx.lib.asongo.mapper.Mapper;
import com.zenika.vertx.lib.asongo.mapper.marshall.jackson.JacksonMapper;
import org.vertx.java.core.eventbus.EventBus;

/**
 * Holder for the configuration
 * @author M. Labusquière
 */
public class AsongoConfiguration {

	private final static String MONGO_PERSISTOR_ADRESS = "vertx.mongopersistor";

	private final Mapper mapper;
	private final EventBus eventBus;
	private final String mongoPersistorAdress;

	AsongoConfiguration(EventBus eventBus) {
		this(eventBus,new JacksonMapper(),MONGO_PERSISTOR_ADRESS);
	}

	AsongoConfiguration(EventBus eventBus, String mongoPersistorAdress) {
		this(eventBus,new JacksonMapper(),mongoPersistorAdress);
	}

	AsongoConfiguration(EventBus eventBus, Mapper mapper) {
		this(eventBus,mapper,MONGO_PERSISTOR_ADRESS);
	}

	AsongoConfiguration(EventBus eventBus,Mapper mapper,String mongoPersistorAdress) {
		this.eventBus = eventBus;
		this.mapper = mapper;
		this.mongoPersistorAdress = mongoPersistorAdress;
	}

	public Mapper getMapper() {
		return mapper;
	}

	public EventBus getEventBus() {
		return eventBus;
	}

	public String getMongoPersistorAdress() {
		return mongoPersistorAdress;
	}
}
