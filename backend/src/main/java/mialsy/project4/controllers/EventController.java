package mialsy.project4.controllers;

import mialsy.project4.database.EventRepository;
import mialsy.project4.models.Event;
import mialsy.project4.utils.ErrorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {
    @Autowired
    private EventRepository repository;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/events")
    Iterable<Event> getEvents(){
       return repository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/events")
    Event createEvent(@RequestBody Event newEvent) {
        return repository.save(newEvent);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/event")
    Event getEventById(@RequestParam(name = "id") Long id) {
        return repository.findById(id)
                .orElseThrow(() -> ErrorUtil.getObjectNotFoundException(Event.class.getName(), id));
    }
}
