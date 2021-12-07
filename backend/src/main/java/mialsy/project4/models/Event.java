package mialsy.project4.models;

import javax.persistence.*;

@Entity
@Table(name = "event", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "count")
    private Integer count;

    @Column(name = "time")
    private Long time;

    @Column(name = "location")
    private String location;

    @Column(name = "picture")
    private String picture;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCount() {
        return count;
    }

    public Long getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getPicture() {
        return picture;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public void decrementCount() {
        this.count--;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
