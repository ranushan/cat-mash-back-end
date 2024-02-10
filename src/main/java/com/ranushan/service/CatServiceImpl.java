package com.ranushan.service;

import com.ranushan.dao.CatDao;
import com.ranushan.domain.Cat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatDao catDao;

    @Override
    public List<Cat> getAllCatPictures() {
        return catDao.findAll();
    }

    @Override
    public void voteCatPicture(String id) {
        catDao.updateVoteCat(id);
    }
}
