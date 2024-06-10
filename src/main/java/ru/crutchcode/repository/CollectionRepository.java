package ru.crutchcode.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import ru.crutchcode.dto.Collection;
import ru.crutchcode.dto.UserDto;

import java.io.File;
import java.io.IOException;
import java.util.*;

@ApplicationScoped
public class CollectionRepository {

    private final static String PATH = "R:/git-repositories/myself-repositories/diploma-gift-store-back/database/collections-by-user.json";

    Map<String, List<Collection>> collections;

    void onStart(@Observes StartupEvent ev) throws IOException {
        collections = new HashMap<>(new ObjectMapper().readValue(
                new File(PATH), new TypeReference<Map<String, List<Collection>>>() {}
        ));
    }

    public List<Collection> getCollectionsByUser(String uid) {
        return collections.get(uid);
    }

    public Collection addCollection(String uid, Collection collection) {
        var userCollections = Optional.ofNullable(collections.get(uid)).orElse(new ArrayList<>());
        userCollections.add(collection.setId(UUID.randomUUID().toString()));
        collections.put(uid, userCollections);
        write();
        return collection;
    }

    public void deleteCollection(String uid, String collectionId) {
        Optional.ofNullable(collections.get(uid))
                .ifPresent(
                        p -> p.removeIf(f -> f.getId().equals(collectionId))
                );
        write();
    }

    private void write() {
        try {
            new ObjectMapper().writeValue(new File(PATH), collections);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
