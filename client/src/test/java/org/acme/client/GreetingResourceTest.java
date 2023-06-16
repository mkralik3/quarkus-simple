package org.acme.client;

import static org.hamcrest.CoreMatchers.is;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import org.acme.client.api.GreetingResource;

import javax.ws.rs.core.MediaType;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestHTTPEndpoint(GreetingResource.class)
public class GreetingResourceTest {

    private static final String NAMESPACE = "default";

    @Test
    public void testHelloEndpoint() {
        given()
            .when().get()
            .then()
            .statusCode(200)
            .body(is("Hello from RESTEasy Reactive"));
    }

    @Test
    public void testNamespaceEndpoint() {
        given()
            .when().get("/" + NAMESPACE + "/pods")
            .then()
            .statusCode(200)
            .contentType(MediaType.TEXT_PLAIN)
            .body(is("1"));
    }
}