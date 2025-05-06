package com.backend.backend_chess_brawl.dtos;


import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class GameDTO {

    private Long gameId;

    private Long player1Id;

    private Long player2Id;

    private Map<Long, List<Long>> selectedEvents;
}
