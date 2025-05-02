package com.backend.backend_chess_brawl.dtos;

import com.backend.backend_chess_brawl.model.Player;

import lombok.Data;

@Data
public class GameDTO {

    private Player player1;

    private Player player2;
}
