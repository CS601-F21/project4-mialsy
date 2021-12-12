package mialsy.project4.controllers;

import mialsy.project4.database.EventRepository;
import mialsy.project4.models.Event;
import mialsy.project4.utils.ErrorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * The controller for event CRUD.
 *
 * @author Chuxi Wang
 */
@RestController
public class EventController {
    /**
     * Autowired repository instance
     */
    private final EventRepository repository;

    /**
     * Construct event controller
     * @param repository autowired event repository instance
     */
    @Autowired
    public EventController(EventRepository repository) {
        this.repository = repository;
    }

    /**
     * Get all events.
     *
     * @return all events
     */
    @GetMapping("/events")
    Iterable<Event> getEvents(){
       return repository.findAll();
    }

    /**
     * Create a new event.
     *
     * @param newEvent the new event
     * @return the saved event
     */
    @PostMapping(value = "/events")
    Event createEvent(@RequestBody Event newEvent) {
        return repository.save(newEvent);
    }

    /**
     * Gets a event by id.
     *
     * @param id the event id
     * @return the event matching id
     */
    @GetMapping("/event")
    Event getEventById(@RequestParam(name = "id") Long id) {
        System.out.println(id);
        return repository.findById(id)
                .orElseThrow(() -> ErrorUtil.getObjectNotFoundException(Event.class.getName(), id));
    }
}
