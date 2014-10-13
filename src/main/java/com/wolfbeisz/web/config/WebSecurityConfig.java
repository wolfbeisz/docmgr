package com.wolfbeisz.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	//disable csrf as it causes a problem with file uploads
    	http.csrf().disable();
    	
    	http
    		.authorizeRequests()
    			.antMatchers("/webjars/**").permitAll()
    			.anyRequest().authenticated();
    			
    	http
			.formLogin()
				.loginPage("/login").permitAll()
				.and()
			.logout()
				.permitAll();
    }

    @Configuration
    protected static class AuthenticationConfiguration extends
            GlobalAuthenticationConfigurerAdapter {
    	
    	@Autowired
    	private UserDetailsService userDetailsService_;
    	
        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
//            auth
//                    .inMemoryAuthentication()
//                    .withUser("user").password("password").roles("USER");
        	
        	auth.userDetailsService(userDetailsService_);
        }
    }
}
