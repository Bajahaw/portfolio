package tech.radhi.portfolio;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Configuration
public class MainConfig {
    @Value("${token}")
    private String KEY;
    @Value("${url}")
    private String URL;

    OncePerRequestFilter tokenFilter = new OncePerRequestFilter() {
        @Override
        protected void doFilterInternal(HttpServletRequest request,
                HttpServletResponse response, FilterChain filterChain
        ) throws ServletException, IOException {
            // simply checking equality with an - already given - token
            var token = request.getParameter("token");
            if (token != null && token.equals(KEY)){
                var auth = new PreAuthenticatedAuthenticationToken("user", null, List.of());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            filterChain.doFilter(request, response);
        }
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/index.html", "/content/add", "/content/put/**", "/content/delete/**")
                        .authenticated().anyRequest().permitAll()
                )
                .addFilterBefore(tokenFilter, BasicAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public OpenAPI keyEnabledApi() {
        SecurityScheme scheme = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.QUERY)
                .name("token");

        Components components = new Components();
        components.addSecuritySchemes("queryToken", scheme);

        SecurityRequirement requirement = new SecurityRequirement();
        requirement.addList("queryToken");

        var server = new Server().url(URL);

        return new OpenAPI()
                .components(components)
                .addSecurityItem(requirement)
                .servers(List.of(server));
    }
}
