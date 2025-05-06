package com.backend.backend_chess_brawl.service;

import java.util.List;

import com.backend.backend_chess_brawl.model.Round;

public interface IRoundService {

    Round saveRound(Round round, Long tournamentId);

    List<Round> findRoundByTournamentId(Long tournamentId);

    Round findLastRoundByTournamentId(Long tournamentId);

    Round findById(Long roundId);

}
