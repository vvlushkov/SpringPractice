package com.practicing.springpractice.service.impl;

import com.practicing.springpractice.dto.EventDto;
import com.practicing.springpractice.mapper.EventMapper;
import com.practicing.springpractice.model.Club;
import com.practicing.springpractice.model.Event;
import com.practicing.springpractice.repository.ClubRepository;
import com.practicing.springpractice.repository.EventRepository;
import com.practicing.springpractice.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.practicing.springpractice.mapper.ClubMapper.mapToClub;
import static com.practicing.springpractice.mapper.EventMapper.mapToEvent;
import static com.practicing.springpractice.mapper.EventMapper.mapToEventDto;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private ClubRepository clubRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
        Club club = clubRepository.findById(clubId).orElseThrow();
        Event event = mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);
    }

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(EventMapper::mapToEventDto).toList();
    }

    @Override
    public EventDto findEventById(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow();
        return mapToEventDto(event);
    }

    @Override
    public void updateEvent(EventDto eventDto) {
        Event event = mapToEvent(eventDto);
        eventRepository.save(event);
    }

    @Override
    public void delete(Long eventId) {
        eventRepository.deleteById(eventId);
    }


}
