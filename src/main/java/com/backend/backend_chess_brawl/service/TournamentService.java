package com.backend.backend_chess_brawl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend_chess_brawl.model.Tournament;
import com.backend.backend_chess_brawl.repository.TournamentRepository;

@Service
public class TournamentService implements ITournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Override
    public Tournament startTournament() {
        Tournament tournament = new Tournament();

        return tournamentRepository.save(tournament);
    }

    @Override
    public Tournament findById(Long tournamentId) {
        return tournamentRepository.findById(tournamentId).orElse(null);
    }

}
