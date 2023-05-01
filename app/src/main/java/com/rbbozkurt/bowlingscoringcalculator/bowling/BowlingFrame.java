package com.rbbozkurt.bowlingscoringcalculator.bowling;


import com.rbbozkurt.bowlingscoringcalculator.BowlingUtils;
/**
 * @author resitberkaybozkurt
 */

/**
 * The BowlingFrame representing a from of bowling game.
 */
public class BowlingFrame {
    /**
     * number of pins rolled within first roll
     */
    private int firstRoll;

    /**
     * number of pins rolled within second roll
     */
    private int secondRoll;
    /**
     * number of bonuses that frame has
     */
    private int bonusRollCounter ;
    /**
     * total bonus score of frame
     */
    private int bonusFrameScore;
    /**
     * final score the frame
     */
    private int endTotalFrameScore;

    private boolean isSpare;
    private boolean isStrike;
    /**
     * number of pins in frame
     */
    private int initPinNumber;

    public BowlingFrame(int initPinNumber) {
        this.firstRoll = BowlingUtils.UNROLLED_SCORE;
        this.secondRoll = BowlingUtils.UNROLLED_SCORE;
        this.endTotalFrameScore = BowlingUtils.UNCALCULATED_FRAME_SCORE;
        this.bonusFrameScore = 0;
        this.bonusRollCounter = 0;
        this.isSpare = false;
        this.isStrike = false;
        this.initPinNumber = initPinNumber;
    }

    /**
     * substracts the pins rolled withing two rolls from the total number of pins in frame
     * @return remaining pins after rolls
     */
    public int remainingPins()
    {
        if(firstRoll == BowlingUtils.UNROLLED_SCORE){
            return this.initPinNumber;
        }else if(secondRoll == BowlingUtils.UNROLLED_SCORE){
            return this.initPinNumber - firstRoll;
        }else{
            return this.initPinNumber - firstRoll - secondRoll;
        }
    }

    /**
     * adds pins to frame
     * @param pinNumber number of pins to added to frame
     */
    public void addPin(int pinNumber){
        this.initPinNumber+=pinNumber;
    }

    /**
     * add bonus points to frame and decreases number of bonuses
     * @param bonusPin bonus points
     */
    public void addBonus(int bonusPin){
        this.bonusFrameScore += bonusPin;
        this.bonusRollCounter--;
    }

    /**
     * calculates the sub total score of the frame excluding the score of the previous frame
     * @return sum of the number of pins rolled and bonus points
     */
    public int getSubTotalFrameScore() {
        return bonusFrameScore + firstRoll + secondRoll;
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

    public int getBonusRollCounter() {
        return bonusRollCounter;
    }

    public void setBonusRollCounter(int bonusRollCounter) {
        this.bonusRollCounter = bonusRollCounter;
    }




    public int getEndTotalFrameScore() {
        return endTotalFrameScore;
    }

    public void setEndTotalFrameScore(int endTotalFrameScore) {
        this.endTotalFrameScore = endTotalFrameScore;
    }

    public boolean isSpare() {
        return isSpare;
    }

    public void setSpare(boolean spare) {
        isSpare = spare;
    }

    public boolean isStrike() {
        return isStrike;
    }

    public void setStrike(boolean strike) {
        isStrike = strike;
    }


}
