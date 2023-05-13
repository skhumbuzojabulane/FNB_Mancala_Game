package com.mancala.mancala.model;

public class Game {
    private String gameId;
    private String currentPlayer;
    private boolean isWinnerExist;
    private String winner;
    private Player player1;
    private Player player2;
    
    public Game(String gameId, Player player1, Player player2) {
        this.gameId = gameId;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1.getName();
        this.isWinnerExist = false;
        this.winner = null;
    }
    
    public String getGameId() {
        return gameId;
    }
    
    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
    
    public String getCurrentPlayer() {
        return currentPlayer;
    }
    
    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
    public boolean isWinnerExist() {
        return isWinnerExist;
    }
    
    public void setWinnerExist(boolean winnerExist) {
        isWinnerExist = winnerExist;
    }
    
    public String getWinner() {
        return winner;
    }
    
    public void setWinner(String winner) {
        this.winner = winner;
    }
    
    public Player getPlayer1() {
        return player1;
    }
    
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }
    
    public Player getPlayer2() {
        return player2;
    }
    
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}
