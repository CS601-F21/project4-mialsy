package mialsy.project4.models;

import mialsy.project4.pojos.TransactionPojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * The Transaction model
 *
 * @author Chuxi Wang
 */
@Entity
@Table(name = "transaction", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;        /* the transaction id */

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;      /* the user related to this transaction */

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;    /* the event related to this transaction */

    /**
     * Gets transaction id.
     *
     * @return the transaction id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets event.
     *
     * @return the event
     */
    public Event getEvent() {
        return event;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Sets event.
     *
     * @param event the event
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * To pojo transaction pojo.
     *
     * @return the transaction pojo
     */
    public TransactionPojo toPojo() {
        return new TransactionPojo(id, user.getId(), event.getId(), event.getName(), event.getDescription(), event.getTime(), event.getLocation());
    }
}