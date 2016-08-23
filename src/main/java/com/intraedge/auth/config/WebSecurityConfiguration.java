package com.intraedge.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import com.intraedge.auth.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
  private CustomLogoutSuccessHandler customLogoutSuccessHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new StandardPasswordEncoder();
	}
	
	
	@Override
  protected void configure(final AuthenticationManagerBuilder auth) 
    throws Exception {
		System.out.println("password="+passwordEncoder().encode("password"));
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
  } 
	
	@Override  
  public void configure(WebSecurity web) throws Exception {  
    web  
        .ignoring()  
        .antMatchers("/healthcheck")
        //.antMatchers("/oauth/authorize")
        /*.antMatchers("/users")
    		.antMatchers("/roles")*/;
  }  
	
	@Override
  protected void configure(HttpSecurity http) throws Exception {
      http.csrf().disable()
      		.authorizeRequests()
          	.anyRequest().authenticated()
          	.and()
          .formLogin()
          	.loginPage("/login")
          	.permitAll()
          	.and()
          .logout()
          	.logoutUrl("/logout")
          	.logoutSuccessHandler(customLogoutSuccessHandler)
          	.logoutSuccessUrl("/login?logout")
        		.permitAll();
  }
	
	@Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
  }
}
