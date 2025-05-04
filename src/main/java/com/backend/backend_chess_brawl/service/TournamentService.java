package com.backend.backend_chess_brawl.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend_chess_brawl.model.Game;
import com.backend.backend_chess_brawl.model.Player;
import com.backend.backend_chess_brawl.model.Round;
import com.backend.backend_chess_brawl.model.Tournament;
import com.backend.backend_chess_brawl.repository.TournamentRepository;

@Service
public class TournamentService implements ITournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

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

        if(players.size() > 4 || players.size() % 2 != 0) {
            throw new IllegalStateException("Torneio precisa de até 4 jogadores e número par.");
        }

        Collections.shuffle(players);
        List<Game> games = new ArrayList<Game>();

        for (int i = 0; i < players.size(); i += 2) {
            Player player1 = players.get(i);
            Player player2 = players.get(i + 1);

            Game game = new Game(player1, player2);
            games.add(gameService.saveGame(game));
        }

        Round round = new Round(games);

        for (Game game : games) {
            game.setRound(round);
        }
        
        tournament.getRounds().add(round);

        return tournamentRepository.save(tournament);
        
    }


    @Override
    public Tournament findById(Long tournamentId) {
        return tournamentRepository.findById(tournamentId).orElse(null);
    }

}
