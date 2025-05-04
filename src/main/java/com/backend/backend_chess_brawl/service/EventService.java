package com.backend.backend_chess_brawl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend_chess_brawl.model.Event;
import com.backend.backend_chess_brawl.repository.EventRepository;

@Service
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

    @Override
    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    

}
