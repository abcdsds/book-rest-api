package simplebookrestapi.infra.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import lombok.RequiredArgsConstructor;
import simplebookrestapi.infra.jwt.JwtAuthEntryPoint;
import simplebookrestapi.modules.service.AccountService;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	private final AccountService accountService;
	//private final AppProperties appProperties;
	private final JwtAuthEntryPoint unauthorizedHandler;
	private final PasswordEncoder passwordEncoder;
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(accountService).passwordEncoder(passwordEncoder);
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(accountService);
		authenticationProvider.setPasswordEncoder(passwordEncoder); // 패스워드를 암호활 경우 사용한다
		return authenticationProvider;
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
				.and()
			.anonymous()
				.and()
			.authorizeRequests()
				.mvcMatchers(HttpMethod.GET, "/api/**")
					.permitAll()
				.anyRequest()
					.permitAll()
					//.authenticated()
				.and()
			.exceptionHandling()
				.authenticationEntryPoint(unauthorizedHandler)
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		//http.addFilterBefore(authenticationJwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}
		
}
