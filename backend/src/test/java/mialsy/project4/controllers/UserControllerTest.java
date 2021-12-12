package mialsy.project4.controllers;

import com.google.gson.Gson;
import mialsy.project4.database.UserRepository;
import mialsy.project4.models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    Gson gson = new Gson();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    UserRepository userRepository;

    RequestPostProcessor authentication;

    User user;

    ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);

    @BeforeEach
    void setUp() {

        this.user = new User();
        String mockUserEmail = "doudou@usfca.edu";
        String mockUserName = "doudou";
        String registrationId = "mock-google-id";

        Map<String, Object> attributesMap = new HashMap<>();
        attributesMap.put("name", mockUserName);
        attributesMap.put("email", mockUserEmail);

        OAuth2User principal = new DefaultOAuth2User(
                asList(new SimpleGrantedAuthority("ROLE_USER")), attributesMap, "name");
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        user.setEmail(mockUserEmail);
        user.setName(mockUserName);
        user.setId(1L);
        this.authentication =
                authentication(new OAuth2AuthenticationToken(principal, principal.getAuthorities(), registrationId));
    }

    @Test
    void getUserByEmailSuccessTest() throws Exception {
        when(this.userRepository.findUserByEmail(argument.capture())).thenReturn(Optional.of(user));
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.
                get("/user").with(authentication).param("email", this.user.getEmail()))
                .andExpect(status().isOk()).andReturn();
        String contentString = mvcResult.getResponse().getContentAsString();
        User returnedUser = gson.fromJson(contentString, User.class);
        Assertions.assertThat(returnedUser.getName()).isEqualTo(this.user.getName());
        Assertions.assertThat(argument.getValue()).isEqualTo(this.user.getEmail());
    }

    @Test
    void getUserByEmailNotFoundTest() throws Exception {
        when(this.userRepository.findUserByEmail(any())).thenReturn(Optional.empty());
        this.mockMvc.perform(MockMvcRequestBuilders.
                get("/user").with(authentication).param("email", this.user.getEmail()))
                .andExpect(status().isNotFound());
    }

    @Test
    void getProfileExistSuccess() throws Exception {
        when(this.userRepository.findUserByEmail(argument.capture())).thenReturn(Optional.of(user));
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.
                get("/profile").with(authentication))
                .andExpect(status().isOk()).andReturn();
        String contentString = mvcResult.getResponse().getContentAsString();
        User returnedUser = gson.fromJson(contentString, User.class);
        Assertions.assertThat(returnedUser.getName()).isEqualTo(this.user.getName());
        Assertions.assertThat(argument.getValue()).isEqualTo(this.user.getEmail());
    }

    @Test
    void getProfileCreateNewSuccessTest() throws Exception {
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        when(this.userRepository.findUserByEmail(argument.capture())).thenReturn(Optional.empty());
        when(this.userRepository.save(userArgumentCaptor.capture())).thenReturn(this.user);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.
                get("/profile").with(authentication))
                .andExpect(status().isOk()).andReturn();
        String contentString = mvcResult.getResponse().getContentAsString();
        User returnedUser = gson.fromJson(contentString, User.class);
        Assertions.assertThat(returnedUser.getName()).isEqualTo(this.user.getName());
        Assertions.assertThat(argument.getValue()).isEqualTo(this.user.getEmail());
        Assertions.assertThat(userArgumentCaptor.getValue().getName()).isEqualTo(this.user.getName());
    }

    @Test
    void updateUserNameSuccessTest() throws Exception {
        when(this.userRepository.findUserByEmail(argument.capture())).thenReturn(Optional.of(user));
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        String updatedName = "test1";
        when(this.userRepository.save(userArgumentCaptor.capture())).thenReturn(this.user);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
                .put("/profile").with(authentication).with(csrf())
                .param("name", updatedName))
                .andExpect(status().isOk()).andReturn();
        String contentString = mvcResult.getResponse().getContentAsString();
        User returnedUser = gson.fromJson(contentString, User.class);
        Assertions.assertThat(returnedUser.getName()).isEqualTo(updatedName);
        Assertions.assertThat(userArgumentCaptor.getValue().getName()).isEqualTo(updatedName);

    }
}