package com.backend.backend_chess_brawl.service;

import com.backend.backend_chess_brawl.model.Round;

public interface IRoundService {

    Round saveRound(Round round);

    Round findById(Long roundId);

}
