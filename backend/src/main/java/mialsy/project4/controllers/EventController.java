package mialsy.project4.controllers;

import mialsy.project4.database.EventRepository;
import mialsy.project4.models.Event;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {
    private final EventRepository repository;

    public EventController(EventRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/events")
    Iterable<Event> getEvents(){
       return repository.findAll();
    }

    @PostMapping(value = "/events")
    Event createEvent(@RequestBody Event newEvent) {
        return repository.save(newEvent);
    }

    @GetMapping("/event")
    Event getEventById(@RequestParam(name = "id") Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id not valid" + id));
    }
}
