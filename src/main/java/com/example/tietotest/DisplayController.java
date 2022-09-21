package com.example.tietotest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
// this controller interacts with somesort of tablet on player side
public class DisplayController {

    private Game game;
    private List<String> names = new LinkedList<>();
    @Autowired
    public DisplayController(Game game){
        this.game = game;
    }

    @GetMapping("/addName")
    public void submitPins(@RequestParam String name){
        names.add(name);
    }

    @GetMapping("/start")
    public void start(){
        game.init(names);
        names.clear();
    }
    @GetMapping("/getPlayerTable")
    public List<Frame> getTable(@RequestParam String name){
        return game.getTableForPlayer(name);
    }

    @GetMapping("/getScores")
    public Map<String,Integer> getScores(){
        return game.getCurrentScores();
    }
}
