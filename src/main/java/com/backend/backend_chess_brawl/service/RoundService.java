package com.backend.backend_chess_brawl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend_chess_brawl.model.Round;
import com.backend.backend_chess_brawl.model.Tournament;
import com.backend.backend_chess_brawl.repository.RoundRepository;
import com.backend.backend_chess_brawl.repository.TournamentRepository;

@Service
public class RoundService implements IRoundService {

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private TournamentRepository tournamentRepository;
    
    @Override
    public Round saveRound(Round round, Long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> new RuntimeException("Tournament não encontrado"));
    
        round.setTournament(tournament);
        return roundRepository.save(round);
    }    

    @Override
    public Round findById(Long roundId) {
        return roundRepository.findById(roundId).orElse(null);
    }

    @Override
    public List<Round> findRoundByTournamentId(Long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> new RuntimeException("Tournament não encontrado"));

        return roundRepository.findRoundByTournament(tournament);

    }

}
