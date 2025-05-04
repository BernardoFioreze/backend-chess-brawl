package com.backend.backend_chess_brawl.dtos;

import lombok.Data;

@Data
public class GameDTO {

    private Long player1Id;

    private Long player2Id;

    private String winner;

    private Long roundId;
}
