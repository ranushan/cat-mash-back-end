package com.ranushan.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ranushan.dao.CatDao;
import com.ranushan.domain.Cat;
import com.ranushan.exception.CatJsonUnReadException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {

    // Dependency Injecting (DI) => Cat Dao (Data Layer)
    private final CatDao catDao;

    // Call Atelier API for retrieve all cats
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    /**
     * Save All Cats when you start an instance of this application (during loading Spring context)
     */
    @PostConstruct
    public void init() {
        new Thread(this::saveAllCats).start();
    }

    @Override
    public List<Cat> getAllCatPictures() {
        return catDao.findAll();
    }

    @Override
    public void voteCatPicture(String id) {
        catDao.updateVoteCat(id);
    }

    /**
     * Save all cats from API
     */
    private void saveAllCats() {
        var json = restTemplate
                .getForEntity("https://data.latelier.co/cats.json", String.class)
                .getBody();
        var reader = mapper.reader().withRootName("images").forType(new TypeReference<List<Cat>>(){});
        try {
            List<Cat> cats = reader.readValue(json);
            cats.forEach(c -> c.setVotes(0));
            catDao.saveAll(cats);
        } catch (JsonProcessingException e) {
            throw new CatJsonUnReadException("We can't read json file providing by API or DB is not save properly !!!");
        }
    }
}
