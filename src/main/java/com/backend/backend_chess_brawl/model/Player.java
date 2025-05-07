package com.backend.backend_chess_brawl.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "player")
public class Player {

    public Player(String name, String nickname, String ranking) {
        this.name = name;
        this.nickname = nickname;
        this.ranking = ranking;
        this.score = 70;
        this.status = Status.PLAYING;
    }

    public Player() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "ranking")
    private String ranking;

    @Column(name = "score")
    private Integer score;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("players")
    @JoinTable(
        name = "player_events",
        joinColumns = @JoinColumn(name = "players_id"),
        inverseJoinColumns = @JoinColumn(name = "events_id")
    )
    private List<Event> events = new ArrayList<>();
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    @JsonBackReference
    private Tournament tournament;


}
