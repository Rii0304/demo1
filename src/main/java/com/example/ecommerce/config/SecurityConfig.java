package com.example.ecommerce.config;

import com.example.ecommerce.handler.CustomAuthenticationFailureHandler;
import com.example.ecommerce.handler.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                                auth.requestMatchers("/Management/**")
                                        .hasRole("ADMIN")
                                        .requestMatchers("/login/**", "/register/**","/guest/**","/guest/products","/guest/view","/guest/add/{id}","/guest/remove/{id}","/guest/clear","/guest/update","/search-guest","/guest/products/{id}","/guest/searchPrice")
                                        .permitAll()
                                        .requestMatchers("/css/**", "/js/**", "/image/**").permitAll()
                                        .anyRequest()
                                        .authenticated()
                )
                .formLogin(formLogin -> formLogin.loginPage("/login")
                                .loginProcessingUrl("/perform_login")
                                .successHandler(authenticationSuccessHandler())
                                .failureHandler(authenticationFailureHandler())
                )
                .logout(logout -> logout.logoutUrl("/logout")
                                .logoutSuccessUrl("/guest")
                                .deleteCookies("JSESSIONID")
                )
        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public CustomAuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }
}
