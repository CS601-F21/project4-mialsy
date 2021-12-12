package mialsy.project4;

import mialsy.project4.handlers.OAuthFailHandler;
import mialsy.project4.handlers.OAuthSuccessHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The Project 4 application Runner Class.
 *
 * @author Chuxi Wang
 */
@SpringBootApplication
@RestController
public class Project4Application extends WebSecurityConfigurerAdapter {
	private static final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Project4Application.class, args);
	}

	/**
	 * Config authentication
	 * @param http HttpSecurity input
	 * @throws Exception throw exception - program terminate when config fails
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// reference: "Spring Boot And Oauth2". 2021. Spring.Io. https://spring.io/guides/tutorials/spring-boot-oauth2/.
		http
				.cors().and()
				.authorizeRequests(a -> a
				// public pages
				.antMatchers("/", "/error", "/webjars/**").permitAll()
				.anyRequest().authenticated())
				.exceptionHandling(e -> e
						.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
				)
				// csrf token
				.csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
				// logout endpoint
				.logout().logoutSuccessHandler(
						new SimpleUrlLogoutSuccessHandler() {
							@Override
							public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
								response.setStatus(HttpServletResponse.SC_OK);
							}
						}
		).permitAll().and().oauth2Login().successHandler(new OAuthSuccessHandler()).failureHandler(new OAuthFailHandler());
	}

	/**
	 * Config CORS policy,
	 * allows cors from frontend server.
	 *
	 * @return the web mvc configurer
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
						.addMapping("/**")
						.allowedOrigins("http://localhost:3000")
						.allowCredentials(true)
						.allowedMethods("*");
			}
		};
	}

}
