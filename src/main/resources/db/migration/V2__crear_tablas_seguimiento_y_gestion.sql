
-- =====================================================
-- MIGRACION V2: TABLAS DE SEGUIMIENTO Y GESTION
-- =====================================================

-- 1. Tabla causal_rechazo
CREATE TABLE causal_rechazo (
                                codigo VARCHAR(30) PRIMARY KEY,
                                descripcion VARCHAR(255) NOT NULL,
                                subsanable BOOLEAN NOT NULL
);

-- 2. Tabla pago
CREATE TABLE pago (
                      id_pago BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                      id_caso BIGINT NOT NULL,
                      fecha_pago DATE NOT NULL,
                      dias_pagados INTEGER NOT NULL,
                      valor NUMERIC(14,2) NOT NULL,
                      soporte VARCHAR(500),
                      identificado BOOLEAN NOT NULL DEFAULT FALSE,

                      CONSTRAINT fk_pago_caso
                          FOREIGN KEY (id_caso)
                              REFERENCES caso(id_caso)
);

-- 3. Tabla transcripcion
CREATE TABLE transcripcion (
                               id_transcripcion BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                               id_caso BIGINT NOT NULL,
                               fecha DATE NOT NULL,
                               medio VARCHAR(30) NOT NULL,
                               numero_radicado VARCHAR(100),
                               evidencia VARCHAR(500),
                               dentro_de_plazo BOOLEAN NOT NULL,

                               CONSTRAINT fk_transcripcion_caso
                                   FOREIGN KEY (id_caso)
                                       REFERENCES caso(id_caso)
);

-- 4. Tabla alerta
CREATE TABLE alerta (
                        id_alerta BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        id_caso BIGINT,
                        tipo VARCHAR(50) NOT NULL,
                        generada_el TIMESTAMP NOT NULL,
                        prioridad INTEGER NOT NULL,
                        atendida BOOLEAN NOT NULL DEFAULT FALSE,
                        atendida_el TIMESTAMP,
                        escalada BOOLEAN NOT NULL DEFAULT FALSE,

                        CONSTRAINT fk_alerta_caso
                            FOREIGN KEY (id_caso)
                                REFERENCES caso(id_caso)
);

-- 5. Tabla rechazo
CREATE TABLE rechazo (
                         id_rechazo BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                         id_caso BIGINT NOT NULL,
                         codigo_causal VARCHAR(30),
                         fecha DATE NOT NULL,
                         subsanable BOOLEAN NOT NULL,
                         accion VARCHAR(255),
                         resultado VARCHAR(255),

                         CONSTRAINT fk_rechazo_caso
                             FOREIGN KEY (id_caso)
                                 REFERENCES caso(id_caso),

                         CONSTRAINT fk_rechazo_causal
                             FOREIGN KEY (codigo_causal)
                                 REFERENCES causal_rechazo(codigo)
);

-- 6. Tabla gestion_cobro
CREATE TABLE gestion_cobro (
                               id_gestion_cobro BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                               id_caso BIGINT NOT NULL,
                               fecha DATE NOT NULL,
                               tipo VARCHAR(50) NOT NULL,
                               medio VARCHAR(50),
                               fundamento TEXT,
                               respuesta TEXT,
                               fecha_compromiso DATE,

                               CONSTRAINT fk_gestion_cobro_caso
                                   FOREIGN KEY (id_caso)
                                       REFERENCES caso(id_caso)
);

-- 7. Tabla hito_prolongacion
CREATE TABLE hito_prolongacion (
                                   id_hito BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                   id_caso BIGINT NOT NULL,
                                   dias INTEGER NOT NULL,
                                   fecha_alcanzado DATE NOT NULL,
                                   fecha_solicitud_concepto DATE,
                                   fecha_concepto DATE,
                                   concepto VARCHAR(30),
                                   enviado_afp BOOLEAN NOT NULL DEFAULT FALSE,
                                   comunicado_colaborador BOOLEAN NOT NULL DEFAULT FALSE,

                                   CONSTRAINT fk_hito_prolongacion_caso
                                       FOREIGN KEY (id_caso)
                                           REFERENCES caso(id_caso)
);

