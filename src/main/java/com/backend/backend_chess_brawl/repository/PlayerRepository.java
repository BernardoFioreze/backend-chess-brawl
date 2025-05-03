package com.backend.backend_chess_brawl.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.backend_chess_brawl.model.Player;
import com.backend.backend_chess_brawl.model.Tournament;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    
    Optional<Player> findByNickname(String nickname);

    List<Player> findByTournament(Tournament tournament);
}
