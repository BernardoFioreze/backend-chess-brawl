package com.backend.backend_chess_brawl.service;

import java.util.List;

import com.backend.backend_chess_brawl.model.Event;

public interface IEventService {

    Event saveEvent(Event event);

    Event findById(Long id);

    List<Event> findAllEvents();

}
