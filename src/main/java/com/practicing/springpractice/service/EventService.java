package com.practicing.springpractice.service;

import com.practicing.springpractice.dto.EventDto;

public interface EventService {
    void createEvent(Long clubId, EventDto event);
}
