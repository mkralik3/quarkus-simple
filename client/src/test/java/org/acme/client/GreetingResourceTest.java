package org.acme.client;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.client.server.mock.KubernetesServer;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.kubernetes.client.KubernetesTestServer;
import io.quarkus.test.kubernetes.client.WithKubernetesTestServer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.acme.client.api.GreetingResource;

import jakarta.ws.rs.core.MediaType;

@QuarkusTest
@WithKubernetesTestServer
@TestHTTPEndpoint(GreetingResource.class)
public class GreetingResourceTest {

    private static final String NAMESPACE = "default";

    @KubernetesTestServer
    KubernetesServer mockServer;

    @BeforeEach
    public void before() {
        final Pod pod1 = new PodBuilder().withNewMetadata().withName("pod1").withNamespace(NAMESPACE).and().build();
        final Pod pod2 = new PodBuilder().withNewMetadata().withName("pod2").withNamespace(NAMESPACE).and().build();
        // Set up Kubernetes so that our "pretend" pods are created
        mockServer.getClient().pods().create(pod1);
        mockServer.getClient().pods().create(pod2);
    }

    @AfterEach
    public void after() {
        mockServer.getClient().pods().inNamespace(NAMESPACE).list().getItems().forEach(
            pod -> mockServer.getClient().resource(pod).inNamespace(NAMESPACE).delete());
    }

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
            .body(is("2"));
    }
}