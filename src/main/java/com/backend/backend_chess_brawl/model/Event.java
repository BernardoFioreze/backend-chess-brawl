package com.backend.backend_chess_brawl.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "event")
public class Event {

    public Event() {
        
    }

    public Event(String name, Integer weight) {
        this.name = name;
        this.weight = weight;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private Integer weight;

    @ManyToOne
    private Player player;

}
