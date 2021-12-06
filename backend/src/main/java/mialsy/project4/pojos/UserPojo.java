package mialsy.project4.pojos;

public class UserPojo {

    private final Long id;

    private final String name;

    private final String email;

    private final String picture;

    public UserPojo(Long id, String name, String email, String picture) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPicture() {
        return picture;
    }
}
