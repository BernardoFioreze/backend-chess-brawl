package com.backend.backend_chess_brawl.service;

import com.backend.backend_chess_brawl.model.Game;

public interface IGameService {

    Game saveGame(Game game);

    Game findById(Long gameId);

}
