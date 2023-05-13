package com.mancala.mancala.controller;

import com.mancala.mancala.service.MancalaGameService;
import com.mancala.mancala.model.Move;
import com.mancala.mancala.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class GameController {

    @Autowired
    private MancalaGameService mancalaGameService;

    @GetMapping("/start")
    @ResponseBody
    public Game getStart(@RequestParam String gameId) {
        return mancalaGameService.getGame(gameId);
    }

    @PostMapping("/move")
    public Game getMovement(@Valid @RequestBody Move move) {
        return mancalaGameService.getMove(move);
    }

}
