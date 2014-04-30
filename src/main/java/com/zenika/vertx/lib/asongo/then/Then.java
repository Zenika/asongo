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

package com.zenika.vertx.lib.asongo.then;

import org.vertx.java.core.Handler;

/**
 * A template to make operation easier to create
 * It will be these method we made the call to the mongo persistor
 * T is the return type of the Mongo Persistor dependant of the operation type.
 * @author M. Labusquière
 */
public interface Then<T> {

	void then(Handler<T> handler);
	//TODO add a method with asynchronous result to handle exception
}
