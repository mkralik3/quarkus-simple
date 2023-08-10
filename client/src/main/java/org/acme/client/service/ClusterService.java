package org.acme.client.service;

import org.acme.model.Deployment;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

import io.fabric8.kubernetes.api.model.PodBuilder;

@ApplicationScoped
public class ClusterService {

    public List<Deployment> getPods(String namespace) {
        List<Deployment> result = new ArrayList<Deployment>();
        //tmp, kubernetes client will be here
        result.add(
            new Deployment(
                new PodBuilder().withNewMetadata().withName("myPod").withNamespace(namespace).endMetadata().build(),
                "Running"));
        return result;
    }
}
