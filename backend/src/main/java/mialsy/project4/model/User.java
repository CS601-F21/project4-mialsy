package mialsy.project4.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "twitterId")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "twitterId", unique = true, nullable = false)
    private Long twitterId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "transactionId")
    private Set<Transaction> transactions;

    public Integer getId() {
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

    public void setId(Integer id) {
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
