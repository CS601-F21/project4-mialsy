package mialsy.project4.models;

import javax.persistence.*;

/**
 * The Event model
 *
 * @author Chuxi Wang
 */
@Entity
@Table(name = "event", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;                 /* the event id */

    @Column(name = "name")
    private String name;            /* the event name */

    @Column(name = "description")
    private String description;     /* the event description */

    @Column(name = "count")
    private Integer count;          /* the event count */

    @Column(name = "time")
    private Long time;              /* the event start time */

    @Column(name = "location")
    private String location;        /* the event location */

    @Column(name = "picture")
    private String picture;         /* the event picture url */

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public Long getTime() {
        return time;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Gets picture.
     *
     * @return the picture
     */
    public String getPicture() {
        return picture;
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
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * Sets time.
     *
     * @param time the time
     */
    public void setTime(Long time) {
        this.time = time;
    }

    /**
     * Decrement count.
     */
    public void decrementCount() {
        this.count--;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Sets picture.
     *
     * @param picture the picture
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }
}
