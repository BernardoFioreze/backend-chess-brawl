package com.backend.backend_chess_brawl.service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend_chess_brawl.model.Event;
import com.backend.backend_chess_brawl.model.Player;
import com.backend.backend_chess_brawl.model.Tournament;
import com.backend.backend_chess_brawl.repository.PlayerRepository;
import com.backend.backend_chess_brawl.repository.TournamentRepository;

@Service
public class PlayerService implements IPlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    @Override
    public Player addPlayerToTurnament(Long tournamentId, String name, String nickname, String ranking) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
        .orElseThrow(() -> new RuntimeException("Torneio não encontrado"));

        Player player = new Player(name, nickname, ranking);
        player.setTournament(tournament);

        return playerRepository.save(player);
    }

    @Override
    public Player addEvent(List<Event> events, Long playerId) {
        
        Player player = playerRepository.findById(playerId)
        .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));

        Set<Long> existingIds = player.getEvents().stream()
        .map(Event::getId)
        .filter(Objects::nonNull)
        .collect(Collectors.toSet());

        for (Event event : events) {
            Long eventId = event.getId();
            if (eventId != null && existingIds.contains(eventId)) {
                throw new IllegalStateException("Evento já atribuído ao jogador: ID " + eventId);
            }

            event.setPlayer(player);

            player.setScore(player.getScore() + event.getWeight());
        }

        player.getEvents().addAll(events);

        return playerRepository.save(player);
    }

    @Override
    public List<Player>getPlayersByTournamentId(Long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
        .orElseThrow(() -> new RuntimeException("Torneio não encontrado"));

        return playerRepository.findByTournament(tournament);
    }
    

    @Override
    public void deletePlayer(Long playerId) {
        playerRepository.deleteById(playerId);
    }

    @Override
    public Player findById(Long playerId) {
        return playerRepository.findById(playerId).orElse(null);
    }

    @Override
    public Player findByNickname(String nickname) {
        return playerRepository.findByNickname(nickname).orElse(null);
    }

    @Override
    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

}
