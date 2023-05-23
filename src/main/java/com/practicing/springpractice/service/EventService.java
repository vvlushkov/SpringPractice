package com.practicing.springpractice.service;

import com.practicing.springpractice.dto.EventDto;

import java.util.List;

public interface EventService {

    void createEvent(Long clubId, EventDto event);

    List<EventDto> findAllEvents();

    EventDto findEventById(Long eventId);

    void updateEvent(EventDto eventDto);

    void delete(Long eventId);
}
