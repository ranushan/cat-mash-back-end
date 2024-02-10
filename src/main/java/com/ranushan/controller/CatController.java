package com.ranushan.controller;

import com.ranushan.domain.Cat;
import com.ranushan.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CatController {

    // Dependency Injection (DI) => Cat Service (Business Layer)
    private final CatService catService;

    /**
     * Get All Cat picture from Database
     * @return List of Cat (id, url, votes)
     */
    @GetMapping("cats")
    public List<Cat> getAllCatPictures() {
        return catService.getAllCatPictures();
    }

    /**
     * Voting for your favorite cat => Update Cat model
     * @param id Identification Cat Picture
     */
    @PostMapping("cats")
    public void voteCatPicture(@RequestBody String id) {
        catService.voteCatPicture(id);
    }

}
