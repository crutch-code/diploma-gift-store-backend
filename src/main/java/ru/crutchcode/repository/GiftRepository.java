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


    private final static String PATH = "R:/git-repositories/myself-repositories/diploma-gift-store-back/database/gifts.json";

    List<Gift> collections;

//    GiftRelativity getDefaultRelativity() {
//    }

    void onStart(@Observes StartupEvent ev) throws IOException {
        collections =new ObjectMapper().readValue(
                new File(PATH), new TypeReference<>() {
                }
        );
        collections.forEach(g -> {
            if(g.getId().equals("1")){
                g.setId(UUID.randomUUID().toString());
            }
        });
        write();
    }

//    private List<Gift> getGiftsByCategory(String category, String){
//        collections.stream().filter(f-> {
//           f.getRelativity().stream().filter(rf-> rf.getCategory().equals(category)).
//        });
//    }

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
