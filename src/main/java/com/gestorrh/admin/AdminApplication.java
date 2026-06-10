package com.gestorrh.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de la aplicación GestorRH Admin.
 * <p>
 * Levanta el servidor de Spring Boot Admin para la monitorización
 * centralizada de los servicios del ecosistema GestorRH.
 * </p>
 *
 * @author Fco Javier García Cañero
 * @version 1.0
 */
@EnableAdminServer
@SpringBootApplication
public class AdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
}
