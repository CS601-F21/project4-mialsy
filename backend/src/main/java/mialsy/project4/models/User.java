package mialsy.project4.models;

import mialsy.project4.pojos.UserPojo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "github_id", unique = true, nullable = false)
    private Long githubId;

    @Column(name = "name")
    private String name;

    @Column(name = "github_username", unique = true, nullable = false)
    private String githubUsername;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Set<Transaction> transactions = new HashSet<>();

    public User() {
    }

    public Long getId() {
        return id;
    }

    public Long getGithubId() {
        return githubId;
    }

    public String getName() {
        return name;
    }

    public String getGithubUsername() {
        return githubUsername;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setGithubId(Long githubId) {
        this.githubId = githubId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGithubUsername(String githubUsername) {
        this.githubUsername = githubUsername;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public UserPojo toPojo() {
        return new UserPojo(id, githubId, name, githubUsername);
    }
}
