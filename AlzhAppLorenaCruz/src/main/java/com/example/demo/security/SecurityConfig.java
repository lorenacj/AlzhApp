package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration
			authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests((requests)->requests
				.requestMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
	            .requestMatchers("/", "/home/**", "/imgs/**", "/auth/**", "/css/**", "/files/**","/webjars/**", "/error/**").permitAll()
	            .anyRequest().authenticated()
				)
				.formLogin((form)->form
						.loginPage("/auth/login")
						.defaultSuccessUrl("/home/")
						//.failureUrl("/auth/login?error")
						//.failureHandler()
						.permitAll()
				)
				.logout((logout)->logout.permitAll()
						.logoutUrl("/logout")
						.logoutSuccessUrl("/auth/login?logout"));
		return http.build();
	}
}
