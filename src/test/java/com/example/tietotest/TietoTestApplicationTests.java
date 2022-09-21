package com.example.tietotest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest
class TietoTestApplicationTests {

    @Autowired
    Game game;
    @Test
    void testSinglePlayerScenario() {
        List<String> names = new LinkedList<>();
        names.add("Player 1");
        game.init(names);
        Integer[] results = new Integer[]{10,7,3,7,2,9,1,10,10,10,2,3,6,4,7,3,3};
        for (Integer pins:results){
            game.recieveScore(pins);
        }
        Assert.isTrue(game.getCurrentScores().get("Player 1")==168,"Scores do not match");
        Integer[] frameScores = new Integer[]{20,17,9,20,30,22,15,5,17,13};
        //Integer[] totalScores = new Integer[]{20,37,46,66,96,118,133,138,155,168};
        List<Frame> scores  = game.getTableForPlayer("Player 1");
        for(int i=0;i<10;i++){
            Assert.isTrue(scores.get(i).getFrameScore() == frameScores[i],"Frame score does not match "+ scores.get(i).getFrameScore() + " vs " + frameScores[i] );
        }
    }

    @Test
    void testMultiPlayerScenario() {
        List<String> names = new LinkedList<>();
        names.add("Player 1");
        names.add("Player 2");
        game.init(names);
        Integer[] results = new Integer[]{10,10 ,7,3,7,3 ,7,2,7,2 ,9,1,9,1 ,10,10 ,10,10 ,10,10 ,2,3,2,3 ,6,4,6,4 ,7,3,3 ,7,3,3};
        for (Integer pins:results){
            game.recieveScore(pins);
        }
        Assert.isTrue(game.getCurrentScores().get("Player 1")==168,"Scores do not match");
        Integer[] frameScores = new Integer[]{20,17,9,20,30,22,15,5,17,13};
        //Integer[] totalScores = new Integer[]{20,37,46,66,96,118,133,138,155,168};
        List<Frame> scores  = game.getTableForPlayer("Player 1");
        for(int i=0;i<10;i++){
            Assert.isTrue(scores.get(i).getFrameScore() == frameScores[i],"Frame score does not match "+ scores.get(i).getFrameScore() + " vs " + frameScores[i] );
        }
    }

}
