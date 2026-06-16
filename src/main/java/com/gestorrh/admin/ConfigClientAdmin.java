package com.gestorrh.admin;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.web.client.HttpHeadersProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import java.util.Base64;

/**
 * Configuración del cliente HTTP de Spring Boot Admin.
 * <p>
 * Proporciona las cabeceras de autenticación necesarias para que Spring Boot Admin
 * pueda acceder a los endpoints de Actuator de las instancias registradas,
 * usando las credenciales almacenadas en los metadatos de cada instancia.
 * </p>
 *
 * @author Fco Javier García Cañero
 * @version 1.0
 */
@Configuration
public class ConfigClientAdmin {

    /**
     * Proveedor de cabeceras HTTP que inyecta las credenciales de Actuator
     * en cada petición que Spring Boot Admin realiza a las instancias registradas.
     * <p>
     * Lee las credenciales de los metadatos de la instancia (management.username
     * y management.password) y las inyecta como cabecera Authorization Basic.
     * </p>
     *
     * @return el proveedor de cabeceras configurado
     */
    @Bean
    public HttpHeadersProvider actuatorCredentialsHeadersProvider() {
        return (Instance instance) -> {
            HttpHeaders headers = new HttpHeaders();
            String username = instance.getRegistration().getMetadata().get("management.username");
            String password = instance.getRegistration().getMetadata().get("management.password");
            if (username != null && password != null) {
                String credentials = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
                headers.set(HttpHeaders.AUTHORIZATION, "Basic " + credentials);
            }
            return headers;
        };
    }
}
