package com.hcmute.alohcmute.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.hcmute.alohcmute.service.UserService;

import com.hcmute.alohcmute.service.JwtAuthFilter;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.authentication.AuthenticationProvider; 
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity; 
import org.springframework.security.core.userdetails.UserDetailsService; 

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter authFilter; 
  
    // User Creation 
    @Bean
    public UserDetailsService userDetailsService() { 
        return new UserService() ; 
    } 

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // return http.csrf().disable()
        //         .authorizeHttpRequests()
        //         .requestMatchers("/", "/css/**", "/images/**", "/register","/api/posts","/api/login").permitAll()
        //         .requestMatchers("/api/**").authenticated()
        //         .and()
        //         .formLogin().loginPage("/login")
        //         .successForwardUrl("/alluser")
        //         .permitAll()
        //         .and()
        //         .build();

        return http.csrf().disable() 
            .authorizeHttpRequests() 
            .requestMatchers("/api/forgot-password","/api/addNewUser", "/api/generateToken", "/css/**", "/images/**", "/register","/api/posts","/","/api/users").permitAll() 
            .requestMatchers("/user/**","/api/profile/**","/post/**","/api/**","/api/posts/**","/api/images","/user/profileuser","/user/profile/**").authenticated() 
            .requestMatchers("/admin/**","/api/profile/**","/post/**","/api/**","/api/posts/**","/api/images","/admin/users/profileuser","/admin/users/profiles/**").authenticated() 
            .and() 
            .sessionManagement() 
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
            .and() 
            .authenticationProvider(authenticationProvider()) 
            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/logout")
            .invalidateHttpSession(true)
            .deleteCookies("jwtToken", "username")
            .permitAll()
            .and()
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder(); 
    } 
    @Bean
    public AuthenticationProvider authenticationProvider() { 
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); 
        authenticationProvider.setUserDetailsService(userDetailsService()); 
        authenticationProvider.setPasswordEncoder(passwordEncoder()); 
        return authenticationProvider; 
    } 
  
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception { 
        return config.getAuthenticationManager(); 
    } 
    
}
