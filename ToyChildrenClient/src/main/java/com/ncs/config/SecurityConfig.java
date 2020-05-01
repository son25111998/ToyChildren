package com.ncs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ncs.repository.AccountRepository;

@Configuration
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = AccountRepository.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

//	@Autowired
//	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeRequests().antMatchers("/customer/**", "/admin/**").authenticated().anyRequest().permitAll();

		// Cấu hình cho Login Form.
		http.authorizeRequests().and().formLogin()//
				.loginProcessingUrl("/j_spring_security_login")//
				.loginPage("/login")//
				.defaultSuccessUrl("/user")//
//	        .failureHandler(customAuthenticationFailureHandler)
				.usernameParameter("username")//
				.passwordParameter("password")
				// Cấu hình cho Logout Page.
				.and().logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login?message=logout");
	}
}
