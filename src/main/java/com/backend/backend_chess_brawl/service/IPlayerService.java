package com.backend.backend_chess_brawl.service;

import java.util.List;

import com.backend.backend_chess_brawl.model.Event;
import com.backend.backend_chess_brawl.model.Player;

public interface IPlayerService {

    Player addPlayerToTurnament(Long tournamentId, String name, String nickname, String ranking);

    Player addEvent(List<Event> events, Long playerId);

    void deletePlayer(Long playerId);

    Player findById(Long palyerId);

    Player findByNickname(String nickname);

    List<Player> findAllPlayers();

}
