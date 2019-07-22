package org.mlp.apps.config;



import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.RestController;


@EnableResourceServer
@RestController
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter
{
	  @Override
		public void configure(HttpSecurity http) throws Exception {
		  http.authorizeRequests()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/expert/**").hasRole("EXPERT")
			.antMatchers("/secure/**", "/secured/**", "/user/**").authenticated()
			.anyRequest().permitAll();
		}   

}

