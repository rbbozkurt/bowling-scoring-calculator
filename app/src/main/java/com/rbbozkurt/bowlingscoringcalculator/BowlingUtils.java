package com.rbbozkurt.bowlingscoringcalculator;

/**
 * @author resitberkaybozkurt
 */

/**
 * The BowlingUtils representing the constant values for bowling game
 */
public class BowlingUtils {
    /**
     * number of frames each game has
     */
    public final static int FRAME_FOR_EACH_GAME = 10;
    /**
     * default number of pins each frame has
     */
    public final static int PIN_FOR_EACH_FRAME = 10;
    /**
     * score of a roll that is not done yet
     */
    public final static int UNROLLED_SCORE = -1;

    /**
     * default value for a frame score
     */
    public final static int UNCALCULATED_FRAME_SCORE = -1;
    /**
     * number of bonuses frame has after a spare
     */
    public final static int BONUS_ROLL_SPARE_FRAME = 1;
    /**
     * number of bonuses frame has after a strike
     */
    public final static  int BONUS_ROLL_STRIKE_FRAME = 2;
}
