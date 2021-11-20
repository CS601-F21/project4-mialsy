package mialsy.project4.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "twitter_id", unique = true, nullable = false)
    private Long twitterId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id")
    private Set<Transaction> transactions;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getTwitterId() {
        return twitterId;
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

    public void setTwitterId(Long twitterId) {
        this.twitterId = twitterId;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
}
