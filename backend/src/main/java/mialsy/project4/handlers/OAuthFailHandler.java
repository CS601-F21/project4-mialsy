package mialsy.project4.handlers;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The handler for oauth failed
 *
 * @author Chuxi Wang
 */
@Component
public class OAuthFailHandler extends SimpleUrlAuthenticationFailureHandler {

    /**
     * Super auth fail method
     * @param request the http request
     * @param response the http response
     * @throws IOException io exception
     * @throws ServletException servlet exception
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException, ServletException {
        super.onAuthenticationFailure(request, response, exception);
    }
}