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

    private final CatService catService;

    @GetMapping("cats")
    public List<Cat> getAllCatPictures() {
        return catService.getAllCatPictures();
    }

    @PostMapping("cats")
    public void voteCatPicture(@RequestBody String id) {
        catService.voteCatPicture(id);
    }

}
