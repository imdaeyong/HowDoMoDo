//package com.ssafy.howdomodo.config.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.context.SecurityContextPersistenceFilter;
//import org.springframework.security.web.csrf.CsrfFilter;
//import org.springframework.web.filter.CharacterEncodingFilter;
//
//import com.ssafy.howdomodo.config.filter.CorsFilter;
//import com.ssafy.howdomodo.config.filter.JwtAuthenticationFilter;
//import com.ssafy.howdomodo.config.jwt.JwtTokenProvider;
//
//import lombok.RequiredArgsConstructor;
//
//@Configuration
//@RequiredArgsConstructor
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//	private final JwtTokenProvider jwtTokenProvider;
//	
//
//	// 암호화에 필요한 PasswordEncoder 를 Bean 등록합니다.
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	}
//
//	// authenticationManager를 Bean 등록합니다.
//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
//
//    
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		CharacterEncodingFilter filter = new CharacterEncodingFilter();
//		filter.setEncoding("UTF-8");
//		filter.setForceEncoding(true);
//		http.httpBasic().disable() // rest api 만을 고려하여 기본 설정은 해제하겠습니다.
//				.cors().and().csrf().disable() // csrf 보안 토큰 disable처리.
//				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반 인증이므로 세션 역시 사용하지
//																							// 않습니다.
//				.and()
//					.authorizeRequests() // 요청에 대한 사용권한 체크
//						.antMatchers("/admin/**").hasRole("ADMIN")
//						.antMatchers("/users/logout").hasRole("USER")
////               		.antMatchers("/feeds/**").hasRole("USER")
//						.anyRequest().permitAll() // 그외 나머지 요청은 누구나 접근 가능
//				.and()
//					.addFilterBefore(filter, CsrfFilter.class)
//					.addFilterBefore(new CorsFilter(), SecurityContextPersistenceFilter.class)
//					.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
//				
//	}
//
//	@Override // ignore check swagger resource
//	public void configure(WebSecurity web) {
//		web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**",
//				"/swagger/**");
//	}
//
//}