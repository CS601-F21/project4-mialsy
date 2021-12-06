package mialsy.project4;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    // reference: https://www.baeldung.com/spring_redirect_after_login
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private final String TARGET_URL = "http://localhost:3000/profile";
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //reference: https://stackoverflow.com/questions/42085510/spring-redirect-with-authorization-header sending token back to the front endauthentication;
        redirectStrategy.sendRedirect(request, response, TARGET_URL);
    }


}
