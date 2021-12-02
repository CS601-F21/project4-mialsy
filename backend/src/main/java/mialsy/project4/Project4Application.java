package mialsy.project4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RestController
public class Project4Application extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(Project4Application.class, args);
	}

	// reference: "Spring Boot And Oauth2". 2021. Spring.Io. https://spring.io/guides/tutorials/spring-boot-oauth2/.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
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
				.logout(l -> l
						.logoutSuccessUrl("/").permitAll()
				).oauth2Login().successHandler(new OAuthSuccessHandler()).failureHandler(new OAuthFailHandler());

	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000").allowCredentials(true);
			}
		};
	}

}
