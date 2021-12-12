package mialsy.project4.models;

import mialsy.project4.pojos.UserPojo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The User model
 *
 * @author Chuxi Wang
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;             /* the user id */

    @Column(name = "name")
    private String name;        /* the user name */

    @Column(name = "email", unique = true, nullable = false)
    private String email;       /* the user email */

    @Column(name = "picture")
    private String picture;     /* the user avatar's url */


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Set<Transaction> transactions = new HashSet<>();    /* the user's transactions */

    /**
     * Instantiates a new User.
     */
    public User() {
    }

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
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
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
     * Gets transactions.
     *
     * @return the transactions
     */
    public Set<Transaction> getTransactions() {
        return transactions;
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
     * Sets transactions.
     *
     * @param transactions the transactions
     */
    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
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
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets picture.
     *
     * @param picture the picture
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * To pojo user pojo.
     *
     * @return the user pojo
     */
    public UserPojo toPojo() {
        return new UserPojo(id, name, email, picture);
    }
}
