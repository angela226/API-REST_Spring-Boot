# API-REST_Spring-Boot
Challenge Backend 
=======
# Challenge Backend API

## Instrucciones
1. Clonar el repositorio.
2. Correr `docker-compose up --build`.
3. Acceder a `http://localhost:8080/swagger-ui.html`.

Endpoints disponibles:
- `POST /api/calculate`
- `GET /api/calculate/history`

Base de datos: PostgreSQL

## Pruebas
- Ejecutar `./mvnw test` para correr los tests unitarios.

## 📦 Cómo usar la imagen de Docker

Puedes usar esta aplicación directamente desde Docker Hub.

### 1. Descargar la imagen
```bash
docker pull angela226/backchallenge:latest

