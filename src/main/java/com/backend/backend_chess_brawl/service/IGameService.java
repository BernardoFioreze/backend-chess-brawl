package com.backend.backend_chess_brawl.service;

import com.backend.backend_chess_brawl.dtos.GameDTO;
import com.backend.backend_chess_brawl.model.Game;

public interface IGameService {

    Game calculateScore(GameDTO gameDTO);
    
    Game saveGame(Game game, Long roundId);

    Game findById(Long gameId);

    Game calculateRandScore(GameDTO gameDTO);

}
