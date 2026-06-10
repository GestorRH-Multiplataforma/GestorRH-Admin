package com.gestorrh.admin;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * Configuración de seguridad del panel de administración.
 * <p>
 * Protege el dashboard de Spring Boot Admin con autenticación de formulario,
 * usando credenciales propias independientes del sistema JWT de la API
 * y de las credenciales de Actuator.
 * </p>
 *
 * @author Fco Javier García Cañero
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
public class ConfigSeguridadAdmin {

    private final AdminServerProperties adminServerProperties;

    /**
     * Construye la configuración de seguridad con las propiedades del servidor Admin.
     *
     * @param adminServerProperties propiedades de configuración de Spring Boot Admin
     */
    public ConfigSeguridadAdmin(AdminServerProperties adminServerProperties) {
        this.adminServerProperties = adminServerProperties;
    }

    /**
     * Define la cadena de filtros de seguridad para el dashboard de Admin.
     * <p>
     * Requiere autenticación para acceder al dashboard, permite el acceso
     * público a los assets estáticos y al endpoint de login, y configura
     * CSRF con cookies para compatibilidad con el frontend de Spring Boot Admin.
     * </p>
     *
     * @param http el objeto HttpSecurity para configurar la seguridad web
     * @return la cadena de filtros configurada
     * @throws Exception si ocurre algún error durante la configuración
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        String contextPath = adminServerProperties.getContextPath();

        SavedRequestAwareAuthenticationSuccessHandler successHandler =
                new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(contextPath + "/");

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(contextPath + "/assets/**").permitAll()
                        .requestMatchers(contextPath + "/login").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage(contextPath + "/login")
                        .successHandler(successHandler)
                )
                .logout(logout -> logout
                        .logoutUrl(contextPath + "/logout")
                )
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers(
                                contextPath + "/instances",
                                contextPath + "/actuator/**"
                        )
                );

        return http.build();
    }
}
