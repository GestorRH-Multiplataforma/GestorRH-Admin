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
 * Protege el dashboard de Spring Boot Admin con autenticación de formulario
 * para el navegador y HTTP Basic para el registro de clientes, usando
 * credenciales propias independientes del sistema JWT de la API
 * y de las credenciales de Actuator.
 * </p>
 *
 * @author Fco Javier García Cañero
 * @version 1.3
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
     * Cadena única de seguridad del panel de administración.
     * <p>
     * Combina autenticación de formulario para el navegador y HTTP Basic para el
     * registro de clientes en una sola cadena, de modo que el dashboard accede a
     * /instances con su cookie de sesión y los clientes lo hacen con Basic.
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
                .httpBasic(basic -> {})
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers(
                                contextPath + "/instances",
                                contextPath + "/instances/**"
                        )
                )
                .rememberMe(rememberMe -> rememberMe
                        .key("gestorrh-admin-remember-me")
                        .tokenValiditySeconds(86400)
                )
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin())
                );

        return http.build();
    }
}
