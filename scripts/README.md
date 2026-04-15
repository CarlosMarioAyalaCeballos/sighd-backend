# Scripts y arranque local SIGHD

Este directorio contiene:

- `init_databases.sql`: crea las bases de datos y tablas mínimas.
- `ARRANQUE_LOCAL.md`: checklist de arranque ordenado.

## Uso recomendado

1. Copia `.env.example` a `.env` y ajusta credenciales/secretos.
2. Ejecuta `scripts/init_databases.sql` en MySQL.
3. Levanta los microservicios en el orden indicado en `ARRANQUE_LOCAL.md`.

## Bases creadas

- `sighd_auth`
- `sighd_pacientes`
- `sighd_triaje`
- `sighd_cola`
- `sighd_atencion`
- `sighd_admin`

## Admin inicial

El usuario `admin` se crea automáticamente al arrancar `auth-service` usando:

- `BOOTSTRAP_ADMIN_USERNAME`
- `BOOTSTRAP_ADMIN_PASSWORD`
- `BOOTSTRAP_ADMIN_ROLE`

Defaults locales:

- usuario: `admin`
- contraseña: `Admin1234!`
- rol: `ADMIN`

