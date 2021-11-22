package mialsy.project4.pojos;

import mialsy.project4.models.Event;
import mialsy.project4.models.User;

import javax.persistence.*;

public class TransactionPojo {

    private Long id;

    private Long userId;

    private Long eventId;

    public TransactionPojo(Long id, Long userId, Long eventId) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

}
