package mialsy.project4.controllers;

import mialsy.project4.database.EventRepository;
import mialsy.project4.database.TransactionRepository;
import mialsy.project4.database.UserRepository;
import mialsy.project4.models.Event;
import mialsy.project4.models.Transaction;
import mialsy.project4.models.User;
import mialsy.project4.pojos.TransactionPojo;
import mialsy.project4.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/transactions")
    Iterable<TransactionPojo> getTransactions(@AuthenticationPrincipal OAuth2User principal){
        User user = AuthUtil.getLoginUser(userRepository, principal);
        return transactionRepository.findAllByUser(user)
                .stream()
                .filter(Objects::nonNull)
                .map(Transaction::toPojo)
                .collect(Collectors.toList());
    }

    @PostMapping("/transactions")
    TransactionPojo createTransaction(@RequestParam(name = "eventId") Long eventId,
                                  @AuthenticationPrincipal OAuth2User principal) {
        User user = AuthUtil.getLoginUser(userRepository, principal);
        Event event = eventRepository.findById(eventId).orElse(null);
        Transaction transaction = new Transaction();

        if (event.getCount() == 0) {
            return null;
        }
        event.decrementCount();
        eventRepository.save(event);

        transaction.setEvent(event);
        transaction.setUser(user);

        transactionRepository.save(transaction);
        return transaction.toPojo();
    }

    @RequestMapping(path = "/transaction", method = RequestMethod.PUT)
    TransactionPojo transferTransaction(@RequestParam(name = "transactionId") Long transactionId,
                                        @RequestParam(name = "toUser") Long toUserId) {
        User user = userRepository.findById(toUserId).orElse(null);
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);
        if (transaction == null) return null;
        transaction.setUser(user);
        transactionRepository.save(transaction);
        return transaction.toPojo();
    }
}
