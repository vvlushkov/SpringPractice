package com.practicing.springpractice.controller;

import com.practicing.springpractice.dto.ClubDto;
import com.practicing.springpractice.dto.EventDto;
import com.practicing.springpractice.model.Event;
import com.practicing.springpractice.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/list")
    public String listEvents(Model model) {
        List<EventDto> events = eventService.findAllEvents();
        model.addAttribute("events", events);
        return "events-list";
    }

    @GetMapping("/{eventId}")
    public String eventDetail(@PathVariable("eventId") Long eventId, Model model) {
        EventDto eventDto = eventService.findEventById(eventId);
        model.addAttribute("event", eventDto);
        return "events-detail";
    }

    @GetMapping("/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId,
                                  Model model) {
        Event event = new Event();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "events-create";
    }

    @GetMapping("/{eventId}/edit")
    public String editClubEventForm(@PathVariable("eventId") Long eventId, Model model) {
        EventDto event = eventService.findEventById(eventId);
        model.addAttribute("event", event);
        return "events-edit";
    }

    @PostMapping("/{clubId}")
    public String createEvent(@PathVariable("clubId") Long clubId,
                              @Valid @ModelAttribute("event") EventDto eventDto,
                              BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("event", eventDto);
            return "events-create";
        }
        eventService.createEvent(clubId, eventDto);
        return "redirect:/clubs/" + clubId;
    }

    @PostMapping("/{eventId}/edit")
    public String updateEvent(@PathVariable("eventId") Long eventId,
                             @Valid @ModelAttribute("event") EventDto event,
                             BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("event", event);
            return "events-edit";
        }
        EventDto eventDto = eventService.findEventById(eventId);
        event.setId(eventId);
        event.setClub(eventDto.getClub());
        eventService.updateEvent(event);
        return "redirect:/events/list";
    }

    @GetMapping("{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Long eventId) {
        eventService.delete(eventId);;
        return "redirect:/events/list";
    }
}
