package com.mancala.mancala.service.serviceImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mancala.mancala.model.Game;
import com.mancala.mancala.model.Move;
import com.mancala.mancala.model.Player;
import com.mancala.mancala.service.MancalaGameService;
import com.mancala.mancala.util.GameConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MancalaGameServiceImp implements MancalaGameService {
 
    private HashMap<String, Game> games = new HashMap<>();

    @Override
    public Game getGame(String gameId) {
        if (games.containsKey(gameId)) {
            return games.get(gameId);
        }
        return games.get(createNewGame());
    }

    @Override
    public Game getMove(Move movement) {
        Game game = this.getGame(movement.getGameId());
        if (!game.getCurrentPlayer().equals(movement.getPlayer())) return game;

        Game tempGame = game;
        int pitNumber = movement.getPitNumber();

        boolean isCurrentsTurn = false;
        switch (movement.getPlayer()) {
            case GameConstants.PLAYER1_KEY:

                isCurrentsTurn = calculateMovements(tempGame, pitNumber);
                if (!isCurrentsTurn) {
                    tempGame.setCurrentPlayer(GameConstants.PLAYER2_KEY);
                }
                log.info("Movement of " + game.getGameId() + " Turn: "+ GameConstants.PLAYER1_KEY);
                break;
            case GameConstants.PLAYER2_KEY:
                Player player1 = game.getPlayer1();
                Player player2 = game.getPlayer2();
                tempGame.setPlayer1(player2);
                tempGame.setPlayer2(player1);
                isCurrentsTurn = calculateMovements(tempGame, pitNumber);
                if (!isCurrentsTurn) {
                    tempGame.setCurrentPlayer(GameConstants.PLAYER1_KEY);
                }
                player1 = tempGame.getPlayer2();
                player2 = tempGame.getPlayer1();
                tempGame.setPlayer1(player1);
                tempGame.setPlayer2(player2);
                log.info("Movement of " + game.getGameId() + " Turn: "+ GameConstants.PLAYER2_KEY);
                break;
            default:
                return game;
        }
        boolean isWinnerExist = checkWinner(tempGame);
        tempGame.setWinnerExist(isWinnerExist);
        game = tempGame;
        return game;
    }

    private String createNewGame() {
        UUID uuid = UUID.randomUUID();
        String gameId = uuid.toString();
        games.put(gameId, this.initiateResultSet(gameId));
        log.info("New Game is created. Game ID:" + gameId);
        return gameId;
    }


    private boolean calculateMovements(Game game, int pitNumber) {
        Player currentPlayer = game.getPlayer1();
        Player counterPlayer = game.getPlayer2();

        int[] ownPits = currentPlayer.getPits();
        int stones = ownPits[pitNumber];
        boolean isMyTurn = false;


        ownPits[pitNumber] = GameConstants.BLANK;
        if (stones == GameConstants.BLANK) return true;
        while (stones != GameConstants.BLANK) {
            for (int i = pitNumber + 1; i < GameConstants.PIT; i++) {
                if (stones == GameConstants.BLANK) break;
                ownPits[i]++;
                stones--;
            }

            if (stones != GameConstants.BLANK) {
                isMyTurn = true;
                currentPlayer.mancala++;
                stones--;
            }

            if (stones != GameConstants.BLANK) {
                isMyTurn = false;
                int[] counterPits = counterPlayer.getPits();
                int i = 0;
                int targetIndex = (stones >= GameConstants.PIT) ? GameConstants.PIT : stones;

                for (i = 0; i < targetIndex; i++) {
                    if (stones == GameConstants.BLANK) break;
                    counterPits[i]++;
                    stones--;
                }
                if (counterPits[i - 1] % 2 == 0 && counterPits[i - 1] > 0) {
                    currentPlayer.mancala += counterPits[i];
                    currentPlayer.mancala += ownPits[GameConstants.PIT - i];
                    ownPits[GameConstants.PIT - i] = 0;
                    counterPits[i - 1] = 0;

                }
                counterPlayer.setPits(counterPits);
            }
            currentPlayer.setPits(ownPits);
        }

        game.setPlayer1(currentPlayer);
        game.setPlayer2(counterPlayer);
        return isMyTurn;
    }

    private Game initiateResultSet(String gameId) {
        int[] pits1 = new int[GameConstants.PIT];
        int[] pits2 = new int[GameConstants.PIT];
    
        Arrays.fill(pits1, GameConstants.STONE);
        Arrays.fill(pits2, GameConstants.STONE);
    
        Player player1 = new Player(GameConstants.PLAYER1_KEY, 0);
        Player player2 = new Player(GameConstants.PLAYER2_KEY, 0);
    
        return new Game(gameId, player1, player2);
    }
    
    

    private boolean checkWinner(Game game) {

        boolean isWinnerExist = isAllStonesSpent(game.getPlayer1().getPits())
                || isAllStonesSpent(game.getPlayer2().getPits());

        if (isWinnerExist) {
            decideWinner(game);
        }
        return isWinnerExist;
    }

    private void decideWinner(Game game) {
        int player1Treasury = game.getPlayer1().mancala +
                Arrays.stream(game.getPlayer1().getPits()).sum();
        int player2Treasury = game.getPlayer2().mancala +
                Arrays.stream(game.getPlayer2().getPits()).sum();
        if (player1Treasury > player2Treasury) {
            log.info("Winner of " + game.getGameId() + " is " + GameConstants.PLAYER1_KEY);
            game.setWinner(GameConstants.PLAYER1_KEY);
        } else {
            log.info("Winner of " + game.getGameId() + " is " + GameConstants.PLAYER2_KEY);
            game.setWinner(GameConstants.PLAYER2_KEY);
        }
    }

    private static boolean isAllStonesSpent(int[] pits) {
        for (int pit : pits) if (pit != 0) return false;
        return true;
    }
}
