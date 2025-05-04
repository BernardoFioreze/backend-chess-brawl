package com.backend.backend_chess_brawl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.backend_chess_brawl.model.Round;
import com.backend.backend_chess_brawl.model.Tournament;

public interface RoundRepository extends JpaRepository<Round, Long> {
    
    List<Round> findRoundByTournament(Tournament tournament);
}
