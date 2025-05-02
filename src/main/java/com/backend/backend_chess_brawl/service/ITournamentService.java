package com.backend.backend_chess_brawl.service;

import com.backend.backend_chess_brawl.model.Tournament;

public interface ITournamentService {
 
    Tournament startTournament();

    Tournament findById(Long tournamentId);

}
