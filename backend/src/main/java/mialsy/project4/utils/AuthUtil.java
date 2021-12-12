package mialsy.project4.utils;

import mialsy.project4.database.UserRepository;
import mialsy.project4.models.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 * The utils for authentication
 *
 * @author Chuxi Wang
 */
public class AuthUtil {
    /**
     * Gets logged in user.
     *
     * @param userRepository the user repository
     * @param principal      the current logged in user principal
     * @return the logged in user
     */
    public static User getLoginUser(UserRepository userRepository, OAuth2User principal) {
        return userRepository.findUserByEmail(principal.getAttribute("email")).orElse(null);
    }

    /**
     * Gets logged in user email.
     *
     * @param principal the current logged in user principal
     * @return the logged in user's email
     */
    public static String getLoginUserEmail(OAuth2User principal) {
        return principal.getAttribute("email");
    }
}
