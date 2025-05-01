package com.backend.backend_chess_brawl.service;

import com.backend.backend_chess_brawl.model.Tournament;
import com.backend.backend_chess_brawl.repository.TournamentRepository;

public class TournamentService implements ITournamentService {

    private TournamentRepository tournamentRepository;

    @Override
    public Tournament saveTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    @Override
    public Tournament findById(Long tournamentId) {
        return tournamentRepository.findById(tournamentId).orElse(null);
    }

}
