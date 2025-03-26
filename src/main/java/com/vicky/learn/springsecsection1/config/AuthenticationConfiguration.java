package com.vicky.learn.springsecsection1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthenticationConfiguration {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.authorizeHttpRequests((authorizeRequests) -> authorizeRequests
				.requestMatchers("/account", "/contact").permitAll().requestMatchers("/welcome").authenticated());

		// For disabling logic page
		// httpSecurity.formLogin(t -> t.disable());
		// For enabling default logic page
		httpSecurity.formLogin(Customizer.withDefaults());
		return httpSecurity.build();

	}

	@Bean
	UserDetailsService userDetailsService() {
		UserDetails userDetails = User.withUsername("vignesh").password("{noop}123456").authorities("read").build();
		UserDetails userDetails2 = User.withUsername("admin").password("{noop}123456").authorities("admin").build();
		return new InMemoryUserDetailsManager(userDetails, userDetails2);
	}

}
