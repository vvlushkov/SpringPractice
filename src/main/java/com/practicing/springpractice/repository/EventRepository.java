package com.practicing.springpractice.repository;

import com.practicing.springpractice.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
