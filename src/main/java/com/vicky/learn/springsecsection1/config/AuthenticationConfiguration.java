package com.vicky.learn.springsecsection1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

@Configuration
@EnableWebSecurity
public class AuthenticationConfiguration {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.authorizeHttpRequests((authorizeRequests) -> authorizeRequests
				.requestMatchers("/account", "/contact","/error*").permitAll()
				.requestMatchers("/**").authenticated()
				//.requestMatchers("/account", "/contact","/errormessage").permitAll()
				);
		

		// For disabling logic page
		// httpSecurity.formLogin(t -> t.disable());
		// For enabling default logic page
		httpSecurity.formLogin(Customizer.withDefaults());
		return httpSecurity.build();

	}

	@Bean
	UserDetailsService userDetailsService() {
		UserDetails userDetails = User.withUsername("vignesh").password("{bcrypt}$2a$12$rilLivkp.PsM.WbOq07hl.f888a1e21ZyR1WO7/6gaKWfr0ApIJoy").authorities("read").build();
		UserDetails userDetails2 = User.withUsername("admin").password("{bcrypt}$2a$12$rilLivkp.PsM.WbOq07hl.f888a1e21ZyR1WO7/6gaKWfr0ApIJoy").authorities("admin").build();
		return new InMemoryUserDetailsManager(userDetails, userDetails2);
	}
	
	@Bean
	PasswordEncoder passwordEncoder()
	{
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	/**
	 * This will check the password is compromised or not from below API
	 * 
	 * https://api.pwnedpasswords.com/range/
	 * 
	 * @return
	 */
	@Bean
	CompromisedPasswordChecker compromisedPasswordChecker()
	{
		//return new HaveIBeenPwnedRestApiPasswordChecker();
		return null;
	}

}
