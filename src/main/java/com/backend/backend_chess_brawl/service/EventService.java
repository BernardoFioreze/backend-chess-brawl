package com.backend.backend_chess_brawl.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.backend.backend_chess_brawl.model.Event;
import com.backend.backend_chess_brawl.repository.EventRepository;

public class EventService implements IEventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event findById(Long eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }

}
