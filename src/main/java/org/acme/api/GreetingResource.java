package org.acme.api;

import org.acme.service.ClusterService;

import jakarta.inject.Inject;
import jakarta.json.JsonArray;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodList;

@Path("/hello")
public class GreetingResource {

    private ClusterService clusterService;

    @Inject
    public void setClusterService(final ClusterService clusterService) {
        this.clusterService = clusterService;
    }

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("/{namespace}/pods")
    @Produces(MediaType.TEXT_PLAIN)
    public int getNumberOfPodsInDefaultNamespace(@PathParam("namespace") String namespace) {
        return clusterService.getPods(namespace).getItems().size();
    }
}