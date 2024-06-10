package ru.crutchcode.resources;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.ResponseStatus;
import ru.crutchcode.dto.Collection;
import ru.crutchcode.repository.CollectionRepository;

@ApplicationScoped
@Path("/collections")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CollectionResource {

    @Inject
    CollectionRepository collectionRepository;

    @GET
    public Response getCollectionByUser(@QueryParam("uid") String uid) {
        return Response.ok(collectionRepository.getCollectionsByUser(uid)).build();
    }

    @PUT
    public Response addCollectionByUser(@QueryParam("uid") String uid, Collection collection) {
        return Response.ok(collectionRepository.addCollection(uid, collection)).build();
    }
    @DELETE
    @ResponseStatus(200)
    public void getCollectionByUser(@QueryParam("uid") String uid, @QueryParam("collectionId") String collectionId) {
        collectionRepository.deleteCollection(uid, collectionId);
    }

}
