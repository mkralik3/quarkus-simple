package org.acme.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class DeploymentTest {

    @Test
    public void testValidation() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Deployment(null, "status");
        });
    }

}
