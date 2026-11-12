# G-COD — Microservicio de Notificaciones

Microservicio para registrar notificaciones, enviarlas por correo y procesar recordatorios programados.

## Tecnologías

- Java 25
- Spring Boot 4.1.2
- Maven
- MySQL
- Flyway
- Mailpit para pruebas locales de correo
- Docker

## Puerto y documentación API

- Aplicación: `8082`
- Swagger UI: http://localhost:8082/swagger-ui/index.html
- Mailpit: http://localhost:8025

## Endpoints principales

| Método | Ruta | Función |
|---|---|---|
| `POST` | `/api/v1/notificaciones` | Crear una notificación |
| `GET` | `/api/v1/notificaciones` | Listar notificaciones |
| `GET` | `/api/v1/notificaciones/{id}` | Consultar una notificación |
| `GET` | `/api/v1/notificaciones/estado` | Consultar por estado |
| `POST` | `/api/v1/notificaciones/{id}/enviar` | Enviar manualmente |

Las notificaciones programadas pendientes se procesan automáticamente cada 60 segundos.

## Ejecución local con Docker

La aplicación necesita que MySQL y Mailpit estén ejecutándose y conectados a la red `gcod-local`.

Construir la imagen:

```powershell
docker build -t gcod-notificaciones-service:1.0 .