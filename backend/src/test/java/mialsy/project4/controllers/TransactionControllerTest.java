package mialsy.project4.controllers;

import com.google.gson.Gson;
import mialsy.project4.database.EventRepository;
import mialsy.project4.database.TransactionRepository;
import mialsy.project4.database.UserRepository;
import mialsy.project4.models.Event;
import mialsy.project4.models.Transaction;
import mialsy.project4.models.User;
import mialsy.project4.pojos.TransactionPojo;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TransactionController.class)
class TransactionControllerTest {

    Gson gson = new Gson();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    TransactionRepository transactionRepository;

    @MockBean
    UserRepository userRepository;

    @MockBean
    EventRepository eventRepository;

    User user;

    User toUser;

    Transaction transaction;

    Event event;

    RequestPostProcessor authentication;

    @BeforeEach
    void setUp() {
        String registrationId = "mock-google-id";
        String mockUserEmail = "doudou@usfca.edu";
        String mockUserName = "doudou";
        String mockToUserName = "Gumball";
        String mockToUserEmail = "gumball@usfca.edu";
        String mockEventName = "concert";

        Map<String, Object> attributesMap = new HashMap<>();
        attributesMap.put("name", mockUserName);
        attributesMap.put("email", mockUserEmail);

        OAuth2User principal = new DefaultOAuth2User(
                asList(new SimpleGrantedAuthority("ROLE_USER")), attributesMap, "name");

        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        this.user = new User();
        user.setEmail(mockUserEmail);
        user.setName(mockUserName);
        user.setId(1L);
        this.toUser = new User();
        toUser.setId(2L);
        toUser.setName(mockToUserName);
        toUser.setEmail(mockToUserEmail);
        this.event = new Event();
        event.setId(1L);
        event.setName(mockEventName);
        this.transaction = new Transaction();
        transaction.setId(1L);
        transaction.setUser(user);
        transaction.setEvent(event);
        when(this.eventRepository.findById(any())).thenReturn(Optional.of(event));
        when(this.userRepository.findUserByEmail(any())).thenReturn(Optional.of(user));
        when(this.userRepository.findById(any())).thenReturn(Optional.of(toUser));
        when(transactionRepository.save(any())).thenReturn(transaction);
        when(transactionRepository.findById(any())).thenReturn(Optional.of(transaction));
        when(this.transactionRepository.findAllByUser(any())).thenReturn(asList(transaction));
        this.authentication =
                authentication(new OAuth2AuthenticationToken(principal, principal.getAuthorities(), registrationId));
    }


    // Reference https://medium.com/@mark.hoogenboom/testing-a-spring-boot-application-secured-by-oauth-e40d1e9a6f60
    // for Authentication mock
    @Test
    void getTransactionsSuccessTest() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.
                get("/transactions").with(authentication))
                .andExpect(status().isOk()).andReturn();
        String contentString = mvcResult.getResponse().getContentAsString();
        Map<String, String>[] returnedTransactions = gson.fromJson(contentString, Map[].class);
        Assertions.assertThat(returnedTransactions.length).isEqualTo(1);
        Assertions.assertThat(returnedTransactions[0].get("eventName")).isEqualTo(this.event.getName());
    }

    @Test
    void createTransactionSuccessfulTest() throws Exception {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        int originalCount = 1;
        this.event.setCount(originalCount);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/transactions").with(authentication).with(csrf())
                .param("eventId", this.event.getId().toString())
                )
                .andExpect(status().isOk()).andReturn();
        String contentString = mvcResult.getResponse().getContentAsString();
        TransactionPojo returnedTransaction = gson.fromJson(contentString, TransactionPojo.class);
        Assertions.assertThat(returnedTransaction.getEventId()).isEqualTo(this.event.getId());
        Assertions.assertThat(this.event.getCount()).isEqualTo(originalCount - 1);
    }

    @Test
    void createTransactionWithNoTicketTest() throws Exception {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        int originalCount = 0;
        this.event.setCount(originalCount);
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/transactions").with(authentication).with(csrf())
                .param("eventId", this.event.getId().toString())
        )
                .andExpect(status().is4xxClientError());
    }

    @Test
    void transferTransactionSuccessTest() throws Exception {
        int originalCount = 1;
        this.event.setCount(originalCount);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
                .put("/transaction").with(authentication).with(csrf())
                .param("transactionId", this.transaction.getId().toString())
                .param("toUser", this.toUser.getId().toString())
        )
                .andExpect(status().isOk()).andReturn();
        String contentString = mvcResult.getResponse().getContentAsString();
        TransactionPojo returnedTransaction = gson.fromJson(contentString, TransactionPojo.class);
        Assertions.assertThat(returnedTransaction.getUserId()).isEqualTo(this.toUser.getId());
    }

    @Test
    void transferTransactionFailedNotAuthorizedTest() throws Exception {
        int originalCount = 1;
        this.event.setCount(originalCount);
        this.user.setEmail("test@usfca.edu");
        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/transaction").with(authentication).with(csrf())
                .param("transactionId", this.transaction.getId().toString())
                .param("toUser", this.toUser.getId().toString())
        )
                .andExpect(status().is4xxClientError());
    }
}