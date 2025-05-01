package com.backend.backend_chess_brawl.service;

import com.backend.backend_chess_brawl.model.Round;
import com.backend.backend_chess_brawl.repository.RoundRepository;

public class RoundService implements IRoundService {

    private RoundRepository roundRepository;
    
    @Override
    public Round saveRound(Round round) {
        return roundRepository.save(round);
    }

    @Override
    public Round findById(Long roundId) {
        return roundRepository.findById(roundId).orElse(null);
    }

}
