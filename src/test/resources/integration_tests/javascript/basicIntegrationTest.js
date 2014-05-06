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
    vertx.createHttpServer().requestHandler(function (req) {
        req.response.end();
    }).listen(8181, function (err) {
        vassert.assertNull(err);
        // The server is listening so send an HTTP request
        vertx.createHttpClient().port(8181).getNow("/", function (resp) {
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
    vertx.setTimer(1000, function (timerID) {
        vassert.assertNotNull(timerID)

        // This demonstrates how tests are asynchronous - the timer does not fire until 1 second later -
        // which is almost certainly after the test method has completed.
        vassert.testComplete()
    });
}

vertxTests.startTests(this);






