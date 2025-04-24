package tech.radhi.portfolio;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig {

    @Bean
    public OpenAPI keyEnabledApi(){
        SecurityScheme scheme = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.QUERY)
                .name("token");

        Components components = new Components();
        components.addSecuritySchemes("queryToken", scheme);

        SecurityRequirement requirement = new SecurityRequirement();
        requirement.addList("queryToken");

        OpenAPI openAPI = new OpenAPI();
        openAPI.components(components).addSecurityItem(requirement);

        return openAPI;
    }
}
