package mialsy.project4.controllers;

import mialsy.project4.database.TransactionRepository;
import mialsy.project4.models.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    private final TransactionRepository repository;

    public TransactionController(TransactionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/transactions")
    Iterable<Transaction> getTransactions(){
        return repository.findAll();
    }

}