-- 8. Tabla aviso_faltantes
CREATE TABLE aviso_faltantes (
                                 id_aviso BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                 id_caso BIGINT NOT NULL,
                                 numero_llamado INTEGER NOT NULL,
                                 fecha_envio DATE NOT NULL,
                                 fecha_limite DATE NOT NULL,
                                 documentos_faltantes TEXT NOT NULL,
                                 citado BOOLEAN NOT NULL DEFAULT FALSE,

                                 CONSTRAINT fk_aviso_faltantes_caso
                                     FOREIGN KEY (id_caso)
                                         REFERENCES caso(id_caso)
);

-- 9. Tabla asignacion_reemplazo
CREATE TABLE asignacion_reemplazo (
                                      id_asignacion BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                      id_colaborador_reemplazado INTEGER NOT NULL,
                                      id_colaborador_reemplazo INTEGER NOT NULL,
                                      desde DATE NOT NULL,
                                      hasta DATE,
                                      registrada_por VARCHAR(150),

                                      CONSTRAINT fk_asignacion_colaborador_reemplazado
                                          FOREIGN KEY (id_colaborador_reemplazado)
                                              REFERENCES colaborador(id_colaborador),

                                      CONSTRAINT fk_asignacion_colaborador_reemplazo
                                          FOREIGN KEY (id_colaborador_reemplazo)
                                              REFERENCES colaborador(id_colaborador)
);

-- 10. Tabla conciliacion
CREATE TABLE conciliacion (
                              id_conciliacion BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                              id_entidad INTEGER NOT NULL,
                              periodo VARCHAR(20) NOT NULL,
                              fecha DATE NOT NULL,
                              estado VARCHAR(40) NOT NULL,
                              total_pagado NUMERIC(14,2) NOT NULL,
                              diferencia NUMERIC(14,2) NOT NULL,

                              CONSTRAINT fk_conciliacion_entidad
                                  FOREIGN KEY (id_entidad)
                                      REFERENCES entidad(id_entidad)
);

-- 11. Tabla detalle_conciliacion
CREATE TABLE detalle_conciliacion (
                                      id_detalle BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                      id_conciliacion BIGINT NOT NULL,
                                      valor_contabilidad NUMERIC(14,2) NOT NULL,
                                      valor_registrado NUMERIC(14,2) NOT NULL,
                                      diferencia NUMERIC(14,2) NOT NULL,
                                      identificado BOOLEAN NOT NULL DEFAULT FALSE,

                                      CONSTRAINT fk_detalle_conciliacion
                                          FOREIGN KEY (id_conciliacion)
                                              REFERENCES conciliacion(id_conciliacion)
);

-- 12. Tabla requisito_documental
CREATE TABLE requisito_documental (
                                      id_requisito BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                      codigo_tipo_novedad VARCHAR(30) NOT NULL,
                                      tipo_documento VARCHAR(50) NOT NULL,
                                      obligatorio BOOLEAN NOT NULL,
                                      condicion TEXT,

                                      CONSTRAINT fk_requisito_tipo_novedad
                                          FOREIGN KEY (codigo_tipo_novedad)
                                              REFERENCES tipo_novedad(codigo)
);

-- 13. Tabla plazo_transcripcion
CREATE TABLE plazo_transcripcion (
                                     id_plazo BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                     id_entidad INTEGER NOT NULL,
                                     codigo_tipo_novedad VARCHAR(30) NOT NULL,
                                     valor INTEGER NOT NULL,
                                     unidad VARCHAR(30) NOT NULL,
                                     observacion VARCHAR(255),

                                     CONSTRAINT fk_plazo_entidad
                                         FOREIGN KEY (id_entidad)
                                             REFERENCES entidad(id_entidad),

                                     CONSTRAINT fk_plazo_tipo_novedad
                                         FOREIGN KEY (codigo_tipo_novedad)
                                             REFERENCES tipo_novedad(codigo)
);
