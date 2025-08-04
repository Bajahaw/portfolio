package tech.radhi.portfolio.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, TokenFilter tokenFilter) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/index.html",
                                "/actuator",
                                "/actuator/health",
                                "/actuator/health/**",
                                "/content/save",
                                "/content/save-all",
                                "/content/project/**",
                                "/content/delete/**",
                                "/content/invalidate-cache",
                                "/api/ai/**"
                        )
                        .authenticated().anyRequest().permitAll()
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .exceptionHandling(auth -> auth
                        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/403"))
                )
                .addFilterBefore(tokenFilter, BasicAuthenticationFilter.class);

        return http.build();
    }
}
