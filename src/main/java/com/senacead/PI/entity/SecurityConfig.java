package com.senacead.PI.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/venda/**").hasAnyRole("GERENTE","VENDEDOR")
                .requestMatchers("/produto/**").hasAnyRole("GERENTE","ATENDENTE")
                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> form
                .defaultSuccessUrl("/main"));
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails gerente = User.withDefaultPasswordEncoder()
                .username("gerente")
                .password("123")
                .roles("GERENTE")
                .build();
        UserDetails vendedor = User.withDefaultPasswordEncoder()
                .username("vendedor")
                .password("123")
                .roles("VENDEDOR")
                .build();
        UserDetails atendente = User.withDefaultPasswordEncoder()
                .username("atendente")
                .password("123")
                .roles("ATENDENTE")
                .build();

        return new InMemoryUserDetailsManager(gerente, vendedor, atendente);

    }
}
