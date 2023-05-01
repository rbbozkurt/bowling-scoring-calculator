package com.rbbozkurt.bowlingscoringcalculator.customs;

import java.util.ArrayList;

/**
 * The Key class representing a key for the pins being rolled.
 */
public class Key {
    /**
     * sign of the key to be shown in board
     */
    private String sign;
    /**
     * value hold by the key
     */
    private int value;

    public Key(String sign, int value) {
        this.sign = sign;
        this.value = value;
    }

    public String getSign() {
        return sign;
    }

    public int getValue() {
        return value;
    }

    /**
     * creates a array of key objects
     * @param keyNumber size of the array
     * @return array of key objects
     */

    public static Key[] createKeyList(int keyNumber){
        Key[] keys = new Key[keyNumber+1];

        for (int i = 0; i <= keyNumber; i++) {
            keys[i] = new Key( String.valueOf(i),i );
        }

        return keys;

    }
}
