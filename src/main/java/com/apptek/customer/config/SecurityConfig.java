package com.apptek.customer.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.apptek.customer.security.JWTAuthenticationFilter;
import com.apptek.customer.security.JWTAuthorizationFilter;
import com.apptek.customer.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private Environment env;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTUtil jwtUtil;

	private static final String[] PUBLIC_MATCHERS = { "/h2-console/**" };

	private static final String[] PUBLIC_MATCHERS_GET = {
			"/clientes/**" 
	};

	private static final String[] PUBLIC_MATCHERS_POST = { 
			"/clientes", 
			"/auth/forgot/**" 
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Buscando o profile ativo no ambiente
		if (Arrays.asList(env.getActiveProfiles()).contains("dev")) {
			// Comando para liberar o banco H2
			http.headers().frameOptions().disable();
		}
		
		// Proteção contra CSRF (Segurança)
		http.cors().and().csrf().disable();
		http.authorizeRequests().antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
				.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll().antMatchers(PUBLIC_MATCHERS).permitAll()
				.anyRequest().authenticated().and().httpBasic();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		
		// Aplicação Stateless e não cria sessão de usuário
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		// configuration.setAllowedOrigins(Arrays.asList("https://www.apptek.com.br",
		// "https://www.[domain].com.br"));

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	// http://helpdev.com.br/2019/01/03/java-configurando-cors-no-spring-boot-e-security/
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
