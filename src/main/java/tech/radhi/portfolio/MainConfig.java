package tech.radhi.portfolio;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;

import java.util.List;
import java.util.Set;

@Configuration
@EnableCaching
public class MainConfig implements CachingConfigurer {

    @Value("${url}")
    String URL;

    @Bean
    public AuthenticationManager defaultAuthManager(){
        return _ -> {
            throw new AuthenticationServiceException("Authentication is disabled");
        };
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

    @Bean
    @Override
    public CacheManager cacheManager() {
        var cacheManager = new SimpleCacheManager();
        var map = new ConcurrentMapCache("default");
        cacheManager.setCaches(Set.of(map));
        return cacheManager;
    }
}
