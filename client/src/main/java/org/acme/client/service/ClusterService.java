package org.acme.client.service;

import org.acme.model.Deployment;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.KubernetesClient;
@ApplicationScoped
public class ClusterService {

    private KubernetesClient kubernetesClient;

    @Inject
    public void setKubernetesClient(final KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }

    public List<Deployment> getPods(String namespace) {
        List<Deployment> result = new ArrayList<Deployment>();
        kubernetesClient.pods().inNamespace(namespace).list().getItems().forEach(
            pod -> {
                result.add(new Deployment(pod, "Exist"));
            }
        );
        return result;
    }

}
