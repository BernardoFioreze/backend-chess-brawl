package com.backend.backend_chess_brawl.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend_chess_brawl.model.Player;
import com.backend.backend_chess_brawl.service.EventService;
import com.backend.backend_chess_brawl.service.GameService;
import com.backend.backend_chess_brawl.service.PlayerService;
import com.backend.backend_chess_brawl.service.RoundService;
import com.backend.backend_chess_brawl.service.TournamentService;

@RestController
public class JudgeUserController {

    private PlayerService playerService;
    
    private EventService eventService;
    
    private GameService gameService;

    private RoundService roundService;

    private TournamentService tournamentService;

    @PostMapping("/api/registration")
    public ResponseEntity<?> registerPlayer(@RequestBody Player player){
        if(playerService.findByNickname(player.getNickname()) != null) {
            return new ResponseEntity<>("Usuario já existente", HttpStatus.CONFLICT);
        }

        Player savedPlayer = playerService.savePlayer(player);
        return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
    }

    @PostMapping("api/tournament")
    public ResponseEntity<?> startTournament(@RequestBody List<Player> players) {
        if(players.size() < 3 || players.size() % 2 != 0) {
            return new ResponseEntity<>("Numero de participantes incorreto", HttpStatus.CONFLICT);
        }

        List<Player> validList = playerService.findAllPlayers();
        return new ResponseEntity<>(validList, HttpStatus.CREATED);
    }

}
