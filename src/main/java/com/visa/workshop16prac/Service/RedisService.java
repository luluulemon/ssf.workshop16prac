package com.visa.workshop16prac.Service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.visa.workshop16prac.Model.Game;

@Service
public class RedisService {
    private static final Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    RedisTemplate<String, Object> template;

    

    public void saveGame(Game game){
        game.generateId();
        logger.info("Inside Controller >>>> get name " + game.getName());
        template.opsForValue().set(game.getId(), game);

        template.opsForValue().get("insertCount");
    }

    public Game loadGame(String id){
        logger.info("Inside Service >>>> check id" + id);
        Game game = (Game)template.opsForValue().get(id);
        return game; 
    }


    public Boolean checkId(String id){
        return template.hasKey(id);
    }


    public void UpdateGame(Game game, String id){

        game.setId(id);
        template.opsForValue().set(id, game);
        
        Game result = (Game)template.opsForValue().get(id); // check entry
        logger.info("Inside Service >>>> check name " + result.getName());
    }

}
