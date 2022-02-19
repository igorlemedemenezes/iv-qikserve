package br.com.iv.qikserve.security.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.iv.qikserve.security.JWTAuthenticationFilter;
import br.com.iv.qikserve.security.JWTAuthorizationFilter;
import br.com.iv.qikserve.security.JWTUtil;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	private static final String[] PUBLIC_MATCHERS_GET = {
			"/basket/**",
			"/product/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_POST = {
			"/product/**",
			"/basket/**",
			"/admin/**"
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		http.authorizeRequests()
			.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
			.antMatchers(HttpMethod.GET,PUBLIC_MATCHERS_GET).permitAll()
			.anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
//		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000/"));
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
	    configuration.setAllowedHeaders(Arrays.asList("X-Requested-With","Origin","Content-Type","Accept","Authorization"));
	    // This allow us to expose the headers
	    configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
	            "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
