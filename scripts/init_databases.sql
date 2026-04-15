-- Base de datos inicial para SIGHD
-- Ejecutar en MySQL como usuario con permisos de CREATE DATABASE

CREATE DATABASE IF NOT EXISTS sighd_auth CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS sighd_pacientes CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS sighd_triaje CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS sighd_cola CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS sighd_atencion CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS sighd_admin CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- =========================
-- auth-service
-- =========================
USE sighd_auth;

CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(80) NOT NULL,
    password VARCHAR(200) NOT NULL,
    rol VARCHAR(20) NOT NULL,
    paciente_id BIGINT NULL,
    activo BIT(1) NOT NULL DEFAULT b'1',
    PRIMARY KEY (id),
    UNIQUE KEY uk_usuarios_username (username)
) ENGINE=InnoDB;

-- Inserta aquí el usuario admin cuando tengas el hash BCrypt real.
-- Ejemplo:
-- INSERT INTO usuarios (username, password, rol, paciente_id, activo)
-- VALUES ('admin', '<BCryptHash>', 'ADMIN', NULL, b'1');

-- =========================
-- ms-pacientes
-- =========================
USE sighd_pacientes;

CREATE TABLE IF NOT EXISTS pacientes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombres VARCHAR(120) NOT NULL,
    apellidos VARCHAR(120) NOT NULL,
    documento VARCHAR(30) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    genero VARCHAR(20) NOT NULL,
    telefono VARCHAR(30) NULL,
    direccion VARCHAR(200) NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_pacientes_documento (documento)
) ENGINE=InnoDB;

-- =========================
-- ms-triaje
-- =========================
USE sighd_triaje;

CREATE TABLE IF NOT EXISTS signos_vitales (
    id BIGINT NOT NULL AUTO_INCREMENT,
    paciente_id BIGINT NOT NULL,
    frecuencia_cardiaca INT NULL,
    presion_arterial VARCHAR(255) NULL,
    temperatura DOUBLE NULL,
    saturacion INT NULL,
    fecha_hora DATETIME(6) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS sintomas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    paciente_id BIGINT NOT NULL,
    descripcion VARCHAR(500) NOT NULL,
    fecha_hora DATETIME(6) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS triajes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    paciente_id BIGINT NOT NULL,
    nivel_prioridad VARCHAR(4) NOT NULL,
    fecha_hora DATETIME(6) NOT NULL,
    activo BIT(1) NOT NULL DEFAULT b'1',
    PRIMARY KEY (id)
) ENGINE=InnoDB;

-- =========================
-- ms-cola
-- =========================
USE sighd_cola;

CREATE TABLE IF NOT EXISTS cola_atencion (
    id BIGINT NOT NULL AUTO_INCREMENT,
    paciente_id BIGINT NOT NULL,
    nivel_prioridad VARCHAR(4) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    fecha_ingreso DATETIME(6) NOT NULL,
    fecha_inicio_atencion DATETIME(6) NULL,
    fecha_fin DATETIME(6) NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

-- =========================
-- ms-atencion
-- =========================
USE sighd_atencion;

CREATE TABLE IF NOT EXISTS atenciones_medicas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    paciente_id BIGINT NOT NULL,
    medico_id BIGINT NOT NULL,
    diagnostico VARCHAR(1000) NOT NULL,
    tratamiento VARCHAR(1000) NOT NULL,
    requiere_hospitalizacion BIT(1) NOT NULL,
    fecha_atencion DATETIME(6) NOT NULL,
    finalizada BIT(1) NOT NULL DEFAULT b'0',
    cola_id BIGINT NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

-- =========================
-- ms-admin
-- =========================
USE sighd_admin;

CREATE TABLE IF NOT EXISTS reglas_priorizacion (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(120) NOT NULL,
    condicion VARCHAR(200) NOT NULL,
    nivel_asignado VARCHAR(10) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

-- La entidad Java usa "reglas_priorizacion"; aquí ya quedó alineada.


