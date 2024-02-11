package com.ranushan.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ranushan.dao.CatDao;
import com.ranushan.domain.Cat;
import com.ranushan.exception.CatJsonUnReadException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {

    // Dependency Injecting (DI) => Cat Dao (Data Layer)
    private final CatDao catDao;

    // Call Atelier API for retrieve all cats
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    // URL API Atelier to get all cats
    private static final String URL = "https://data.latelier.co/cats.json";
    private static final String ROOT_NAME = "images";

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
        log.info("==========> START CALL API Atelier ==========");
        var json = restTemplate
                .getForEntity(URL, String.class)
                .getBody();
        var reader = mapper.reader().withRootName(ROOT_NAME).forType(new TypeReference<List<Cat>>(){});
        try {
            List<Cat> cats = reader.readValue(json);
            checkIfCatIsPresent(cats);
            log.info("========== END CALL API Atelier WITH SAVING <==========");
        } catch (JsonProcessingException e) {
            throw new CatJsonUnReadException("We can't read json file providing by API or DB is not save properly !!!");
        }
    }

    private void checkIfCatIsPresent(List<Cat> cats) {
        var allCatPictures = getAllCatPictures();
        var newCats = cats.stream()
                .filter(c -> allCatPictures.stream().noneMatch(alp -> c.getId().equals(alp.getId())))
                .collect(Collectors.toList());
        log.info("========== SHOULD ADDING {} element{} <==========", newCats.size(), newCats.size() > 1 ? "s" : "");
        if(!newCats.isEmpty()) {
            newCats.forEach(c -> c.setVotes(0));
            catDao.saveAll(newCats);
        }
    }
}
