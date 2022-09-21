package com.example.tietotest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
// Lets pretend that machine consists of two parts one that counts pins
public class InputController {

    private Game game;
    @Autowired
    public InputController(Game game){
        this.game = game;
    }

    @GetMapping("/pins")
    public String submitPins(@RequestParam Integer pins){
        return game.recieveScore(pins);
    }
}
