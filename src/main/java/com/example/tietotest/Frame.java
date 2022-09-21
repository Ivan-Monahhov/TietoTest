package com.example.tietotest;

public class Frame {
    private Integer firstRoll;
    private Integer secondRoll;
    private Integer frameScore = 0;
    private Integer recieve =2;

    public boolean addScore(Integer pins) {
        if(recieve == 0) return true;
        frameScore += pins;
        recieve--;
        if (firstRoll == null) {
            firstRoll = pins;
            if(pins == 10){
                secondRoll = 0;
                recieve++;
            }
        } else if (secondRoll == null) {
            secondRoll = pins;
            if(firstRoll + secondRoll == 10){
                recieve++;
            }
        }
        return recieve == 0;
    }

    public boolean advance() {
        return firstRoll != null && secondRoll != null;
    }

    public int getFirstRoll() {
        return firstRoll;
    }

    public void setFirstRoll(int firstRoll) {
        this.firstRoll = firstRoll;
    }

    public int getSecondRoll() {
        return secondRoll;
    }

    public void setSecondRoll(int secondRoll) {
        this.secondRoll = secondRoll;
    }

    public int getFrameScore() {
        return frameScore;
    }

    public void setFrameScore(int frameScore) {
        this.frameScore = frameScore;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "firstRoll=" + firstRoll +
                ", secondRoll=" + secondRoll +
                ", frameScore=" + frameScore +
                ", recieve=" + recieve +
                '}';
    }

}
