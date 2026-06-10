# GestorRH - Admin

[![CI Pipeline](https://github.com/GestorRH-Multiplataforma/GestorRH-Admin/actions/workflows/ci.yml/badge.svg)](https://github.com/GestorRH-Multiplataforma/GestorRH-Admin/actions/workflows/ci.yml)
[![Version](https://img.shields.io/badge/version-v1.0.0-brightgreen)](https://github.com/GestorRH-Multiplataforma/GestorRH-Admin/releases/tag/v1.0.0)
[![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)](https://openjdk.org/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.14-6DB33F?logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)

Panel de administración y monitorización centralizada del ecosistema GestorRH, construido con Spring Boot Admin.

> Este repositorio forma parte del ecosistema **GestorRH Multiplataforma**. Para entender el contexto general del proyecto, consulta el [README de la organización](https://github.com/GestorRH-Multiplataforma).

---

## Tecnologías Utilizadas

- **Java 21** & **Spring Boot 3.5.14**
- **Spring Boot Admin 3.5.8** (panel de monitorización)
- **Spring Security** (autenticación del dashboard)
- **Docker** & **Docker Compose**
- **GitHub Actions** (CI)

---

## Variables de Entorno

El despliegue en producción requiere un archivo `.env` basado en `.env.example`.

| Variable | Descripción |
|---|---|
| `ADMIN_USER` | Usuario para acceder al dashboard de Admin |
| `ADMIN_PASSWORD` | Contraseña para acceder al dashboard de Admin |
| `ACTUATOR_USER` | Usuario de Actuator de la API (definido en GestorRH-API) |
| `ACTUATOR_PASSWORD` | Contraseña de Actuator de la API (definido en GestorRH-API) |

> **Nota:** El archivo `.env` nunca debe subirse al repositorio. Está incluido en `.gitignore`.

---

## Despliegue

Spring Boot Admin se despliega como un contenedor adicional en el `docker-compose.prod.yml` de GestorRH-API, junto a la API y PostgreSQL. El dashboard es accesible en:

`https://gestorrh.ddns.net/admin`

---

## Licencia

Este proyecto está bajo la Licencia Apache 2.0 - mira el archivo [LICENSE](LICENSE) para más detalles.