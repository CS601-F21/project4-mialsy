package mialsy.project4.controllers;

import mialsy.project4.database.UserRepository;
import mialsy.project4.models.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/user")
    Map<String, Object> getUser(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }

    @GetMapping("/profile")
    User getProfile(@AuthenticationPrincipal OAuth2User principal) {
        Integer intId = principal.getAttribute("id");
        Long id = Long.valueOf(intId);
        String username = principal.getAttribute("login");
        String name = principal.getAttribute("name");

        User user = repository.findById(id).orElse(null);
        if (user == null) {
            user = new User();
            user.setId(id);
            user.setGithubUsername(username);
            user.setName(name);
            repository.save(user);
        }

        return user;
    }

    @RequestMapping(path = "/profile", method = RequestMethod.PUT)
    User updateUserName(@AuthenticationPrincipal OAuth2User principal,
                        @RequestParam(name = "name") String name) {
        Integer intId = principal.getAttribute("id");
        Long id = Long.valueOf(intId);
        String username = principal.getAttribute("login");
        User user = new User();
        user.setId(id);
        user.setGithubUsername(username);
        user.setName(name);
        repository.save(user);
        return user;
    }

}
