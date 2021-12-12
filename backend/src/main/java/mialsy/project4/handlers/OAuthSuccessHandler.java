package mialsy.project4.handlers;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The Oauth success handler.
 *
 * @author Chuxi Wang
 */
@Component
public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    /**
     * Redirect strategy instance for sending redirect, reference: https://www.baeldung.com/spring_redirect_after_login
     */
    private static final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * send redirect on oauth success
     * @param request the http request
     * @param response the http response
     * @param authentication the authentication instance
     * @throws IOException io exception
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException {
        // Sending token back to the front end authentication
        // reference: https://stackoverflow.com/questions/42085510/spring-redirect-with-authorization-header
        redirectStrategy.sendRedirect(request, response, "http://localhost:3000/profile");
    }


}
