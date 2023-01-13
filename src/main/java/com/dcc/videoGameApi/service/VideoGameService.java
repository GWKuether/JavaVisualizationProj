package com.dcc.videoGameApi.service;

import com.dcc.videoGameApi.models.VideoGame;
import com.dcc.videoGameApi.repository.VideoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
public class VideoGameService {

    @Autowired
    private VideoGameRepository videoGameRepository;

    public long GetCountOfGames(){
        return videoGameRepository.count();
    }

    public List<VideoGame> GetAllVGs() {
        return videoGameRepository.findAll().stream().toList();
    }

    public Optional<VideoGame> getByVGId(Integer id){
        return videoGameRepository.findById(id);
    }

    public HashMap<String, Double> GetAllPlatformSales(){
        List<String> allPlatforms = videoGameRepository.findAll().stream().filter(y ->y.getYear() >= 2013).map(p -> p.getPlatform()).distinct().collect(Collectors.toList());
        HashMap<String, Double> salesPerPlatform = new HashMap<>();
        for (String platform: allPlatforms)
        salesPerPlatform.put(platform, videoGameRepository.findAll().stream().filter(y ->y.getYear() >= 2013).filter(p -> p.getPlatform().equals(platform)).map(g -> g.getGlobalsales()).reduce((double)0, (e1,e2) -> e1 + e2));
        return salesPerPlatform;
    }
    public HashMap<String,Double> GetGenreSales(){
        List<String> allgenres = videoGameRepository.findAll().stream().filter(y -> y.getYear() >= 2003).map(g -> g.getGenre()).distinct().collect(Collectors.toList());
        HashMap<String,Double> SalesPerGenre = new HashMap<>();
        for (String genre : allgenres)
            SalesPerGenre.put(genre, videoGameRepository.findAll().stream().filter(y -> y.getYear() >= 2003).filter(g -> g.getGenre().equals(genre)).map(g -> g.getGlobalsales()).reduce((double)0, (e1,e2) -> e1 + e2));
        return SalesPerGenre;
    }

//    public HashMap<String,Double> GetSalesPerPlatform(){
//        List<String> allPlatforms = videoGameRepository.findAll().stream().map(p -> p.getPlatform()).distinct().collect(Collectors.toList());
//        List<String> allPublishers = videoGameRepository.findAll().stream().map(p -> p.getPublisher()).distinct().collect(Collectors.toList());
//        HashMap<String,Double> SalesPerPublisher = new HashMap<>();
//        for (String platform : allPlatforms)
//            SalesPerPublisher.put(platform, videoGameRepository.findAll().stream().map(p -> p.getPublisher()).distinct().collect(Collectors.toList());
//
//
//
//    }
}


//videoGameRepository.findAll().stream().filter(p ->p.getPublisher()).map(g ->g.getGlobalsales()).reduce(double)0, (e1,e2) -> e1 + e2));
