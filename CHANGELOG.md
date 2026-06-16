# Changelog

Todos los cambios relevantes de este proyecto se documentan en este fichero.

El formato está basado en [Keep a Changelog](https://keepachangelog.com/es/1.0.0/),
y este proyecto sigue [Semantic Versioning](https://semver.org/lang/es/).

---

## [Unreleased]

### Añadido
- Implementación inicial de Spring Boot Admin Server 4.0.4 con Spring Boot 4.0.3 (#131)
- Configuración de seguridad con cadena única que combina autenticación de formulario para el dashboard y HTTP Basic para el registro de clientes, con credenciales propias independientes del JWT de la API y de las de Actuator (#131)
- Perfiles `dev` y `prod` con Fail-Fast en producción para `ADMIN_USER`, `ADMIN_PASSWORD`, `ACTUATOR_USER` y `ACTUATOR_PASSWORD` (#131)
- Dependencia `spring-boot-starter-web` requerida para la integración de Spring Security con Spring Boot Admin Server (#131)
- Dockerfile multi-stage ARM64 con JRE Alpine (#131)
- Imagen Docker ARM64 publicada en GitHub Container Registry (GHCR) (#131)
- Pipeline CI con GitHub Actions (#131)
- Pipeline CD con GitHub Actions para despliegue automático en Oracle Cloud al hacer merge a `main` y disparador manual (`workflow_dispatch`) (#131)

---

[Unreleased]: https://github.com/GestorRH-Multiplataforma/GestorRH-Admin/commits/main