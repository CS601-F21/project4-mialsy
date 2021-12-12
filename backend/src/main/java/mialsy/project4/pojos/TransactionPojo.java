package mialsy.project4.pojos;

/**
 * The Transaction POJO.
 *
 * @author Chuxi Wang
 */
public class TransactionPojo {

    private final Long id;           /* the transaction id */

    private final Long userId;      /* id for user owning the transaction */

    private final Long eventId;     /* id for event this transaction is related to */

    private final String eventName; /* name for event this transaction is related to */

    private final String eventDescription;  /* description for event this transaction is related to */

    private final Long eventTime;   /* start time for event this transaction is related to */

    private final String eventLocation;     /* location for event this transaction is related to */

    /**
     * Instantiates a new Transaction pojo.
     *
     * @param id               the transaction id
     * @param userId           the user id
     * @param eventId          the event id
     * @param eventName        the event name
     * @param eventDescription the event description
     * @param eventTime        the event time
     * @param eventLocation    the event location
     */
    public TransactionPojo(Long id, Long userId, Long eventId, String eventName, String eventDescription, Long eventTime, String eventLocation) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventTime = eventTime;
        this.eventLocation = eventLocation;
    }

    /**
     * Gets transaction id.
     *
     * @return the transaction id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Gets event id.
     *
     * @return the event id
     */
    public Long getEventId() {
        return eventId;
    }

    /**
     * Gets event name.
     *
     * @return the event name
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Gets event description.
     *
     * @return the event description
     */
    public String getEventDescription() {
        return eventDescription;
    }

    /**
     * Gets event time.
     *
     * @return the event time
     */
    public Long getEventTime() {
        return eventTime;
    }

    /**
     * Gets event location.
     *
     * @return the event location
     */
    public String getEventLocation() {
        return eventLocation;
    }
}
