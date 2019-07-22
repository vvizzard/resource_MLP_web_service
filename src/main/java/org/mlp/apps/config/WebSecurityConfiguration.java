package org.mlp.apps.config;

import org.mlp.apps.config.user.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@SuppressWarnings("unused") // The role hierarchy for the app
	private static final String ROLE_HIERARCHY = "ROLE_ADMIN > ROLE_MODERATEUR > ROLE_EXPERT > ROLE_USER";
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	       return super.authenticationManagerBean();
	}
	
	@Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new  BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }
   

    @Override
    protected void configure(HttpSecurity http) throws Exception { 
    	http
    	.csrf().disable()
        .authorizeRequests()
        	.antMatchers("/","/index","/webpublico").permitAll()
        	.antMatchers("/webprivado").authenticated()
        	.antMatchers("/webadmin").hasRole("ADMIN").and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
            .logout() // Metodo get pues he desabilitado CSRF
            .permitAll();
		http.cors();
    	http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll();
    }

}
