package com.eliasshallouf.examples.library_management_system;

import com.eliasshallouf.examples.library_management_system.service.utils.UserAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppSecurity {
    @Autowired
    private UserAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                    auth
                        .requestMatchers("/api/borrow/**").authenticated()
                        .requestMatchers("/api/return/**").authenticated()
                        .anyRequest().permitAll()
                )
                .httpBasic(basic ->
                    basic
                        .authenticationEntryPoint(authenticationEntryPoint)
                )
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User
            .withUsername("admin")
            .password(passwordEncoder().encode("admin"))
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
