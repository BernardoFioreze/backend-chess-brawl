package com.backend.backend_chess_brawl.service;

import com.backend.backend_chess_brawl.model.Match;
import com.backend.backend_chess_brawl.repository.MatchRepository;

public class MatchService implements IMatchService {

     private MatchRepository matchRepository;

    @Override
    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    @Override
    public Match finById(Long matchId) {
        return matchRepository.findById(matchId).orElse(null);
    }

}
