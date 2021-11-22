package mialsy.project4.controllers;

import mialsy.project4.database.EventRepository;
import mialsy.project4.database.TransactionRepository;
import mialsy.project4.database.UserRepository;
import mialsy.project4.models.Event;
import mialsy.project4.models.Transaction;
import mialsy.project4.models.User;
import mialsy.project4.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/transactions")
    Iterable<Transaction> getTransactions(@AuthenticationPrincipal OAuth2User principal){
        User user = AuthUtil.getLoginUser(userRepository, principal);
        return transactionRepository.findAllByUser(user);
    }

    @PostMapping("/transactions")
    Transaction createTransaction(@RequestParam(name = "eventId") Long eventId,
                                  @AuthenticationPrincipal OAuth2User principal) {
        Transaction transaction = new Transaction();

        User user = AuthUtil.getLoginUser(userRepository, principal);
        transaction.setUser(user);

        Event event = eventRepository.findById(eventId).orElse(null);
        if (event.getCount() == 0) {
            return null;
        }
        event.decrementCount();
        eventRepository.save(event);
        transaction.setEvent(event);

        transactionRepository.save(transaction);
        return transaction;
    }

}
