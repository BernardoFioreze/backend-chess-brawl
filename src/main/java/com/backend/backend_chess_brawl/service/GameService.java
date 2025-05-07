package com.backend.backend_chess_brawl.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend_chess_brawl.dtos.GameDTO;
import com.backend.backend_chess_brawl.model.Event;
import com.backend.backend_chess_brawl.model.Game;
import com.backend.backend_chess_brawl.model.Player;
import com.backend.backend_chess_brawl.model.Round;
import com.backend.backend_chess_brawl.model.Status;
import com.backend.backend_chess_brawl.repository.EventRepository;
import com.backend.backend_chess_brawl.repository.GameRepository;
import com.backend.backend_chess_brawl.repository.PlayerRepository;
import com.backend.backend_chess_brawl.repository.RoundRepository;

@Service
public class GameService implements IGameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Game calculateScore(GameDTO gameDTO) {
        Game game = gameRepository.findById(gameDTO.getGameId())
        .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));

        Player player1 = playerRepository.findById(gameDTO.getPlayer1Id())
            .orElseThrow(() -> new RuntimeException("Jogador 1 não encontrado"));

        Player player2 = playerRepository.findById(gameDTO.getPlayer2Id())
            .orElseThrow(() -> new RuntimeException("Jogador 2 não encontrado"));

        Map<Long, List<Long>> selectedEvents = gameDTO.getSelectedEvents();

        
        List<Event> player1Events = eventRepository.findAllById(selectedEvents.get(player1.getId()));
        List<Event> player2Events = eventRepository.findAllById(selectedEvents.get(player2.getId()));

        int player1Score = player1Events.stream().mapToInt(Event::getWeight).sum();
        int player2Score = player2Events.stream().mapToInt(Event::getWeight).sum();

        player1.setScore(player1.getScore() + player1Score);
        player2.setScore(player2.getScore() + player2Score);

        player1.getEvents().clear();
        player1.getEvents().addAll(player1Events);

        player2.getEvents().clear();
        player2.getEvents().addAll(player2Events);

        Player winner = player1;
        Player loser = player2;


        Random rand = new Random();

        if (Objects.equals(player1.getScore(), player2.getScore())) {
            if (rand.nextBoolean()) {
                player1.setScore(player1.getScore() + 2);
            } else {
                player2.setScore(player2.getScore() + 2);
            }
        }

        if (player1.getScore() < player2.getScore()) {
            winner = player2;
            loser = player1;
        }

        winner.setStatus(Status.WINNER);
        loser.setStatus(Status.LOSER);

        winner.setScore(winner.getScore() + 30);
        
        playerRepository.save(player1);
        playerRepository.save(player2);

        game.setWinner(winner);
        game.setFinished(true);

        return gameRepository.save(game);
    }

    @Override
    public Game saveGame(Game game, Long roundId) {
        Round round = roundRepository.findById(roundId).orElseThrow(() -> new RuntimeException("Round não encontrado"));
    
        game.setRound(round);
        return gameRepository.save(game);
    }

    @Override
    public Game findById(Long gameId) {
        return gameRepository.findById(gameId).orElse(null);
    }

    @Override
    public Game calculateRandScore(GameDTO gameDTO) {
        Game game = gameRepository.findById(gameDTO.getGameId())
        .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));

        Player player1 = playerRepository.findById(gameDTO.getPlayer1Id())
            .orElseThrow(() -> new RuntimeException("Jogador 1 não encontrado"));

        Player player2 = playerRepository.findById(gameDTO.getPlayer2Id())
            .orElseThrow(() -> new RuntimeException("Jogador 2 não encontrado"));

        Map<Long, List<Long>> selectedEvents = gameDTO.getSelectedEvents();

        List<Event> allEvents = eventRepository.findAllById(selectedEvents.get(player1.getId()));

        Collections.shuffle(allEvents);
        List<Event> player1Events = allEvents.subList(0, 3);
        List<Event> player2Events = allEvents.subList(3, 6);

        int player1Score = player1Events.stream().mapToInt(Event::getWeight).sum();
        int player2Score = player2Events.stream().mapToInt(Event::getWeight).sum();

        player1.setScore(player1.getScore() + player1Score);
        player2.setScore(player2.getScore() + player2Score);

        player1.getEvents().clear();
        player1.getEvents().addAll(player1Events);

        player2.getEvents().clear();
        player2.getEvents().addAll(player2Events);
    
        Player winner = player1;
        Player loser = player2;

        Random rand = new Random();

        if (Objects.equals(player1.getScore(), player2.getScore())) {
            if (rand.nextBoolean()) {
                player1.setScore(player1.getScore() + 2);
            } else {
                player2.setScore(player2.getScore() + 2);
            }
        }

        if (player1.getScore() < player2.getScore()) {
            winner = player2;
            loser = player1;
        }

        winner.setStatus(Status.WINNER);
        loser.setStatus(Status.LOSER);

        winner.setScore(winner.getScore() + 30);
        
        playerRepository.save(player1);
        playerRepository.save(player2);

        game.setWinner(winner);
        game.setFinished(true);

        return gameRepository.save(game);

    }

}
