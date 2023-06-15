package org.acme.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.KubernetesClient;
@ApplicationScoped
public class ClusterService {

    private KubernetesClient kubernetesClient;

    @Inject
    public void setKubernetesClient(final KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }

    public PodList getPods(String namespace) {
        return kubernetesClient.pods().inNamespace(namespace).list();
    }

}
