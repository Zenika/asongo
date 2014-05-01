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







/*
 * Example Groovy integration test that deploys the module that this project builds.
 *
 * Quite often in integration tests you want to deploy the same module for all tests and you don't want tests
 * to start before the module has been deployed.
 *
 * This test demonstrates how to do that.
 */

import static org.vertx.testtools.VertxAssert.*

// And import static the VertxTests script
import org.vertx.groovy.testtools.VertxTests;

// The test methods must being with "test"

def testPing() {
  container.logger.info("in testPing()")
  println "vertx is ${vertx.getClass().getName()}"
  vertx.eventBus.send("ping-address", "ping!", { reply ->
    assertEquals("pong!", reply.body())

    /*
    If we get here, the test is complete
    You must always call `testComplete()` at the end. Remember that testing is *asynchronous* so
    we cannot assume the test is complete by the time the test method has finished executing like
    in standard synchronous tests
    */
    testComplete()
  })
}

def testSomethingElse() {
  testComplete()
}

// Make sure you initialize
VertxTests.initialize(this)

// The script is execute for each test, so this will deploy the module for each one
// Deploy the module - the System property `vertx.modulename` will contain the name of the module so you
// don't have to hardecode it in your tests
container.deployModule(System.getProperty("vertx.modulename"), { asyncResult ->
  // Deployment is asynchronous and this this handler will be called when it's complete (or failed)
  assertTrue(asyncResult.succeeded)
  assertNotNull("deploymentID should not be null", asyncResult.result())
  // If deployed correctly then start the tests!
  VertxTests.startTests(this)
})



