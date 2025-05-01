package com.backend.backend_chess_brawl.service;

import java.util.List;

import com.backend.backend_chess_brawl.model.Player;

public interface IPlayerService {

    Player savePlayer(Player player);

    void deletePlayer(Long playerId);

    Player findById(Long palyerId);

    Player findByNickname(String nickname);

    List<Player> findAllPlayers();

}
