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

/**
 * Simple integration test which shows tests deploying other verticles, using the Vert.x API etc
 */

var container = require("vertx/container")
var vertx = require("vertx");
var vertxTests = require("vertx_tests");
var vassert = require("vertx_assert");
var console = require("vertx/console");

// The test methods must begin with "test"

function testHTTP() {
  // Create an HTTP server which just sends back OK response immediately
  vertx.createHttpServer().requestHandler(function(req) {
    req.response.end();
  }).listen(8181, function(err) {
    vassert.assertNull(err);
    // The server is listening so send an HTTP request
    vertx.createHttpClient().port(8181).getNow("/", function(resp) {
      vassert.assertTrue(200 == resp.statusCode());
      /*
       If we get here, the test is complete
       You must always call `testComplete()` at the end. Remember that testing is *asynchronous* so
       we cannot assume the test is complete by the time the test method has finished executing like
       in standard synchronous tests
       */
      vassert.testComplete();
    })
  })
}

/*
 This test deploys some arbitrary verticle - note that the call to testComplete() is inside the Verticle `SomeVerticle`
 */
function testDeployArbitraryVerticle() {
  container.deployVerticle("fr.zenika.integration.java.SomeVerticle");
}

function testCompleteOnTimer() {
  vertx.setTimer(1000, function(timerID) {
    vassert.assertNotNull(timerID)

    // This demonstrates how tests are asynchronous - the timer does not fire until 1 second later -
    // which is almost certainly after the test method has completed.
    vassert.testComplete()
  });
}

vertxTests.startTests(this);






