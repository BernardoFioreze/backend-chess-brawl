package com.backend.backend_chess_brawl.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend_chess_brawl.model.Game;
import com.backend.backend_chess_brawl.model.Player;
import com.backend.backend_chess_brawl.model.Round;
import com.backend.backend_chess_brawl.model.Status;
import com.backend.backend_chess_brawl.model.Tournament;
import com.backend.backend_chess_brawl.repository.TournamentRepository;

@Service
public class TournamentService implements ITournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private RoundService roundService;

    @Autowired
    private GameService gameService;

    @Override
    public Tournament createTournament() {
        Tournament tournament = new Tournament();
        tournament.setWinner(null);
        return tournamentRepository.save(tournament);
    }

    @Override
    public Tournament startTournament(Long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
        .orElseThrow(() -> new RuntimeException("Torneio não encontrado"));

        List<Player> players = tournament.getPlayers();

        if(players.size() < 4 || players.size() % 2 != 0 || players.size() == 6) {
            throw new IllegalStateException("Torneio precisa de 4 ou 8 players");
        }

        Collections.shuffle(players);
        Round round = new Round();
        round = roundService.saveRound(round, tournamentId);

        List<Game> games = new ArrayList<>();

        for (int i = 0; i < players.size(); i += 2) {
            Player player1 = players.get(i);
            Player player2 = players.get(i + 1);

            Game game = new Game(player1, player2);
            gameService.saveGame(game, round.getId());
        }

        round.setGames(games);
        roundService.saveRound(round, tournamentId);

        return tournamentRepository.save(tournament);
        
    }

    @Override
    public Tournament createRound(Long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
        .orElseThrow(() -> new RuntimeException("Torneio não encontrado"));

        Round previousRound  = tournament.getRounds().getLast();

        List<Player> players = previousRound.getGames().stream()
            .filter(Game::isFinished)
            .map(Game::getWinner)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
 
        Collections.shuffle(players);
        Round round = new Round();
        round = roundService.saveRound(round, tournamentId);

        List<Game> games = new ArrayList<>();

        for (int i = 0; i < players.size(); i += 2) {
            Player player1 = players.get(i);
            Player player2 = players.get(i + 1);

            player1.setStatus(Status.PLAYING);
            player2.setStatus(Status.PLAYING);

            Game game = new Game(player1, player2);
            game.setRound(round);
            game = gameService.saveGame(game, round.getId());
            games.add(game);

        }

        round.setGames(games);
        tournament.getRounds().add(round);
        roundService.saveRound(round, tournamentId);

        return tournamentRepository.save(tournament);

    }

    @Override
    public Tournament findById(Long tournamentId) {
        return tournamentRepository.findById(tournamentId).orElse(null);
    }

}
