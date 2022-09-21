package com.example.tietotest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Scope("singleton")
public class Game {
    Map<String, Integer> players = Collections.synchronizedMap(new HashMap<>());

    // we could use a 1 dimensional list of objects which store only players stats
    // change it to lists of subscribed frames which are removed after n data
    List<Frame>[] frames;
    List<String> playerNames;
    String active;


    public String init(List<String> playerNames) {
        this.playerNames = playerNames;
        frames = new List[playerNames.size()];
        for (int i = 0; i < playerNames.size(); i++) {
            if (active == null) {
                active = playerNames.get(i);
            }
            players.put(playerNames.get(i), i);
            frames[i] = new LinkedList<>();
            frames[i].add(new Frame());
        }
        return active;
    }

    public String recieveScore(Integer pins) {
        if (pins > 10 || pins < 0) {
            throw new IllegalArgumentException("Invalid number of pins");
        }
        Integer id = players.get(active);
        List<Frame> playerFrames = frames[id];
        final Frame last[] = new Frame[1];
        boolean end[] = new boolean[1];
        playerFrames.forEach(frame1 -> {
            end[0] = frame1.addScore(pins);
            last[0] = frame1;
        });
        if (last[0].advance() && playerFrames.size() < 10) {
            playerFrames.add(new Frame());
            id = id +1;
            if(id == playerNames.size()) id = 0;
            active = playerNames.get(id);
        } else if (playerFrames.size() == 10 && end[0]) {
            id = id +1;
            if(id == playerNames.size()) id = 0;
            active = playerNames.get(id);
        }

        return active;
    }

    public List<Frame> getTableForPlayer(String name) {
        Integer id = players.get(name);
        if (id == -1) {
            throw new IllegalArgumentException("Player '" + name + "' does not exist");
        }
        List<Frame> score = frames[id];
        return score;
    }

    public Map<String, Integer> getCurrentScores() {
        Map<String, Integer> result = new HashMap<>();
        for (Map.Entry entry : players.entrySet()) {
            String name = (String) entry.getKey();
            Integer id = (Integer) entry.getValue();
            List<Frame> scores = frames[id];
            Integer total = 0;
            for (Frame frame : scores) {
                total += frame.getFrameScore();
            }
            result.put(name, total);
        }
        return result;
    }
}
