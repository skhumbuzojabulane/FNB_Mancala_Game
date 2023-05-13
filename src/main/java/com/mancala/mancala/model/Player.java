package com.mancala.mancala.model;

import java.util.Arrays;
import com.mancala.mancala.util.GameConstants;

public class Player {
    public String name;
    public int[] pits;
    public int mancala;

    // Constructor
    public Player(String name, int stoneCount) {
        this.name = name;
        this.pits = new int[GameConstants.PIT];
        Arrays.fill(this.pits, stoneCount);
        this.mancala = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getPits() {
        return pits;
    }

    public void setPits(int[] pits) {
        this.pits = pits;
    }

    public int getMancala() {
        return mancala;
    }

    public void setMancala(int mancala) {
        this.mancala = mancala;
    }
}
