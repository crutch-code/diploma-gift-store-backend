package ru.crutchcode.resources;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ru.crutchcode.dto.UserDto;
import ru.crutchcode.repository.UserRepository;

import java.io.IOException;

@ApplicationScoped
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    UserRepository userRepository;


    @POST
    @Path("/login")
    public Response login(UserDto dto) {
        return Response.ok(
                userRepository.auth(dto.getUsername(), dto.getPassword())
        ).build();
    }

    @POST
    @Path("/register")
    public Response register(UserDto userDto) throws IOException {
        return Response.ok(
                userRepository.register(userDto)
        ).build();
    }

}
