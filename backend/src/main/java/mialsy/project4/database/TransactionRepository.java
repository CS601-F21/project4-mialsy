package mialsy.project4.database;

import mialsy.project4.models.Transaction;
import mialsy.project4.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The interface Transaction repository, for accessing Transactions in DB
 *
 * @author Chuxi Wang
 */
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    /**
     * Find all transactions own by user.
     *
     * @param user the user that owns the transaction
     * @return the transaction list
     */
    List<Transaction> findAllByUser(User user);
}
