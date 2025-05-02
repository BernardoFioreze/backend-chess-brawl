package com.backend.backend_chess_brawl.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.backend.backend_chess_brawl.model.Game;
import com.backend.backend_chess_brawl.repository.GameRepository;

public class GameService implements IGameService {

    @Autowired
    private GameRepository gameRepository;

    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game findById(Long gameId) {
        return gameRepository.findById(gameId).orElse(null);
    }

}
