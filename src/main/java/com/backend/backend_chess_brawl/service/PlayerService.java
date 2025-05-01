package com.backend.backend_chess_brawl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend_chess_brawl.model.Player;
import com.backend.backend_chess_brawl.repository.PlayerRepository;

@Service
public class PlayerService implements IPlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Player savePlayer(final Player player) {
        return playerRepository.save(player);
    }

    @Override
    public void deletePlayer(final Long playerId) {
        playerRepository.deleteById(playerId);
    }

    @Override
    public Player findById(final Long playerId) {
        return playerRepository.findById(playerId).orElse(null);
    }

    @Override
    public Player findByNickname(String nickname) {
        return playerRepository.findByNickname(nickname).orElse(null);
    }

    @Override
    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }
}
