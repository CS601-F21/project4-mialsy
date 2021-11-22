package mialsy.project4.utils;

import mialsy.project4.database.UserRepository;
import mialsy.project4.models.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class AuthUtil {
    public static User getLoginUser(UserRepository userRepository, OAuth2User principal) {
        Integer intId = principal.getAttribute("id");
        Long id = Long.valueOf(intId);
        return userRepository.findByGithubId(id).orElseThrow(null);
    }

    public static Long getLoginUserGithubId(OAuth2User principal) {
        Integer intId = principal.getAttribute("id");
        return Long.valueOf(intId);
    }
}
