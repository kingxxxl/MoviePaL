package com.example.moviepal.config;

import com.example.moviepal.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final MyUserDetailsService myUserDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
        //super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       // super.configure(http);
                 http.csrf().disable();
//                .authorizeRequests()
//                .antMatchers(HttpMethod.POST,"/user").permitAll()
//                .antMatchers(HttpMethod.GET,"/user")
//                .hasAuthority("ADMIN")
//                .anyRequest()
//                .authenticated().and().httpBasic();

    }

}
