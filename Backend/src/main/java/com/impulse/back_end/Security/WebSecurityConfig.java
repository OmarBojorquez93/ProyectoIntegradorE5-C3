package com.impulse.back_end.Security;

import com.impulse.back_end.Constant.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth) ->
                       auth
                               .requestMatchers(
                                       antMatcher("/h2-console/**"),
                                       antMatcher("/error"),
                                       antMatcher(Constants.PublicRoutes.REGISTRO + "/**"),
                                       antMatcher(Constants.PublicRoutes.LOGIN + "/**"),
                                       antMatcher(Constants.AdminRoutes.USUARIO + "/**")
                               ).permitAll()
                               .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .logout(withDefaults());

       return http.build();
    }
}
