package com.acculytixs.wayuparty.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.acculytixs.wayuparty.CustomTokenAuthenticationFilter;
import com.acculytixs.wayuparty.RestAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
	
	
	 @Configuration
	    @Order(1)
	    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
	    	
	    	@Autowired
	    	CustomTokenAuthenticationFilter customTokenAuthenticationFilter;
	    	
	    	@Autowired
	    	RestAuthenticationEntryPoint restAuthenticationEntryPoint;
		   
	       @Bean
	   	   @Override
	   	   public AuthenticationManager authenticationManagerBean() throws Exception {
	   	       return super.authenticationManagerBean();
	   	   }
	       
	        protected void configure(HttpSecurity http) throws Exception {
	        	 http
	        	 .antMatcher("/rest/**").addFilterBefore(customTokenAuthenticationFilter, BasicAuthenticationFilter.class).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint) 
	                 .and()
	                 .authorizeRequests().antMatchers("/rest/**").fullyAuthenticated()
	                 .and().anonymous()
	                  .and()
	                   .securityContext()
	                   .and()
	                   .headers().disable()
	                   .rememberMe().disable()
	                   .requestCache().disable()
	                   .x509().disable()
	                   .csrf().disable()
	                   .httpBasic().disable()
	                   .formLogin().disable()
	                   .logout().disable()
	                   .httpBasic();
	        }
	    }

	
	@Configuration
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
		
		@Autowired
		WayupartyAuthenticationProvider wayupartyAuthenticationProvider;
		
		@Autowired
		WayupartyAuthenticationFailureHandler wayupartyAuthenticationFailureHandler;
		
		@Autowired
		WayupartyAuthenticationSuccessHandler wayupartyAuthenticationSuccessHandler;
		
		@Autowired
		WayupartyLogoutSuccessHandler wayupartyLogoutSuccessHandler;
		
		
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    	auth.authenticationProvider(wayupartyAuthenticationProvider);
	    }
	    

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	http.csrf().disable();
	        http.authorizeRequests()
	                .antMatchers("/home").permitAll()
	                .antMatchers("/login").permitAll()
	                .antMatchers("/forgotPassword").permitAll()
	                .antMatchers("/registerUser").permitAll()
	                .antMatchers("/").permitAll()
	                .antMatchers("/resources/**").permitAll()
	                .antMatchers("/wayuparty-static/**").permitAll()
	                .antMatchers("/ws/**").permitAll()
	                .anyRequest().authenticated()
	                .and().formLogin().loginPage("/login")
	                .usernameParameter("userName")
	    			.passwordParameter("password")
	    			.failureHandler(wayupartyAuthenticationFailureHandler)
	    			.successHandler(wayupartyAuthenticationSuccessHandler)
	    			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
	    			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessHandler(wayupartyLogoutSuccessHandler).logoutSuccessUrl("/logout.done").deleteCookies("JSESSIONID")
	    			.invalidateHttpSession(true)
	    			.and().exceptionHandling().accessDeniedPage("/404");
	    }
	}
}
