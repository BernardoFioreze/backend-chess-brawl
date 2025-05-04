package com.backend.backend_chess_brawl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend_chess_brawl.dtos.GameDTO;
import com.backend.backend_chess_brawl.model.Game;
import com.backend.backend_chess_brawl.model.Player;
import com.backend.backend_chess_brawl.model.Round;
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

    @Override
    public Game calculateScore(GameDTO gameDTO) {
        Player player1 = playerRepository.findById(gameDTO.getPlayer1Id())
            .orElseThrow(() -> new RuntimeException("Jogador 1 não encontrado"));

        Player player2 = playerRepository.findById(gameDTO.getPlayer2Id())
            .orElseThrow(() -> new RuntimeException("Jogador 2 não encontrado"));

        Round round = roundRepository.findById(gameDTO.getRoundId())
        .orElseThrow(() -> new RuntimeException("Round não encontrado"));

        Game game = new Game(player1, player2);
        game.setRound(round);

        Player winner;

        if (player1.getScore() > player2.getScore()) {
            winner = player1;
        } else if (player2.getScore() > player1.getScore()) {
            winner = player2;
        } else {
            winner = Math.random() < 0.5 ? player1 : player2;
            winner.setScore(winner.getScore() + 2);
        }

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

}
