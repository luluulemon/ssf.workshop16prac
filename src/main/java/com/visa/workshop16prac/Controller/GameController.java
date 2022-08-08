package com.visa.workshop16prac.Controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.visa.workshop16prac.Model.Game;
import com.visa.workshop16prac.Service.RedisService;

@RestController
@RequestMapping(path="/game", consumes="application/json", produces="application/json")
public class GameController {
    
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    @Autowired
    RedisService service;


    @PostMapping
    public ResponseEntity<Game> SaveGame(@RequestBody Game game){
        
        logger.info("Inside Controller >>>> get name" + game.getName());
        service.saveGame(game);

        return ResponseEntity.ok(game);
    }


    @GetMapping(path = "/{GameId}")
    public ResponseEntity<Game> getGame(@PathVariable String GameId){
        
        logger.info("Inside Controller GET >>>>> " + GameId);
        Boolean idExists = service.checkId(GameId);
        logger.info("Inside GET >>> get boolean " + idExists);
        if(idExists){
            Game game = service.loadGame(GameId);
            //logger.info("Inside GET >>> get name " + game.getName());
            return ResponseEntity.ok(game);
        }
        
        return null;
    }


    @PutMapping(path = "/{GameId}")
    public ResponseEntity<Game> updateGame(@PathVariable String GameId, @RequestBody Game game,
        @RequestParam Optional<Boolean> upSert){

        logger.info("INside PUT >>>> check UPSERT " + upSert);
        
        // if upSert is false, and Id does not exist --> do not create
        if(!upSert.get() || service.checkId(GameId))
        {       return null;                                    }
        else    // if upSert is true, no need check for existing
        {   service.UpdateGame(game, GameId);   
            return ResponseEntity.ok(game);
        }
    }


}
