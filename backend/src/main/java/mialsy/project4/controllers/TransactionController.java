package mialsy.project4.controllers;

import mialsy.project4.database.TransactionRepository;
import mialsy.project4.models.Transaction;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {
    private final TransactionRepository repository;

    public TransactionController(TransactionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/transactions")
    Iterable<Transaction> getTransactions(){
        // TODO: filter on userid, need DAO
        return repository.findAll();
    }

    @PostMapping("/transactions")
    Transaction createTransaction(@RequestBody Transaction transaction) {
        repository.save(transaction);
        return transaction;
    }

}
