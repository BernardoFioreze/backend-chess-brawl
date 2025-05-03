package com.backend.backend_chess_brawl.dtos;

import lombok.Data;

@Data
public class PlayerDTO {

    public PlayerDTO(String name, String nickname, String ranking, Integer score, String status) {
        this.name = name;
        this.nickname = nickname;
        this.ranking = ranking;
        this.score = score;
        this.status = status;
    }

    public PlayerDTO() {

    }

    private String name;

    private String nickname;

    private String ranking;
    
    private Integer score;
    
    private String status;
}
