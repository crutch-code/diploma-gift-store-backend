package ru.crutchcode.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import ru.crutchcode.dto.Collection;
import ru.crutchcode.dto.Gift;
import ru.crutchcode.dto.GiftComparator;
import ru.crutchcode.dto.GiftRelativity;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class GiftRepository {


//    private final static String PATH = "R:/git-repositories/myself-repositories/diploma-gift-store-back/database/gifts.json";
    private final static String PATH = "C:\\Users\\Boris.Stupin\\self-repositories\\diploma-gift-store-backend\\database\\gifts.json";

    List<Gift> collections;

//    GiftRelativity getDefaultRelativity() {
//    }

    void onStart(@Observes StartupEvent ev) throws IOException {
        collections =new ObjectMapper().readValue(
                new File(PATH), new TypeReference<>() {
                }
        );
//        collections.forEach(g -> {
//            if(g.getId().equals("1")){
//                g.setId(UUID.randomUUID().toString());
//            }
//        });
//        write();
    }

    public Gift approve(String id, Collection collection){
        var target = collections.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow();
        target.getRelativity()
                .stream()
                .filter(p -> p.getCategory().equals(collection.getCategory()))
                .findFirst()
                .ifPresentOrElse(
                        gift -> {
                            gift.setAssignCount(gift.getAssignCount() + 1);
                            gift.setCountScore(gift.getCountScore() + 1);
                            gift.setPercents(
                                    (double) Math.round(
                                            ((double) gift.getAssignCount() /gift.getCountScore()) * 100
                                    )
                            );
                        },
                        () -> {
                            target.getRelativity().add(
                                    new GiftRelativity()
                                            .setCategory(collection.getCategory())
                                            .setAges(collection.getAge())
                                            .setPercents(100.0)
                                            .setCountScore(1)
                                            .setDisapproveScore(0)
                                            .setAssignCount(1)
                            );
                        }
                );
        write();
        return target;
    }

    public Gift disapprove(String id, Collection collection){
        var target = collections.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow();
        target.getRelativity()
                .stream()
                .filter(p -> p.getCategory().equals(collection.getCategory()))
                .findFirst()
                .ifPresentOrElse(
                        gift -> {
                            gift.setCountScore(gift.getCountScore() + 1);
                            gift.setDisapproveScore(gift.getDisapproveScore() +1);
                            gift.setPercents(
                                    (double) Math.round(
                                            ((double) gift.getAssignCount() /gift.getCountScore()) * 100
                                    )
                            );
                        },
                        () -> {
                            target.getRelativity().add(
                                    new GiftRelativity()
                                            .setCategory(collection.getCategory())
                                            .setAges(collection.getAge())
                                            .setPercents(0.0)
                                            .setCountScore(1)
                                            .setDisapproveScore(1)
                                            .setAssignCount(0)
                            );
                        }
                );
        write();
        return target;
    }

    public Gift addRelativity(String id, GiftRelativity relativity){
        var target = collections.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow();

        target.getRelativity()
                .stream()
                .filter(p -> p.getCategory().equals(relativity.getCategory()))
                .findFirst()
                .ifPresentOrElse(
                        gift -> {
                            gift.setCountScore(relativity.getCountScore())
                                    .setDisapproveScore(relativity.getDisapproveScore())
                                    .setAssignCount(relativity.getAssignCount());
                        },
                        () -> {
                            target.getRelativity().add(relativity);
                        }
                );
        write();
        return target;
    }

    public List<Gift> getGiftsByCategory(String category){
        return collections.stream().filter(
                f-> f.getRelativity().stream().anyMatch(rf-> rf.getCategory().equals(category))
                ).sorted(new GiftComparator(category)).toList();
    }

    public List<Gift> read() {
        return collections;
    }

    private void write() {
        try {
            new ObjectMapper().writeValue(new File(PATH), collections);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
