package com.example.StudentRecordManagementSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableMethodSecurity
@Configuration

public class SecurityConfig {
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //configuring the security filter chain bean
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests((authorize) ->
                authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                        .anyRequest().authenticated()
        ).httpBasic(Customizer.withDefaults());

        //letting allusers access api
      //  http.csrf().disable().authorizeHttpRequests((authorize) ->authorize.anyRequest().authenticated()
      //  ).httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails ogechi = User.builder()
                .username("ogechi")
                .password(passwordEncoder().encode("12345"))
                .roles("user")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("admin")
                .build();

        return new InMemoryUserDetailsManager(admin, ogechi);

    }
}
