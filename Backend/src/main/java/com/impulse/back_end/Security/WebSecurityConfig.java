package com.impulse.back_end.Security;

import com.impulse.back_end.Constant.Constants;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(cors -> cors.configurationSource(request -> {
                var config = new org.springframework.web.cors.CorsConfiguration();
                config.setAllowedOrigins(Arrays.asList("https://back-servicios-aws.d2k2pc2wp463ng.amplifyapp.com")); // Aquí debes poner tu dominio Amplify
                config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // Métodos permitidos
                config.setAllowedHeaders(Arrays.asList("*")); // Permitimos todos los encabezados
                config.setAllowCredentials(true); // Si necesitas cookies o credenciales
                return config;
            }))
            .authorizeHttpRequests((auth) ->
                auth
                    .requestMatchers(
                        antMatcher("/h2-console/**"),
                        antMatcher("/error"),
                        antMatcher(Constants.PublicRoutes.REGISTRO + "/**"),
                        antMatcher(Constants.PublicRoutes.LOGIN + "/**"),
                        antMatcher(Constants.PublicRoutes.PRODUCTO + "/**"),
                        antMatcher(Constants.PublicRoutes.CATEGORIA + "/**"),
                        antMatcher(Constants.AdminRoutes.USUARIO + "/**"),
                        antMatcher(Constants.AdminRoutes.PRODUCTO + "/**"),
                        antMatcher(Constants.AdminRoutes.CATEGORIA + "/**")
                    ).permitAll()
                    .anyRequest().authenticated()
            )
            .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
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
