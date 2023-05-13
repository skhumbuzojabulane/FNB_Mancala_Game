package com.mancala.mancala.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class Move {
    @NotBlank
    private String gameId;

    @NotBlank
    @Pattern(regexp = "(?:player1|player2)", message = "must be player1 or player2")
    private String player;

    @Min(0)
    @Max(5)
    private int pitNumber;

    // Constructor
    public Move(String gameId, String player, int pitNumber) {
        this.gameId = gameId;
        this.player = player;
        this.pitNumber = pitNumber;
    }

    // Getters and setters
    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getPitNumber() {
        return pitNumber;
    }

    public void setPitNumber(int pitNumber) {
        this.pitNumber = pitNumber;
    }

    // Other methods
    // ...
}
