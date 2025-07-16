package br.com.wbs.gestao_vagas.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info().title("Gestão de vagas")
                .description("API responsável pela gestão de vagas")
                .version("1"))
                .schemaRequirement("jwt_auth", createSecurityScheme());
    }

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme().scheme("bearer").name("jwt_auth").bearerFormat("JWT")
                .type(SecurityScheme.Type.HTTP).in(SecurityScheme.In.HEADER);
    }
}
