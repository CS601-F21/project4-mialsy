package mialsy.project4.utils;

import mialsy.project4.database.UserRepository;
import mialsy.project4.models.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class AuthUtil {
    public static User getLoginUser(UserRepository userRepository, OAuth2User principal) {
        return userRepository.findUserByEmail(principal.getAttribute("email")).orElse(null);
    }

    public static String getLoginUserEmail(OAuth2User principal) {
        return principal.getAttribute("email");
    }
}
