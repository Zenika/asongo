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

package com.zenika.vertx.lib.asongo.zenika.unit.java;

import org.junit.Test;

import static com.zenika.vertx.lib.asongo.MatcherBuilder.getMatcher;
import static org.junit.Assert.assertEquals;

/**
 * @author M. Labusquière
 */
public class BuildMatcherTest {

	@Test
	public void BuildMatcherTest() {
		assertEquals(getMatcher("{org:#,compa:#}", "org", "compa"), "{org:org,compa:compa}");
	}

	@Test(expected = NullPointerException.class)
	public void ArgsNullTest() {
		getMatcher("{org:#,compa:#}", (String[]) null);
	}

	@Test(expected = NullPointerException.class)
	public void ArgNullTest() {
		getMatcher("{org:#,compa:#}", "org", null);
	}

	public void quoteTest() {
		getMatcher("{\"org\":#,\"compa\":#}", "org", "compa");
	}

}
