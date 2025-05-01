package com.backend.backend_chess_brawl.service;

import com.backend.backend_chess_brawl.model.Match;

public interface IMatchService {

    Match saveMatch(Match match);

    Match finById(Long matchId);

}
