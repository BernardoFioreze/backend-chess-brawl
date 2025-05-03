package com.backend.backend_chess_brawl.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend_chess_brawl.dtos.PlayerDTO;
import com.backend.backend_chess_brawl.model.Player;
import com.backend.backend_chess_brawl.model.Tournament;
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

    @PostMapping
    public ResponseEntity<Tournament> createTournament() {
        Tournament tournament = tournamentService.createTournament();
        return ResponseEntity.ok(tournament);
    }

    @PostMapping("/{id}/player")
    public ResponseEntity<Player> registerPlayerInTournament(@PathVariable Long tournamentId, @RequestBody PlayerDTO playerDTO){
        Player player = playerService.addPlayerToTurnament(
            tournamentId,
            playerDTO.getName(),
            playerDTO.getNickname(),
            playerDTO.getRanking()
            );
            return ResponseEntity.ok(player);
    }
    
    @PostMapping("/{tournamentId}/startTournament")
    public ResponseEntity<?> startTournament(@PathVariable Long tournamentId) {
        Tournament tournament = tournamentService.startTournament(tournamentId);

        return ResponseEntity.ok(tournament);
    }



}
