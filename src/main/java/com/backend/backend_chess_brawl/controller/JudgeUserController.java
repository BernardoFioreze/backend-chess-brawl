package com.backend.backend_chess_brawl.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend_chess_brawl.model.Player;
import com.backend.backend_chess_brawl.service.EventService;
import com.backend.backend_chess_brawl.service.MatchService;
import com.backend.backend_chess_brawl.service.PlayerService;
import com.backend.backend_chess_brawl.service.RoundService;
import com.backend.backend_chess_brawl.service.TournamentService;

@RestController
public class JudgeUserController {

    private PlayerService playerService;
    
    private EventService eventService;
    
    private MatchService matchService;

    private RoundService roundService;

    private TournamentService tournamentService;

    @PostMapping("/api/registration")
    public ResponseEntity<?> register(@RequestBody Player player){
        if(playerService.findByNickname(player.getNickname()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Player savedPlayer = playerService.savePlayer(player);
        return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
    }

}
