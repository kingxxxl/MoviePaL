package com.example.moviepal.config;

import com.example.moviepal.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final MyUserDetailsService myUserDetailsService;
    private SessionRegistry sessionRegistry;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                 http.csrf().disable().cors().configurationSource(corsConfigurationSource()).and()
                .authorizeRequests()
                         .antMatchers("/user/register","/movie/lookby/name/**").permitAll()
                .antMatchers("movie/**").hasAnyAuthority()
                         .and()
                         .authorizeRequests()
                         .anyRequest()
                         .authenticated()
                         .and()
                         .logout()
                         .logoutUrl("/user/logout").logoutSuccessUrl("/user/logout-done")
                         .clearAuthentication(true)
                         .invalidateHttpSession(true)
                         .deleteCookies("JSESSIONID")
                         .and()
                         .httpBasic();
//                  .antMatchers(HttpMethod.POST,"/user").permitAll()

    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .cors().configurationSource(corsConfigurationSource()).
//                and()
//                .authorizeRequests()
//                .antMatchers("/api/v1/auth/register").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .logout().logoutUrl("/api/v1/auth/logout").invalidateHttpSession(true).deleteCookies("JSESSIONID")
//                .and()
//                .httpBasic();
//    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "OPTIONS"));
        configuration.addAllowedMethod(HttpMethod.TRACE);
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }



}
