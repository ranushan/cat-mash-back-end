package com.ranushan.service;

import com.ranushan.domain.Cat;

import java.util.List;

public interface CatService {
    /**
     * Get All picture of cat
     * @return List all cats
     */
    List<Cat> getAllCatPictures();

    /**
     * Update picture vote by id
     * @param id Identification of picture
     */
    void voteCatPicture(String id);
}
