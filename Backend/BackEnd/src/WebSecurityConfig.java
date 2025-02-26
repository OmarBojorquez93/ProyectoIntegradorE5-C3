package com.impulse.back_end.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                antMatcher("/h2-console/**"),
                                antMatcher("/usuario/**"),
                                antMatcher("/error")
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .formLogin(withDefaults())
                .logout(logout -> logout
                        .logoutUrl("/logout")  // Define la URL de logout
                        .logoutSuccessHandler((request, response, authentication) -> {
                            new SecurityContextLogoutHandler().logout(request, response, authentication);
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.getWriter().write("Sesión cerrada correctamente");
                        })
                        .invalidateHttpSession(true) // Invalidar la sesión
                        .deleteCookies("JSESSIONID") // Eliminar cookies de sesión
                );

        return http.build();
    }
}
