package mialsy.project4.controllers;

import mialsy.project4.database.EventRepository;
import mialsy.project4.database.TransactionRepository;
import mialsy.project4.database.UserRepository;
import mialsy.project4.models.Event;
import mialsy.project4.models.Transaction;
import mialsy.project4.models.User;
import mialsy.project4.pojos.TransactionPojo;
import mialsy.project4.utils.AuthUtil;
import mialsy.project4.utils.ErrorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
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
                .collect(Collectors.toSet());
    }

    @PostMapping("/transactions")
    TransactionPojo createTransaction(@RequestParam(name = "eventId") Long eventId,
                                  @AuthenticationPrincipal OAuth2User principal) {
        User user = AuthUtil.getLoginUser(userRepository, principal);
        Event event = eventRepository.findById(eventId)
                    .orElseThrow(() -> ErrorUtil.getObjectNotFoundException(Event.class.getName(), eventId));

        Transaction transaction = new Transaction();

        if (event.getCount() == 0) {
            throw ErrorUtil.getUnprocessableEntityException();
        }
        event.decrementCount();
        eventRepository.save(event);

        transaction.setEvent(event);
        transaction.setUser(user);

        transactionRepository.save(transaction);
        return transaction.toPojo();
    }

    @RequestMapping(path = "/transaction", method = RequestMethod.PUT)
    TransactionPojo transferTransaction(@AuthenticationPrincipal OAuth2User principal,
                                        @RequestParam(name = "transactionId") Long transactionId,
                                        @RequestParam(name = "toUser") Long toUserId) {
        User toUser = userRepository
                .findById(toUserId)
                .orElseThrow(() -> ErrorUtil.getObjectNotFoundException(User.class.getName(), toUserId));
        Transaction transaction = transactionRepository
                    .findById(transactionId)
                    .orElseThrow(() -> ErrorUtil.getObjectNotFoundException(Transaction.class.getName(), transactionId));

        // check if the login user owns the transaction
        if (!transaction.getUser().getEmail().equals(AuthUtil.getLoginUserEmail(principal))) {
            throw ErrorUtil.getNotAuthorizedException();
        }

        transaction.setUser(toUser);
        transactionRepository.save(transaction);
        return transaction.toPojo();
    }
}
