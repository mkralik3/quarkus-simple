package org.acme.client.api;

import org.acme.client.service.ClusterService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
        return clusterService.getPods(namespace).size();
    }
}