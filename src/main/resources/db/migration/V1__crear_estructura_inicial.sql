
-- =====================================================
-- MIGRACION V1: ESTRUCTURA INICIAL DE SIGI
-- =====================================================

-- 1. Tabla colaborador
CREATE TABLE colaborador (
                             id_colaborador INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                             nombre_completo VARCHAR(150) NOT NULL,
                             documento VARCHAR(30) NOT NULL,
                             cargo VARCHAR(100),
                             area VARCHAR(100),
                             correo VARCHAR(150),
                             activo BOOLEAN NOT NULL DEFAULT TRUE
);

-- 2. Tabla usuario
CREATE TABLE usuario (
                         id_usuario BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                         nombre VARCHAR(150) NOT NULL,
                         correo VARCHAR(150) NOT NULL UNIQUE,
                         rol VARCHAR(40) NOT NULL,
                         activo BOOLEAN NOT NULL DEFAULT TRUE
);

-- 3. Tabla tipo_novedad
CREATE TABLE tipo_novedad (
                              codigo VARCHAR(30) PRIMARY KEY,
                              nombre VARCHAR(100) NOT NULL,
                              requiere_epicrisis BOOLEAN NOT NULL DEFAULT FALSE,
                              dias_minimos_epicrisis INTEGER
);

-- 4. Tabla entidad
CREATE TABLE entidad (
                         id_entidad INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                         nombre VARCHAR(150) NOT NULL,
                         tipo VARCHAR(50) NOT NULL,
                         medio_radicacion VARCHAR(50) NOT NULL,
                         portal_url VARCHAR(255),
                         correo_notificacion VARCHAR(150),
                         activa BOOLEAN NOT NULL DEFAULT TRUE
);

-- 5. Tabla caso
CREATE TABLE caso (
                      id_caso BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                      consecutivo VARCHAR(30),
                      fecha_recepcion DATE NOT NULL,
                      fecha_registro DATE NOT NULL,
                      canal_recepcion VARCHAR(30) NOT NULL,
                      fecha_inicio DATE NOT NULL,
                      fecha_fin DATE NOT NULL,
                      dias INTEGER NOT NULL,
                      estado VARCHAR(40) NOT NULL,
                      fecha_limite_transcripcion DATE,
                      registro_tardio BOOLEAN NOT NULL,
                      valor_liquidado NUMERIC(14,2),
                      saldo NUMERIC(14,2),
                      codigo_tipo_novedad VARCHAR(30) NOT NULL,

                      CONSTRAINT fk_caso_tipo_novedad
                          FOREIGN KEY (codigo_tipo_novedad)
                              REFERENCES tipo_novedad(codigo)
);

-- 6. Tabla documento
CREATE TABLE documento (
                           id_documento BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                           id_caso BIGINT NOT NULL,
                           nombre_archivo VARCHAR(255) NOT NULL,
                           tipo VARCHAR(50) NOT NULL,
                           es_original BOOLEAN NOT NULL,
                           recibido BOOLEAN NOT NULL DEFAULT FALSE,
                           fecha_carga TIMESTAMP,
                           ruta VARCHAR(500),

                           CONSTRAINT fk_documento_caso
                               FOREIGN KEY (id_caso)
                                   REFERENCES caso(id_caso)
);

-- 7. Tabla incapacidad
CREATE TABLE incapacidad (
                             id_caso BIGINT PRIMARY KEY,
                             origen VARCHAR(30) NOT NULL,
                             es_prorroga BOOLEAN NOT NULL DEFAULT FALSE,

                             CONSTRAINT fk_incapacidad_caso
                                 FOREIGN KEY (id_caso)
                                     REFERENCES caso(id_caso)
);

-- 8. Tabla licencia
CREATE TABLE licencia (
                          id_caso BIGINT PRIMARY KEY,
                          tipo_licencia VARCHAR(30) NOT NULL,
                          fecha_nacimiento DATE,
                          semanas_gestacion INTEGER,

                          CONSTRAINT fk_licencia_caso
                              FOREIGN KEY (id_caso)
                                  REFERENCES caso(id_caso)
);
