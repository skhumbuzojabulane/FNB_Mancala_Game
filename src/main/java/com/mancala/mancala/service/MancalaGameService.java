package com.mancala.mancala.service;

import com.mancala.mancala.model.Game;
import com.mancala.mancala.model.Move;

public interface MancalaGameService {
    Game getGame(String gameId);
    Game getMove(Move movement);
}
