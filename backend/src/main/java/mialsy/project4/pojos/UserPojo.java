package mialsy.project4.pojos;

import javax.persistence.Column;

public class UserPojo {

    private Long id;

    private String name;

    private String githubUsername;

    public UserPojo(Long id, String name, String githubUsername) {
        this.id = id;
        this.name = name;
        this.githubUsername = githubUsername;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGithubUsername() {
        return githubUsername;
    }
}
