package com.ranushan.service;

import com.ranushan.domain.Cat;

import java.util.List;

public interface CatService {
    List<Cat> getAllCatPictures();
    void voteCatPicture(String id);
}
