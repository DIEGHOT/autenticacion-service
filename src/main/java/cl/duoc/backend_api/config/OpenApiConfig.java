package cl.duoc.backend_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Autenticación") // <-- ¡Aquí cambias el título principal!
                        .version("1.0.0") // Puedes inventarle una versión
                        .description("Documentación oficial del microservicio encargado de gestionar usuarios y la seguridad del sistema.")); // Una pequeña descripción debajo del título
    }
}