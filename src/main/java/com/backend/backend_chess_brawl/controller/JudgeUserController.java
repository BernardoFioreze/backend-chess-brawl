package com.backend.backend_chess_brawl.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend_chess_brawl.dtos.EventDTO;
import com.backend.backend_chess_brawl.dtos.GameDTO;
import com.backend.backend_chess_brawl.dtos.PlayerDTO;
import com.backend.backend_chess_brawl.model.Event;
import com.backend.backend_chess_brawl.model.Game;
import com.backend.backend_chess_brawl.model.Player;
import com.backend.backend_chess_brawl.model.Round;
import com.backend.backend_chess_brawl.model.Tournament;
import com.backend.backend_chess_brawl.service.EventService;
import com.backend.backend_chess_brawl.service.GameService;
import com.backend.backend_chess_brawl.service.PlayerService;
import com.backend.backend_chess_brawl.service.RoundService;
import com.backend.backend_chess_brawl.service.TournamentService;

@CrossOrigin(origins = "*")
@RestController
public class JudgeUserController {

    private final PlayerService playerService;
    
    private final EventService eventService;
    
    private final GameService gameService;

    private final RoundService roundService;

    private final TournamentService tournamentService;

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

    // Primeiro acesso a api
    @PostMapping("/api/chessBrawl")
    public ResponseEntity<Tournament> createTournament() {
        Tournament tournament = tournamentService.createTournament();
        return ResponseEntity.ok(tournament);
    }

    // Criação de Usuarios
    @PostMapping("/{tournamentId}/player")
    public ResponseEntity<?> registerPlayerInTournament(@PathVariable Long tournamentId, @RequestBody PlayerDTO playerDTO) {
        try{    
        Player player = playerService.addPlayerToTurnament(
            tournamentId,
            playerDTO.getName(),
            playerDTO.getNickname(),
            playerDTO.getRanking()
            );
            return ResponseEntity.ok(player);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Consulta aos usuarios de um torneio
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

    // Inicialização de um torneio
    @PostMapping("/{tournamentId}/startTournament")
    public ResponseEntity<Tournament> startTournament(@PathVariable Long tournamentId) {
        Tournament tournament = tournamentService.startTournament(tournamentId);

        return ResponseEntity.ok(tournament);
    }

    // Adiciona eventos em um player
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
    
    // Calcula o vencedor de cada game
    @PostMapping("/game/winner")
    public ResponseEntity<Game> gameCalScore(@RequestBody GameDTO gameDTO) {
        Game game = gameService.calculateScore(gameDTO);

        return ResponseEntity.ok(game);
    }

    // Cria uma nova roda em um torneio
    @PostMapping("/NewRound/{tournamentId}")
    public ResponseEntity<Tournament> createRound(@PathVariable Long tournamentId) {
        Tournament tournament = tournamentService.createRound(tournamentId);

        return ResponseEntity.ok(tournament);
    }

    // Puxa os rounds dentro de um torneio
    @GetMapping("/getRounds/{tournamentId}")
    public ResponseEntity<List<Round>> getTournamentRoundList(@PathVariable Long tournamentId) {
        List<Round> rounds = roundService.findRoundByTournamentId(tournamentId);

        return ResponseEntity.ok(rounds);
    }

    // Puxa o ultimo round de um torneio
    @GetMapping("getLastRound/{tournamentId}")
    public ResponseEntity<Round> getlastTournamentRound(@PathVariable Long tournamentId) {
        Round round = roundService.findLastRoundByTournamentId(tournamentId);

        return ResponseEntity.ok(round);
    }

    // Puxa os eventos que foram previamente settados
    @GetMapping("/getEvents")
    public ResponseEntity<List<Event>> getEvents() {
        List<Event> events = eventService.findAllEvents();
        return ResponseEntity.ok(events);
    }
}
