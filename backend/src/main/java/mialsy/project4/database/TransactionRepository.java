package mialsy.project4.database;

import mialsy.project4.models.Transaction;
import mialsy.project4.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findAllByUser(User user);
}
