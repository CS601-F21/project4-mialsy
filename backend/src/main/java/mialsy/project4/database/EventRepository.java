package mialsy.project4.database;


import mialsy.project4.models.Event;
import org.springframework.data.repository.CrudRepository;

/**
 * The interface Event repository for accessing Event in DB
 *
 * @author Chuxi Wang
 */
public interface EventRepository extends CrudRepository<Event, Long> {

}
