package mialsy.project4.controllers;

import mialsy.project4.database.UserRepository;
import mialsy.project4.models.User;
import mialsy.project4.pojos.UserPojo;
import mialsy.project4.utils.AuthUtil;
import mialsy.project4.utils.ErrorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

/**
 * The User controller for CRUD on user.
 *
 * @author Chuxi Wang
 */
@RestController
public class UserController {

    /**
     * Autowired user repository instance
     */
    private final UserRepository repository;

    /**
     * Constructor for user controller
     *
     * @param repository Autowired user repository instance
     */
    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Gets user by email.
     *
     * @param email the email
     * @return the user matching the input email
     */
    @GetMapping("/user")
    UserPojo getUserByEmail(@RequestParam(name = "email") String email) {
        return repository.findUserByEmail(email)
                .orElseThrow(() -> ErrorUtil.getObjectNotFoundException(User.class.getName(), "email", email)).toPojo();
    }

    /**
     * Gets profile of the logged in user
     *
     * @param principal the current logged in user principal
     * @return the pojo of current user
     */
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

    /**
     * Update the current user's name.
     *
     * @param principal the current logged in user principal
     * @param name      the name to update to
     * @return the updated user
     */
    @RequestMapping(path = "/profile", method = RequestMethod.PUT)
    UserPojo updateUserName(@AuthenticationPrincipal OAuth2User principal,
                        @RequestParam(name = "name") String name) {
        String email = principal.getAttribute("email");
        String picture = principal.getAttribute("picture");
        return upsertUser(email, name, picture);
    }

    /**
     * Helper method for upsert user in db
     *
     * @param email the user's email
     * @param name the user's name
     * @param picture the user's avatar link
     * @return the saved user
     */
    private UserPojo upsertUser(String email, String name, String picture) {
        User user = repository.findUserByEmail(email).orElse(new User());
        user.setName(name);
        user.setPicture(picture);
        user.setEmail(email);
        repository.save(user);
        return user.toPojo();
    }

}
