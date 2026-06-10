# Changelog

Todos los cambios relevantes de este proyecto se documentan en este fichero.

El formato está basado en [Keep a Changelog](https://keepachangelog.com/es/1.0.0/),
y este proyecto sigue [Semantic Versioning](https://semver.org/lang/es/).

---

## [Unreleased]

### Añadido
- Pipeline CD con GitHub Actions para despliegue automático en Oracle Cloud al hacer merge a `main` (#131)
- Imagen Docker ARM64 publicada en GitHub Container Registry (GHCR) (#131)
- Implementación inicial de Spring Boot Admin Server 3.5.8 con Spring Boot 3.5.14 (#131)
- Configuración de seguridad con autenticación de formulario, credenciales propias independientes del sistema JWT de la API y de las credenciales de Actuator (#131)
- Perfiles `dev` y `prod` con Fail-Fast en producción para `ADMIN_USER`, `ADMIN_PASSWORD`, `ACTUATOR_USER` y `ACTUATOR_PASSWORD` (#131)
- Dockerfile multi-stage con JRE Alpine para imagen ligera (#131)
- Pipeline CI con GitHub Actions (#131)

---

[Unreleased]: https://github.com/GestorRH-Multiplataforma/GestorRH-Admin/commits/main