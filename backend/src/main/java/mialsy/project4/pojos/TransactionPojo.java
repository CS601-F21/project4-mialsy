package mialsy.project4.pojos;

public class TransactionPojo {

    private final Long id;

    private final Long userId;

    private final Long eventId;

    private final String eventName;

    private final String eventDescription;

    private final Long eventTime;

    public TransactionPojo(Long id, Long userId, Long eventId, String eventName, String eventDescription, Long eventTime) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventTime = eventTime;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public Long getEventTime() {
        return eventTime;
    }
}
