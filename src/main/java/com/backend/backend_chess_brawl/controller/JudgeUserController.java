package com.backend.backend_chess_brawl.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend_chess_brawl.dtos.EventDTO;
import com.backend.backend_chess_brawl.dtos.PlayerDTO;
import com.backend.backend_chess_brawl.model.Event;
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

    public JudgeUserController(
        PlayerService playerService,
        EventService eventService,
        GameService gameService,
        RoundService roundService,
        TournamentService tournamentService
    ) {
        this.playerService = playerService;
        this.eventService = eventService;
        this.gameService = gameService;
        this.roundService = roundService;
        this.tournamentService = tournamentService;
    }

    @PostMapping("/api/chessBrawl")
    public ResponseEntity<Tournament> createTournament() {
        Tournament tournament = tournamentService.createTournament();
        return ResponseEntity.ok(tournament);
    }

    @PostMapping("/{tournamentId}/player")
    public ResponseEntity<Player> registerPlayerInTournament(@PathVariable Long tournamentId, @RequestBody PlayerDTO playerDTO) {    
        Player player = playerService.addPlayerToTurnament(
            tournamentId,
            playerDTO.getName(),
            playerDTO.getNickname(),
            playerDTO.getRanking()
            );
            return ResponseEntity.ok(player);
    }

    @GetMapping("/{tournamentId}/players")
    public ResponseEntity<List<PlayerDTO>> getPlayersByTournament(@PathVariable Long tournamentId) {
        List<Player> players = playerService.getPlayersByTournamentId(tournamentId);
        
        List<PlayerDTO> playerDTOs = players.stream().map(player -> new PlayerDTO(
            player.getName(),
            player.getNickname(),
            player.getRanking(),
            player.getScore(),
            player.getStatus().toString()
        )).toList();

    return ResponseEntity.ok(playerDTOs);
}
    
    @PostMapping("/{tournamentId}/startTournament")
    public ResponseEntity<Tournament> startTournament(@PathVariable Long tournamentId) {
        Tournament tournament = tournamentService.startTournament(tournamentId);

        return ResponseEntity.ok(tournament);
    }

    @PostMapping("/{playerId}/events")
    public ResponseEntity<Player> addEventesToPlayer(@PathVariable Long playerId, @RequestBody List<EventDTO> eventDTOs) {
        List<Event> events = eventDTOs.stream().map(dto -> {
            Event e = new Event();
            e.setName(dto.getName());
            e.setWeight(dto.getWeight());
            return e;
        }).toList();       

        Player player = playerService.addEvent(events, playerId);

        return ResponseEntity.ok(player); 

    }



}
