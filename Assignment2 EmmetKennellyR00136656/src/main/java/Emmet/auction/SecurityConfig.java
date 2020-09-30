package Emmet.auction;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{


	@Autowired
	DataSource ds;


	@Autowired
	PasswordEncoder pw;

//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/apis/**", "/", "/users/**", "/jobs/**",  "/bids/**", "/registration/**").permitAll().anyRequest().authenticated().and().formLogin();
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();http.headers().frameOptions().disable();
		http.authorizeRequests().
				antMatchers( "/", "/apis/**", "/jobs/**", "/bids/**", "/registration/**").permitAll().anyRequest().authenticated().and()
				.formLogin().permitAll().defaultSuccessUrl("/");
		http.csrf().disable();
		http.headers().frameOptions().disable();
//				loginPage("/login").permitAll().defaultSuccessUrl("/").usernameParameter("email");
	}

protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	auth.jdbcAuthentication().dataSource(ds)

	.usersByUsernameQuery("select user.email,user.password,user.enabled from user where user.email = ?")
    .authoritiesByUsernameQuery("select user.email, user.role from user where user.email=?");
	String encodedPassword = pw.encode("password");
    auth.inMemoryAuthentication()
    .withUser("user").password(encodedPassword).roles("USER");

}

}
