package mialsy.project4.pojos;

/**
 * The User POJO.
 *
 * @author Chuxi Wang
 */
public class UserPojo {

    private final Long id;  /* the user id */

    private final String name;  /* the user's name */

    private final String email; /* the user's email */

    private final String picture; /* the user's picture url */

    /**
     * Instantiates a new User pojo.
     *
     * @param id      the id
     * @param name    the name
     * @param email   the email
     * @param picture the picture
     */
    public UserPojo(Long id, String name, String email, String picture) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
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
}
