package mialsy.project4.controllers;

import com.google.gson.Gson;
import mialsy.project4.database.EventRepository;
import mialsy.project4.models.Event;
import mialsy.project4.models.Transaction;
import mialsy.project4.models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EventController.class)
class EventControllerTest {

    Gson gson = new Gson();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    EventRepository eventRepository;

    Event mockedEvent;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        mockedEvent = new Event();
        Long testId = 1L;
        String mockEventName = "Gumball";
        mockedEvent.setId(testId);
        mockedEvent.setName(mockEventName);


    }

    @Test
    void getEvents() throws Exception {
        when(eventRepository.findAll()).thenReturn(asList(mockedEvent));
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.
                get("/events")).
                andExpect(status().isOk()).andReturn();
        String contentString = mvcResult.getResponse().getContentAsString();
        Event[] returnedEvents = gson.fromJson(contentString, Event[].class);
        Assertions.assertThat(returnedEvents.length).isEqualTo(1);

    }

    @Test
    void createEvent() throws Exception {

        String content = "{\"name\": \"Gumball\"}";
        when(eventRepository.save(any())).thenReturn(mockedEvent);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.
                post("/events").contentType("application/json").content(content)).
                andExpect(status().isOk())
                .andReturn();
        String contentString = mvcResult.getResponse().getContentAsString();
        Event returnedEvent = gson.fromJson(contentString, Event.class);
        Assertions.assertThat(returnedEvent).isEqualToComparingFieldByField(mockedEvent);
    }

    @Test
    void getEventById() throws Exception {
        Mockito.when(eventRepository.findById(mockedEvent.getId())).thenReturn(Optional.of(mockedEvent));
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        this.mockMvc.perform(MockMvcRequestBuilders.
                get("/event").param("id", mockedEvent.getId().toString())).
                andExpect(status().isOk());
    }

    @Test
    void getEventIdNotFound() throws Exception {
        Long testId = 123L;
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        this.mockMvc.perform(MockMvcRequestBuilders.
                get("/event").param("id", testId.toString())).
                andExpect(status().isNotFound());
    }
}