package mialsy.project4.pojos;

public class UserPojo {

    private Long id;

    private Long githubId;

    private String name;

    private String githubUsername;

    public UserPojo(Long id, Long githubId, String name, String githubUsername) {
        this.id = id;
        this.githubId = githubId;
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

    public Long getGithubId() {
        return githubId;
    }
}
