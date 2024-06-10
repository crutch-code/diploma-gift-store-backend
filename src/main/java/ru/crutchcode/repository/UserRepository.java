package ru.crutchcode.repository;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.security.AuthenticationFailedException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import ru.crutchcode.dto.UserDto;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserRepository {

//    private final static String PATH = "R:/git-repositories/myself-repositories/diploma-gift-store-back/database/users.json";
    private final static String PATH = "C:\\Users\\Boris.Stupin\\self-repositories\\diploma-gift-store-backend\\database\\users.json";

    List<UserDto> users;



    void onStart(@Observes StartupEvent ev) throws IOException {
        users = new ObjectMapper().readValue(
                new File(PATH), new TypeReference<List<UserDto>>() {}
        );
    }

    public UserDto auth(String name, String password) {
        return users
                .stream()
                .filter(p -> p.getUsername().equals(name) && p.getPassword().equals(password))
                .findFirst()
                .orElseThrow(AuthenticationFailedException::new);
    }

    public UserDto register(UserDto user) {
        users.add(
                user.setId(
                        UUID.randomUUID().toString()
                ).setJwt(
                        "asdfasdf"
                )
        );
        try {
            new ObjectMapper().writeValue(new File(PATH), users);
            return user;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
