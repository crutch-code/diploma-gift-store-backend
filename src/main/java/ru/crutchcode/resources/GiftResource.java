package ru.crutchcode.resources;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ru.crutchcode.dto.Collection;
import ru.crutchcode.dto.Gift;
import ru.crutchcode.dto.GiftRelativity;
import ru.crutchcode.repository.GiftRepository;

import java.util.List;

@ApplicationScoped
@Path("/gifts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GiftResource {

    @Inject
    GiftRepository giftRepository;

    @GET
    public Response getGifts(@QueryParam("category") String category) {
        return category == null ?
                Response.ok(giftRepository.read()).build() :
                Response.ok(giftRepository.getGiftsByCategory(category)).build();
    }

    @PUT
    @Path("/relativity")
    public Response addRelativity(@QueryParam("id") String id, GiftRelativity body){
        return Response.ok(giftRepository.addRelativity(id, body)).build();
    }

    @PUT
    @Path("/relativity/approve")
    public Response approve(@QueryParam("id") String id, Collection body){
        return Response.ok(giftRepository.approve(id, body)).build();
    }

    @PUT
    @Path("/relativity/disapprove")
    public Response disapprove(@QueryParam("id") String id, Collection body){
        return Response.ok(giftRepository.disapprove(id, body)).build();
    }
}
