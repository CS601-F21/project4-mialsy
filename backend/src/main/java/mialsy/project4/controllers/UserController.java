package mialsy.project4.controllers;

import mialsy.project4.database.UserRepository;
import mialsy.project4.models.User;
import mialsy.project4.pojos.UserPojo;
import mialsy.project4.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/user")
    Map<String, Object> getUser(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }

    @GetMapping("/profile")
    UserPojo getProfile(@AuthenticationPrincipal OAuth2User principal) {
        Integer intId = principal.getAttribute("id");
        Long id = Long.valueOf(intId);
        String username = principal.getAttribute("login");
        String name = principal.getAttribute("name");

        User user = AuthUtil.getLoginUser(repository, principal);
        if (user == null) {
            return upsertUser(id, username, name);
        } else {
            return user.toPojo();
        }
    }

    @RequestMapping(path = "/profile", method = RequestMethod.PUT)
    UserPojo updateUserName(@AuthenticationPrincipal OAuth2User principal,
                        @RequestParam(name = "name") String name) {
        Integer intId = principal.getAttribute("id");
        Long id = Long.valueOf(intId);
        String username = principal.getAttribute("login");
        return upsertUser(id, username, name);
    }

    private UserPojo upsertUser(Long id, String githubUsername, String name) {
        User user = repository.findById(id).orElse(new User());
        user.setGithubUsername(githubUsername);
        user.setName(name);
        repository.save(user);
        return user.toPojo();
    }

}
