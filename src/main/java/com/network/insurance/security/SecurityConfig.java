package com.network.insurance.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests()
		.antMatchers("/upload").hasRole("ADMIN")
		.antMatchers("/**").permitAll()
		.anyRequest().authenticated();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
}
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
}
