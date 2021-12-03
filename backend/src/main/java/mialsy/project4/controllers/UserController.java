package mialsy.project4.controllers;

import mialsy.project4.database.UserRepository;
import mialsy.project4.models.Event;
import mialsy.project4.models.User;
import mialsy.project4.pojos.UserPojo;
import mialsy.project4.utils.AuthUtil;
import mialsy.project4.utils.ErrorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/user")
    User getUserByEmail(@RequestParam(name = "email") String email) {
        return repository.findUserByEmail(email)
                .orElseThrow(() -> ErrorUtil.getObjectNotFoundException(User.class.getName(), "email", email));
    }

    @GetMapping("/profile")
    UserPojo getProfile(@AuthenticationPrincipal OAuth2User principal) {
        String name = principal.getAttribute("name");
        String email = principal.getAttribute("email");
        String picture = principal.getAttribute("picture");

        User user = AuthUtil.getLoginUser(repository, principal);
        if (user == null) {
            return upsertUser(email, name, picture );
        } else {
            return user.toPojo();
        }
    }

    @RequestMapping(path = "/profile", method = RequestMethod.PUT)
    UserPojo updateUserName(@AuthenticationPrincipal OAuth2User principal,
                        @RequestParam(name = "name") String name) {
        String email = principal.getAttribute("email");
        String picture = principal.getAttribute("picture");
        return upsertUser(email, name, picture);
    }

    private UserPojo upsertUser(String email, String name, String picture) {
        User user = repository.findUserByEmail(email).orElse(new User());
        user.setName(name);
        user.setPicture(picture);
        user.setEmail(email);
        repository.save(user);
        return user.toPojo();
    }

}
