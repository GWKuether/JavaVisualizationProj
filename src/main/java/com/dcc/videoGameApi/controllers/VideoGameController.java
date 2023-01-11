package com.dcc.videoGameApi.controllers;

import com.dcc.videoGameApi.models.VideoGame;
import com.dcc.videoGameApi.service.VideoGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class VideoGameController {

    @Autowired
    private VideoGameService service;

    //Example endpoint to return count of all games on DB. Should return 10000 in Postman
    @GetMapping("/count")
    public long GetCount(){
        return service.GetCountOfGames();
    }

    @GetMapping("/all")
    public List<VideoGame> getVGs(){
        return service.GetAllVGs();
    }

    @GetMapping("getById/{id}")
    public Optional<VideoGame> getById(@PathVariable Integer id){
        return service.getByVGId(id);
    }

    @GetMapping("/platformsales")
    public HashMap<String, Double> getPlatforms(){
        return service.GetAllPlatformSales();
    }

}


