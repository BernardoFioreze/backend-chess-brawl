package com.backend.backend_chess_brawl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.backend_chess_brawl.model.Round;

public interface RoundRepository extends JpaRepository<Round, Long> {

}
