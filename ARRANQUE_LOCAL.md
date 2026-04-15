# Checklist final de arranque local - SIGHD

## 1. Preparar variables de entorno
- [ ] Copiar `.env.example` a `.env`
- [ ] Cambiar `MYSQL_USER` y `MYSQL_PASSWORD` por credenciales reales
- [ ] Cambiar `JWT_SECRET` por un secreto largo y único
- [ ] Cambiar `BOOTSTRAP_ADMIN_PASSWORD` por una contraseña segura

## 2. Crear las bases de datos
Ejecutar `scripts/init_databases.sql` en MySQL.

Bases esperadas:
- `sighd_auth`
- `sighd_pacientes`
- `sighd_triaje`
- `sighd_cola`
- `sighd_atencion`
- `sighd_admin`

## 3. Verificar el orden de arranque
Arrancar en este orden:
1. MySQL
2. `auth-service` (`8081`)
3. `ms-cola` (`8084`)
4. `ms-pacientes` (`8082`)
5. `ms-triaje` (`8083`)
6. `ms-atencion` (`8085`)
7. `ms-admin` (`8086`)
8. `api-gateway` (`8080`)

## 4. Validación funcional
- [ ] Confirmar que `auth-service` crea el admin inicial automáticamente
- [ ] Probar login con `admin / BOOTSTRAP_ADMIN_PASSWORD`
- [ ] Probar el gateway en `http://localhost:8080`
- [ ] Validar que los endpoints protegidos requieren `Authorization: Bearer <token>`

## 5. Si algo falla
- [ ] Revisar logs de `auth-service` primero
- [ ] Confirmar que el secreto JWT sea idéntico en todos los servicios
- [ ] Confirmar que las URLs de `MS_*_URL` apunten a los puertos correctos
- [ ] Confirmar credenciales MySQL

