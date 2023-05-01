package com.rbbozkurt.bowlingscoringcalculator.bowling;

import com.rbbozkurt.bowlingscoringcalculator.BowlingUtils;

/**
 * @author resitberkaybozkurt
 */

/**
 * The BowlingCalculator representing a single game score calculations.
 */
public class BowlingCalculator {

    /**
     * frames representing scores and pins
     */
    public BowlingFrame[] frames;
    /**
     * id of current frame in which the player is rolling
     */
    private int currentFrameInd;
    /**
     * determines whether a user has an extra roll in the last frame
     */
    private boolean hasExtraRoll;

    public BowlingCalculator(BowlingFrame[] frames) {
        this.currentFrameInd = 0;
        this.frames = frames;
        this.hasExtraRoll = false;

    }

    /**
     * updates the frames and the game score
     * @param pinNumber number of pins that were rolled after a roll
     */
    public void roll(int pinNumber) {
        //is game still on
        if (isOver()) {
            return;
        }
        //pin number is within the range allowed range
        if (pinNumber > BowlingUtils.PIN_FOR_EACH_FRAME) {
            return;
        }
        //is there enough pin to roll
        if (pinNumber > this.frames[currentFrameInd].remainingPins()) {
            return;
        }
        //check two frames before the current frame, if they have any bonus from this roll
        for (int backCounter = 2; backCounter > 0; backCounter--) {
            int frameInd = this.currentFrameInd - backCounter;
            //check if the frames exist and it has a bonus
            if (frameInd >= 0 && this.frames[frameInd].getBonusRollCounter() > 0) {
                //add frame's bonus
                this.frames[frameInd].addBonus(pinNumber);
                //frame does not have anymore bonuses
                if (this.frames[frameInd].getBonusRollCounter() <= 0) {
                    //calculate the accumulativ score of the frame
                    int preFrameInd = frameInd - 1;
                    //frame has a previous, so add scores
                    if (preFrameInd >= 0) {
                        this.frames[frameInd].setEndTotalFrameScore(this.frames[preFrameInd].getEndTotalFrameScore() +
                                this.frames[frameInd].getSubTotalFrameScore());
                    } else {
                        //frame is the first frame of the game
                        this.frames[frameInd].setEndTotalFrameScore(this.frames[frameInd].getSubTotalFrameScore());
                    }
                }
            }
        }
        //get the current frame
        BowlingFrame currentFrame = this.frames[currentFrameInd];
        //checks if the last frame of the game
        if(currentFrameInd == this.frames.length - 1){
            //first roll of the frame
            if(currentFrame.getFirstRoll() == BowlingUtils.UNROLLED_SCORE){
                currentFrame.setFirstRoll(pinNumber);
                //if all pins down than mark as strike and give one extra shot
                if(currentFrame.remainingPins() <= 0){
                    currentFrame.setStrike(true);
                    currentFrame.addPin(BowlingUtils.PIN_FOR_EACH_FRAME);
                    this.hasExtraRoll = true;
                }
            }
            //second roll of the game
            else if(currentFrame.getSecondRoll() == BowlingUtils.UNROLLED_SCORE){
                currentFrame.setSecondRoll(pinNumber);
                //if all pins down than mark as spare and give one extra shot
                if(currentFrame.remainingPins() <= 0){
                    currentFrame.setSpare(true);
                    currentFrame.addPin(BowlingUtils.PIN_FOR_EACH_FRAME);
                    this.hasExtraRoll = true;
                }
                //calculate the score of the frame without extra roll
                int preFrameInd = currentFrameInd - 1;
                currentFrame.setEndTotalFrameScore(this.frames[preFrameInd].getEndTotalFrameScore() +
                        currentFrame.getSubTotalFrameScore());

            }else{
                //current roll is extra roll a

                //add extra bonus to frame
                currentFrame.addBonus(pinNumber);
                int preFrameInd = currentFrameInd - 1;
                //calculate the final score of the frame and mark extra roll
                currentFrame.setEndTotalFrameScore(this.frames[preFrameInd].getEndTotalFrameScore() +
                        currentFrame.getSubTotalFrameScore());
                this.hasExtraRoll = false;
            }
            return;
        }
        //not the last frame of game

        //if first roll
        if(currentFrame.getFirstRoll() == BowlingUtils.UNROLLED_SCORE){
            currentFrame.setFirstRoll(pinNumber);
            //if all pins down
            if(currentFrame.remainingPins() <= 0){
                //add bonus rolls to frame
                currentFrame.setBonusRollCounter(BowlingUtils.BONUS_ROLL_STRIKE_FRAME);
                //mark as a strike
                currentFrame.setStrike(true);
                //set second roll as zero, because pins are down for the frame
                currentFrame.setSecondRoll(0);
                //switch to next frame
                currentFrameInd++;
                return;
            }
        }else{
            //if second roll
            currentFrame.setSecondRoll(pinNumber);

            //mark as a spare and add bonus rolls
            if(currentFrame.remainingPins() <= 0){
                currentFrame.setBonusRollCounter(BowlingUtils.BONUS_ROLL_SPARE_FRAME);
                currentFrame.setSpare(true);

            }else{
                //if not spare

                //if has a previous frame
                if(this.currentFrameInd - 1 >= 0){
                    //sum up the score of the current frame with the previous frame's score
                    currentFrame.setEndTotalFrameScore(this.frames[currentFrameInd - 1].getEndTotalFrameScore() +
                                                        currentFrame.getSubTotalFrameScore());
                }else{
                    //if first frame

                    //calculate the score for the frame
                    currentFrame.setEndTotalFrameScore(currentFrame.getSubTotalFrameScore());
                }

            }
            //switch to next frame
            currentFrameInd++;
            return;
        }


    }

    /**
     * checks if the game is on or finished
     * @return if all rolls are made and there no extra rolls, then true
     *          else false
     */
    private boolean isOver() {
        //current frame is not the last frame
        if (currentFrameInd < this.frames.length - 1) {
            return false;
        } else {
            //two rolls of the last frame is done and there are not extra rolls
            return !hasExtraRoll &&
                    this.frames[currentFrameInd].getFirstRoll() != BowlingUtils.UNROLLED_SCORE &&
                    this.frames[currentFrameInd].getSecondRoll() != BowlingUtils.UNROLLED_SCORE;
        }
    }
}
