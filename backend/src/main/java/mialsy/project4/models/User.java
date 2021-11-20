package mialsy.project4.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "github_username", unique = true, nullable = false)
    private String githubUsername;

    public User() {
    }

    public User(Long id, String name, String githubUsername) {
        this.id = id;
        this.name = name;
        this.githubUsername = githubUsername;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Set<Transaction> transactions;

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
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
}
