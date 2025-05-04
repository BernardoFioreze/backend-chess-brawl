package com.backend.backend_chess_brawl.service;

import com.backend.backend_chess_brawl.model.Tournament;

public interface ITournamentService {
 
    Tournament createTournament();

    Tournament createRound(Long tournamentId);

    Tournament startTournament(Long tournamentId);

    Tournament findById(Long tournamentId);

}
