package com.nt.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nt.exception.ResourceNotFoundException;
import com.nt.model.Event;
import com.nt.repository.EventRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    
    public Page<Event> getAllEvents(String name, String date, Pageable pageable) {
        LocalDate parsedDate = null;
        try {
            if (date != null && !date.isEmpty()) {
                parsedDate = LocalDate.parse(date);
            }
        } catch (Exception ignored) {}

        if (name != null || parsedDate != null) {
            return eventRepository.findByNameContainingIgnoreCaseOrDate(name, parsedDate, pageable);
        }
        return eventRepository.findAll(pageable);
    }

    
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id " + id));
    }

    
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    
    public Event updateEvent(Long id, Event updatedEvent) {
        Event event = getEventById(id);
        event.setName(updatedEvent.getName());
        event.setDescription(updatedEvent.getDescription());
        event.setDate(updatedEvent.getDate());
        event.setLocation(updatedEvent.getLocation());
        return eventRepository.save(event);
    }

   
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }


	public void getMatchingEvent(String name) {
		
		  eventRepository.findByName(name);
	}
}