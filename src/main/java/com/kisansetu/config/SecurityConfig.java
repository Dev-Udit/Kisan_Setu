package com.kisansetu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/admin/login", "/register", "/farmer/signup", "/css/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")  //  Protects AdminController
                        .requestMatchers("/farmer/**").hasRole("FARMER") //  Protects FarmerController
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .failureUrl("/login?error=true") // Add this line
                        .successHandler((request, response, authentication) -> {

                            var authorities = authentication.getAuthorities();
                            for (var auth : authorities) {
                                if (auth.getAuthority().equals("ROLE_ADMIN")) {
                                    response.sendRedirect("/admin/dashboard");
                                    return;
                                }
                            }
                            response.sendRedirect("/farmer/dashboard");
                        })
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );
        return http.build();
    }
}