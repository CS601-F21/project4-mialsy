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
    Iterable<Event> events(){
       return repository.findAll();
    }

    @PostMapping(value = "/events")
    Event newEvent(@RequestBody Event newEvent) {
        return repository.save(newEvent);
    }

    @GetMapping("/events/id={id}")
    Event one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id not valid" + id));
    }
}
