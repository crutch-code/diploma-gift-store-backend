package ru.crutchcode.resources;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import ru.crutchcode.dto.Gift;
import ru.crutchcode.repository.GiftRepository;

import java.util.List;

@ApplicationScoped
@Path("/gifts")
public class GiftResource {

    @Inject
    GiftRepository giftRepository;

    @GET
    public Response getGifts() {
        return Response.ok(giftRepository.read()).build();
    }
}
