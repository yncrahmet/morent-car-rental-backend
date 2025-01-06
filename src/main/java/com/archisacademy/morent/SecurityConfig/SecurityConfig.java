package com.archisacademy.morent.SecurityConfig;

import com.archisacademy.morent.jwt.JwtAuthenticationFilter;
import com.archisacademy.morent.jwt.JwtService;
import com.archisacademy.morent.jwt.TokenBlacklistService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter authorizationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final JwtService jwtService;
    private final TokenBlacklistService tokenBlacklistService;

    public static final String LOGIN = "/api/users/login";
    public static final String REGISTER = "/api/users/register";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers(LOGIN, REGISTER, "/api/payments/initiate").permitAll()
                        .requestMatchers("/api/admin/register","/api/admin/login").permitAll()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/user/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/api/users/logout")
                        .logoutRequestMatcher(new AntPathRequestMatcher("/api/users/logout", "POST"))
                        .logoutSuccessHandler((request, response, authentication) -> {
                            String token = request.getHeader("Authorization").replace("Bearer ", "");
                            if (jwtService.isTokenExpired(token) || !jwtService.isTokenValid(token)) {
                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                response.getWriter().write("{\"message\": \"Invalid token.\"}");
                            } else {
                                tokenBlacklistService.addTokenToBlacklist(token);
                                response.setStatus(HttpServletResponse.SC_OK);
                                response.getWriter().write("{\"message\": \"User logged out successfully\"}");
                            }
                        })
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }

}
