package com.backend.backend_chess_brawl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.backend_chess_brawl.model.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

}
