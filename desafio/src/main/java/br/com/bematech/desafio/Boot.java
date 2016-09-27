package br.com.bematech.desafio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.bematech.desafio.models.Usuario;
import br.com.bematech.desafio.services.UsuarioService;

@SpringBootApplication
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Boot
{

   public static void main(String[] args)
   {
      SpringApplication.run(Boot.class, args);
   }
   
}


@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {
	

  @Autowired
  UsuarioService usuarioService;

  @Override
  public void init(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService());
  }

  @Bean
  UserDetailsService userDetailsService() {
    return new UserDetailsService() {

      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	     	  
    	  Usuario account = usuarioService.findByUsername(username);
    	  if(account != null) {
    		  return new User(account.getUsername(), account.getPassword(), true, true, true, true,
		        AuthorityUtils.createAuthorityList("USER"));
		  } else {
			  throw new UsernameNotFoundException("could not find the user '"
		          + username + "'");
		  }
	  }
      
    };
  }
}




@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/avaliacao/**").authenticated()
        .antMatchers(HttpMethod.PUT, "/avaliacao/**").authenticated()
        .antMatchers(HttpMethod.DELETE, "/avaliacao/**").authenticated()
        .antMatchers(HttpMethod.GET, "/avaliacao/**").authenticated()
        .anyRequest().permitAll().and()
        .httpBasic().and()
		.csrf().disable();
	}
  
}