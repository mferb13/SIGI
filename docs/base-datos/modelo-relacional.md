
# Modelo relacional - SIGI

## 1. Objetivo

Documentar el diseño de la base de datos relacional de SIGI, definiendo las tablas, atributos, tipos de datos, claves y relaciones necesarias para el funcionamiento del sistema.

## 2. Tabla: caso

### 2.1. Descripción

La tabla caso almacenará la información general de cada registro de incapacidad o licencia dentro del sistema SIGI.

### 2.2. Atributos identificados
### Tabla: caso

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_caso | BIGINT | PK, autogenerado |
| consecutivo | VARCHAR(30) | UNIQUE |
| fecha_recepcion | DATE | NOT NULL |
| fecha_registro | DATE | NOT NULL |
| canal_recepcion | VARCHAR(30) | NOT NULL |
| fecha_inicio | DATE | NOT NULL |
| fecha_fin | DATE | NOT NULL |
| dias | INTEGER | NOT NULL |
| estado | VARCHAR(40) | NOT NULL |
| fecha_limite_transcripcion | DATE | NULL |
| registro_tardio | BOOLEAN | NOT NULL |
| valor_liquidado | NUMERIC(14,2) | NULL |
| saldo | NUMERIC(14,2) | NULL |
| codigo_tipo_novedad | VARCHAR(30) | FK, NOT NULL |
### 2.3. Reglas y observaciones

- El estado representa la situación actual del caso.
- Los cambios de estado deberán conservarse en un historial.
- El consecutivo identifica el registro dentro del sistema.
- Las fechas permitirán controlar los tiempos de recepción, registro y transcripción.
- El valor liquidado deberá manejar precisión decimal.



### 2.4. Decisiones pendientes

- Validar si el consecutivo será único según las reglas del negocio.
- Definir qué atributos serán obligatorios.
- Establecer las longitudes definitivas de los campos VARCHAR.
- Definir la precisión y escala del campo NUMERIC.
- Determinar las relaciones con colaborador, entidad y tipo de novedad.
- Validar la estrategia de herencia entre Incapacidad y Licencia.
- Definir las restricciones de integridad.


### 2.5. Estado del diseño

Tabla identificada y atributos iniciales documentados. Pendiente de validación y definición de restricciones.



### 2.6. Propuesta de clave primaria

Se propone utilizar un identificador técnico independiente para la tabla caso.

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_caso | BIGINT | PRIMARY KEY, GENERATED ALWAYS AS IDENTITY |
| consecutivo | VARCHAR(30) | UNIQUE pendiente de validación |

**Justificación:**

- El identificador interno permitirá relacionar los casos con otras tablas.
- El consecutivo permitirá identificar el caso dentro de los procesos del negocio.
- La unicidad del consecutivo debe confirmarse con los responsables de los requisitos.

**Estado:** propuesta pendiente de validación.



## 3. Herencia de la entidad Caso

### 3.1. Descripción

Según el diagrama de clases, las clases Incapacidad y Licencia heredan de Caso.

Esto significa que ambas comparten los atributos generales definidos en Caso y poseen información específica de su propio tipo.

### 3.2. Propuesta de implementación en PostgreSQL

### 3.3. Tabla: incapacidad

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_caso | BIGINT | PK, FK hacia caso(id_caso) |
| origen | VARCHAR(30) | NOT NULL |
| es_prorroga | BOOLEAN | NOT NULL, DEFAULT FALSE |
### 3.3. Claves y relaciones propuestas

- caso tendrá como clave primaria id_caso.
- incapacidad utilizará id_caso como clave primaria y clave foránea hacia caso.
- licencia utilizará id_caso como clave primaria y clave foránea hacia caso.

### 3.4. Tabla: licencia

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_caso | BIGINT | PK, FK hacia caso(id_caso) |
| tipo_licencia | VARCHAR(30) | NOT NULL |
| fecha_nacimiento | DATE | NULL |
| semanas_gestacion | INTEGER | NULL |
### 3.5. Estado del diseño

Propuesta inicial basada en la herencia del diagrama de clases. Pendiente de validar las reglas que garantizan que cada caso corresponda al tipo adecuado.



## 4. Relaciones identificadas

### 4.1. Caso y HitoProlongacion

Un caso podrá tener cero o muchos hitos de prolongación.

Cada hito de prolongación estará asociado a un único caso.

Propuesta:
- hito_prolongacion tendrá una clave foránea id_caso.
- id_caso referenciará caso(id_caso).

La posible relación adicional con Colaborador queda pendiente de validación.

### 4.2. Colaborador y AsignacionReemplazo

La tabla asignacion_reemplazo tendrá dos claves foráneas hacia colaborador:

- id_colaborador_reemplazado: identifica al colaborador que será reemplazado.
- id_colaborador_reemplazo: identifica al colaborador que asumirá el reemplazo.

Ambas claves referenciarán colaborador(id_colaborador).

### 4.3. TipoNovedad y Caso

Un tipo de novedad puede estar asociado a cero o muchos casos.

Cada caso corresponde a un único tipo de novedad.

Propuesta:
- caso tendrá una clave foránea codigo_tipo_novedad.
- codigo_tipo_novedad referenciará tipo_novedad(codigo).

### 4.4. Colaborador y Caso

Se identifica una relación denominada reporta entre Colaborador y Caso.

La propuesta inicial es que un colaborador pueda reportar varios casos y que cada caso esté asociado a un colaborador.

La cardinalidad y la implementación definitiva quedan pendientes de validación con el diagrama UML.

## 5. Estado de las relaciones

Se identificaron relaciones entre las entidades principales y se plantearon sus posibles claves foráneas.

Las cardinalidades pendientes deberán validarse antes de implementar las restricciones en PostgreSQL.


## 6. Tabla: colaborador

### 6.1. Descripción

La tabla colaborador almacenará la información de las personas vinculadas a la organización que participan en los procesos de incapacidades y licencias.

### 6.2. Atributos identificados
### Tabla: colaborador

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_colaborador | INTEGER | PK, autogenerado |
| nombre_completo | VARCHAR(150) | NOT NULL |
| documento | VARCHAR(30) | UNIQUE, NOT NULL |
| cargo | VARCHAR(100) | NULL |
| area | VARCHAR(100) | NULL |
| correo | VARCHAR(150) | NULL |
| activo | BOOLEAN | NOT NULL, DEFAULT TRUE |
### 6.3. Propuesta de clave primaria

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_colaborador | INTEGER | PRIMARY KEY |

### 6.4. Reglas y observaciones

- Cada colaborador tendrá un identificador único.
- El documento permitirá identificar al colaborador.
- El correo almacenará su dirección de contacto.
- El campo activo permitirá identificar si el colaborador continúa activo en el sistema.

### 6.5. Decisiones pendientes

- Confirmar si el documento debe ser único.
- Confirmar si el correo debe ser único.
- Definir qué campos serán obligatorios.
- Validar las relaciones con Caso, Incapacidad y HitoProlongacion.

### 6.6. Estado del diseño

Atributos identificados a partir del diagrama de clases. Restricciones y relaciones pendientes de validación.



## 7. Tabla: usuario

### 7.1. Descripción

La tabla usuario almacenará la información de las personas que tienen acceso al sistema SIGI y permitirá controlar sus roles y estado de acceso.

### 7.2. Atributos identificados

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_usuario | BIGINT | PK, autogenerado |
| nombre | VARCHAR(150) | NOT NULL |
| correo | VARCHAR(150) | UNIQUE, NOT NULL |
| rol | VARCHAR(40) | NOT NULL |
| activo | BOOLEAN | NOT NULL, DEFAULT TRUE |

### 7.3. Propuesta de clave primaria

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_usuario | BIGINT | PRIMARY KEY, GENERATED ALWAYS AS IDENTITY |

### 7.4. Reglas y observaciones

- Cada usuario tendrá un identificador único.
- El correo permitirá identificar la cuenta del usuario.
- El rol determinará los permisos de acceso al sistema.
- El campo activo permitirá habilitar o deshabilitar una cuenta sin eliminarla.

### 7.5. Decisiones pendientes

- Confirmar si el correo será el identificador de inicio de sesión.
- Definir los roles y permisos definitivos.
- Determinar cómo se gestionarán las contraseñas y la autenticación.
- Validar la relación entre Usuario y las acciones realizadas en el sistema.

### 7.6. Estado del diseño

Atributos identificados a partir del diagrama de clases. Clave primaria propuesta. Autenticación, restricciones y permisos pendientes de validación.
## 8. Tabla: tipo_novedad

### 8.1. Descripción

La tabla tipo_novedad almacenará los tipos de novedades que pueden registrarse en SIGI, como incapacidades y licencias, junto con las reglas documentales asociadas.

### 8.2. Atributos identificados

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| codigo | VARCHAR(30) | PK |
| nombre | VARCHAR(100) | NOT NULL |
| requiere_epicrisis | BOOLEAN | NOT NULL, DEFAULT FALSE |
| dias_minimos_epicrisis | INTEGER | NULL |

### 8.3. Propuesta de clave primaria

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| codigo | VARCHAR(30) | PRIMARY KEY |

### 8.4. Reglas y observaciones

- Cada tipo de novedad tendrá un código único.
- El nombre permitirá identificar el tipo de novedad.
- El campo requiere_epicrisis indicará si se exige este documento.
- dias_minimos_epicrisis permitirá establecer el mínimo de días para exigir epicrisis, cuando corresponda.

### 8.5. Relaciones propuestas

- Un tipo de novedad podrá estar asociado a varios casos.
- Un tipo de novedad podrá tener varios requisitos documentales.
- Un tipo de novedad podrá tener configuraciones de plazo de transcripción.

### 8.6. Decisiones pendientes

- Confirmar los códigos y nombres definitivos de los tipos de novedad.
- Validar las reglas para exigir epicrisis.
- Confirmar las relaciones con Caso, RequisitoDocumental y PlazoTranscripcion.

### 8.7. Estado del diseño

Atributos identificados a partir del diagrama de clases. Clave primaria propuesta. Reglas y relaciones pendientes de validación.
## 9. Tabla: entidad

### 9.1. Descripción

La tabla entidad almacenará la información de las entidades externas relacionadas con los procesos de incapacidades y licencias gestionados por SIGI.

Permitirá identificar cada entidad, su tipo, los medios de radicación disponibles y sus datos de contacto.

### 9.2. Atributos identificados
| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_entidad | INTEGER | PK, autogenerado |
| nombre | VARCHAR(150) | NOT NULL |
| tipo | VARCHAR(50) | NOT NULL |
| medio_radicacion | VARCHAR(50) | NOT NULL |
| portal_url | VARCHAR(255) | NULL |
| correo_notificacion | VARCHAR(150) | NULL |
| activa | BOOLEAN | NOT NULL, DEFAULT TRUE |
### 9.3. Propuesta de clave primaria
| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_entidad | INTEGER | PRIMARY KEY, GENERATED ALWAYS AS IDENTITY |
### 9.4. Reglas y observaciones

- Cada entidad tendrá un identificador único.
- nombre permitirá identificar la entidad.
- tipo clasificará la entidad.
- medioRadicacion indicará el medio utilizado para realizar las radicaciones.
- portalUrl almacenará la dirección del portal web, cuando exista.
- correoNotificacion permitirá registrar el correo de contacto.
- activa permitirá identificar si la entidad se encuentra habilitada.

### 9.5. Decisiones pendientes

- Confirmar los valores permitidos para TipoEntidad.
- Confirmar los valores permitidos para MedioRadicacion.
- Determinar si el portal y el correo serán obligatorios.
- Definir si el nombre de la entidad debe ser único.
- Validar las relaciones y cardinalidades con Caso e Incapacidad.
- Determinar cómo se administrarán los plazos de transcripción.

### 9.6. Estado del diseño

Atributos y clave primaria propuestos a partir del diagrama de clases. Relaciones, restricciones y reglas de plazos pendientes de validación.



## 10. Tabla: documento
### 10.1. Descripción

La tabla documento almacenará los archivos y soportes asociados a los casos de incapacidades y licencias, permitiendo identificar su tipo, estado de recepción y ubicación.

### 10.2. Atributos identificados
| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_documento | BIGINT | PK, autogenerado |
| id_caso | BIGINT | FK, NOT NULL |
| nombre_archivo | VARCHAR(255) | NOT NULL |
| tipo | VARCHAR(50) | NOT NULL |
| es_original | BOOLEAN | NOT NULL |
| recibido | BOOLEAN | NOT NULL, DEFAULT FALSE |
| fecha_carga | TIMESTAMP | NULL |
| ruta | VARCHAR(500) | NULL |

### 10.3. Propuesta de clave primaria

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_documento | BIGINT | PRIMARY KEY, GENERATED ALWAYS AS IDENTITY |

### 10.4. Reglas y observaciones

- Cada documento tendrá un identificador único.
- id_caso permitirá asociar el documento con un caso.
- nombre_archivo almacenará el nombre del archivo.
- tipo utilizará los valores definidos en la enumeración TipoDocumento.
- es_original indicará si el documento corresponde al archivo original.
- recibido permitirá controlar si el documento fue recibido.
- fecha_carga registrará cuándo se incorporó el documento al sistema.
- ruta almacenará la ubicación del archivo. Podrá ser NULL cuando el tipo de documento sea EPICRISIS, según lo establecido en el modelo UML.

### 10.5. Relaciones propuestas

- id_caso referenciará caso(id_caso).
- Un caso podrá tener varios documentos.
- Cada documento estará asociado a un único caso.

### 10.6. Decisiones pendientes

- Confirmar si todos los documentos deben tener una ruta de almacenamiento.
- Definir cómo se gestionarán los documentos de tipo EPICRISIS.
- Validar si se permitirá cargar varias versiones del mismo documento.
- Confirmar si se necesita registrar el usuario que realizó la carga.

### 10.7. Estado del diseño
Atributos identificados a partir del diagrama de clases. Clave primaria y relación con Caso propuestas. Reglas de almacenamiento y versiones pendientes de validación.

## 11 Inventario resumido de entidades
### 11.1. Descripción

Este apartado consolida las entidades identificadas para el sistema SIGI. Su inclusión es preliminar y no implica que todas deban convertirse en tablas independientes.
### 11.2. Atributos identificados

| Módulo | Entidades |
|---|---|
| Personas y usuarios | Colaborador, Usuario |
| Gestión de casos | Caso, Incapacidad, Licencia |
| Catálogos | TipoNovedad, Entidad, TipoDocumento, CausalRechazo |
| Documentación | Documento, RequisitoDocumental |
| Seguimiento | CambioEstado, Transcripcion, Alerta, AvisoFaltantes |
| Prolongaciones y reemplazos | HitoProlongacion, AsignacionReemplazo |
| Gestión económica | Pago, GestionCobro, Conciliacion, DetalleConciliacion, Rechazo |
| Administración | RegistroAuditoria, CargaHistorica, Reporte, PlazoTranscripcion |
| Inteligencia artificial | ExtraccionIA |

## 11.1. Criterio de diseño

Las entidades identificadas serán evaluadas para determinar si requieren una tabla propia, si pueden representarse mediante catálogos o enumeraciones, o si corresponden a componentes de lógica de negocio.

La estructura definitiva se establecerá según los requisitos funcionales y los diagramas del sistema.

## 11.2. Estado

Inventario consolidado. Pendiente de validar las entidades y sus relaciones antes de implementar la base de datos.

## 12. Tabla: pago

### 12.1. Descripción

La tabla pago almacenará los pagos realizados por las entidades externas relacionados con los casos registrados en SIGI.

### 12.2. Atributos identificados

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_pago | BIGINT | PK, autogenerado |
| id_caso | BIGINT | FK, NOT NULL |
| fecha_pago | DATE | NOT NULL |
| dias_pagados | INTEGER | NOT NULL |
| valor | NUMERIC(14,2) | NOT NULL |
| soporte | VARCHAR(500) | NULL |
| identificado | BOOLEAN | NOT NULL, DEFAULT FALSE |

### 12.3. Propuesta de clave primaria

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_pago | BIGINT | PRIMARY KEY, GENERATED ALWAYS AS IDENTITY |

### 12.4. Reglas y observaciones

- Cada pago tendrá un identificador único.
- Un caso podrá tener varios pagos.
- El valor permitirá registrar el importe recibido.
- Los días pagados permitirán comparar los días reconocidos con los días de la incapacidad o licencia.
- El campo identificado permitirá controlar si el pago ya fue asociado correctamente.

### 12.5. Relación con Caso

Un caso podrá tener cero o muchos pagos.

Cada pago estará asociado a un único caso.

Propuesta:
- pago tendrá una clave foránea id_caso.
- id_caso referenciará caso(id_caso).

### 12.6. Decisiones pendientes

- Confirmar si un pago puede cubrir varios casos.
- Definir si se necesita relacionar el pago con una entidad específica.
- Determinar si la fecha de pago y el valor serán siempre obligatorios.
- Validar cómo se almacenarán los soportes de pago.

### 12.7. Estado del diseño

Atributos identificados a partir del diagrama de clases. Clave primaria y relación con Caso propuestas. Reglas de asociación de pagos pendientes de validación.
## 13. Tabla: transcripcion

### 13.1. Descripción

La tabla transcripcion almacenará los registros de transcripción realizados ante las entidades externas.

### 13.2. Atributos identificados

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_transcripcion | BIGINT | PK, autogenerado |
| id_caso | BIGINT | FK, NOT NULL |
| fecha | DATE | NOT NULL |
| medio | VARCHAR(30) | NOT NULL |
| numero_radicado | VARCHAR(100) | NULL |
| evidencia | VARCHAR(500) | NULL |
| dentro_de_plazo | BOOLEAN | NOT NULL |

### 13.3. Propuesta de clave primaria

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_transcripcion | BIGINT | PRIMARY KEY, GENERATED ALWAYS AS IDENTITY |

### 13.4. Reglas y observaciones

- Cada transcripción tendrá un identificador único.
- Un caso podrá tener varios registros de transcripción si el proceso lo requiere.
- El medio indicará si se realizó por portal, correo o presencialmente.
- El número de radicado permitirá identificar el trámite ante la entidad.
- La evidencia almacenará la ubicación del soporte de la transcripción.
- dentro_de_plazo permitirá identificar si el trámite se realizó dentro del plazo establecido.

### 13.5. Relación con Caso

Un caso podrá tener cero o muchas transcripciones.

Cada transcripción estará asociada a un único caso.

Propuesta:
- transcripcion tendrá una clave foránea id_caso.
- id_caso referenciará caso(id_caso).

### 13.6. Decisiones pendientes

- Confirmar si un caso puede tener varias transcripciones.
- Definir si la evidencia será obligatoria.
- Determinar si se necesita relacionar la transcripción con una entidad.
- Validar cómo se calculará el cumplimiento del plazo.

### 13.7. Estado del diseño

Atributos identificados a partir del diagrama de clases. Clave primaria y relación con Caso propuestas. Reglas de transcripción pendientes de validación.

## 14. Tabla: alerta

### 14.1. Descripción

La tabla alerta almacenará las notificaciones generadas por el sistema para advertir sobre vencimientos, documentos pendientes y seguimientos que requieren atención.

### 14.2. Atributos identificados

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_alerta | BIGINT | PK, autogenerado |
| id_caso | BIGINT | FK, NULL |
| tipo | VARCHAR(50) | NOT NULL |
| generada_el | TIMESTAMP | NOT NULL |
| prioridad | INTEGER | NOT NULL |
| atendida | BOOLEAN | NOT NULL, DEFAULT FALSE |
| atendida_el | TIMESTAMP | NULL |
| escalada | BOOLEAN | NOT NULL, DEFAULT FALSE |

### 14.3. Propuesta de clave primaria

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_alerta | BIGINT | PRIMARY KEY, GENERATED ALWAYS AS IDENTITY |

### 14.4. Reglas y observaciones

- Cada alerta tendrá un identificador único.
- El tipo permitirá clasificar la alerta según el evento que la genera.
- La prioridad permitirá establecer su nivel de atención.
- atendida indicará si la alerta ya fue gestionada.
- atendida_el registrará cuándo se atendió.
- escalada indicará si la alerta fue elevada a otro nivel de seguimiento.

### 14.5. Relación con Caso

Una alerta podrá estar asociada a un caso.

Propuesta:
- alerta tendrá una clave foránea id_caso.
- id_caso referenciará caso(id_caso).
- La relación podrá ser opcional mientras se valida si existen alertas generales que no pertenezcan a un caso específico.

### 14.6. Decisiones pendientes

- Confirmar si todas las alertas deben estar asociadas a un caso.
- Definir los niveles de prioridad.
- Determinar quién puede atender o escalar alertas.
- Validar si se requiere registrar el usuario responsable de la atención.

### 14.7. Estado del diseño

Atributos identificados a partir del diagrama de clases. Clave primaria propuesta y relación opcional con Caso pendiente de validación.

## 15. Tabla: rechazo

### 15.1. Descripción

La tabla rechazo almacenará los rechazos realizados sobre los casos, incluyendo su fecha, posibilidad de subsanación, acciones tomadas y resultado.

### 15.2. Atributos identificados

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_rechazo | BIGINT | PK, autogenerado |
| id_caso | BIGINT | FK, NOT NULL |
| codigo_causal | VARCHAR(30) | NULL |
| fecha | DATE | NOT NULL |
| subsanable | BOOLEAN | NOT NULL |
| accion | VARCHAR(255) | NULL |
| resultado | VARCHAR(255) | NULL |

### 15.3. Propuesta de clave primaria

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_rechazo | BIGINT | PRIMARY KEY, GENERATED ALWAYS AS IDENTITY |

### 15.4. Reglas y observaciones

- Cada rechazo tendrá un identificador único.
- Un caso podrá registrar varios rechazos durante su gestión.
- subsanable indicará si el motivo del rechazo puede corregirse.
- accion almacenará la gestión realizada frente al rechazo.
- resultado permitirá registrar el desenlace de la gestión.

### 15.5. Relaciones propuestas

- id_caso referenciará caso(id_caso).
- codigo_causal referenciará causal_rechazo(codigo), cuando se implemente dicha tabla.

### 15.6. Decisiones pendientes

- Definir la estructura definitiva de causal_rechazo.
- Confirmar si todos los rechazos deben tener una causal registrada.
- Determinar si fecha debe almacenar también la hora.
- Validar cómo se registrará el cierre del rechazo.

### 15.7. Estado del diseño

Atributos identificados a partir del diagrama de clases. Clave primaria y relaciones propuestas. Catálogo de causales y reglas de cierre pendientes de validación.

## 16. Tabla: gestion_cobro

### 16.1. Descripción

La tabla gestion_cobro almacenará las actividades realizadas para gestionar el cobro de incapacidades y licencias pendientes de reconocimiento o pago.

### 16.2. Atributos identificados

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_gestion_cobro | BIGINT | PK, autogenerado |
| id_caso | BIGINT | FK, NOT NULL |
| fecha | DATE | NOT NULL |
| tipo | VARCHAR(50) | NOT NULL |
| medio | VARCHAR(50) | NULL |
| fundamento | TEXT | NULL |
| respuesta | TEXT | NULL |
| fecha_compromiso | DATE | NULL |

### 16.3. Propuesta de clave primaria

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_gestion_cobro | BIGINT | PRIMARY KEY, GENERATED ALWAYS AS IDENTITY |

### 16.4. Reglas y observaciones

- Cada gestión de cobro tendrá un identificador único.
- Un caso podrá tener varias gestiones de cobro.
- El tipo permitirá clasificar la actividad realizada.
- El medio indicará cómo se realizó la gestión.
- El fundamento almacenará la justificación de la gestión.
- La respuesta conservará la contestación recibida.
- fecha_compromiso permitirá registrar fechas acordadas para el pago.

### 16.5. Relación con Caso

Un caso podrá tener cero o muchas gestiones de cobro.

Cada gestión estará asociada a un único caso.

Propuesta:
- gestion_cobro tendrá una clave foránea id_caso.
- id_caso referenciará caso(id_caso).

### 16.6. Decisiones pendientes

- Confirmar los tipos de gestión permitidos.
- Determinar si se requiere almacenar el usuario responsable.
- Validar si se necesita conservar documentos de soporte.
- Definir cómo se controlarán los compromisos de pago.

### 16.7. Estado del diseño

Atributos identificados a partir del diagrama de clases. Clave primaria y relación con Caso propuestas. Reglas de seguimiento pendientes de validación.

## 17. Tabla: hito_prolongacion

### 17.1. Descripción

La tabla hito_prolongacion almacenará los hitos alcanzados durante el seguimiento de incapacidades prolongadas y permitirá controlar la solicitud y recepción de conceptos.

### 17.2. Atributos identificados

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_hito | BIGINT | PK, autogenerado |
| id_caso | BIGINT | FK, NOT NULL |
| dias | INTEGER | NOT NULL |
| fecha_alcanzado | DATE | NOT NULL |
| fecha_solicitud_concepto | DATE | NULL |
| fecha_concepto | DATE | NULL |
| concepto | VARCHAR(30) | NULL |
| enviado_afp | BOOLEAN | NOT NULL, DEFAULT FALSE |
| comunicado_colaborador | BOOLEAN | NOT NULL, DEFAULT FALSE |

### 17.3. Propuesta de clave primaria

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_hito | BIGINT | PRIMARY KEY, GENERATED ALWAYS AS IDENTITY |

### 17.4. Reglas y observaciones

- Cada hito tendrá un identificador único.
- Un caso podrá registrar varios hitos de prolongación.
- dias indicará el número de días acumulados al alcanzar el hito.
- Las fechas permitirán controlar las solicitudes y recepción de conceptos.
- concepto registrará el resultado obtenido.
- enviado_afp indicará si la información fue enviada a la AFP.
- comunicado_colaborador indicará si el resultado fue comunicado al colaborador.

### 17.5. Relación con Caso

Un caso podrá tener cero o muchos hitos de prolongación.

Cada hito estará asociado a un único caso.

Propuesta:
- hito_prolongacion tendrá una clave foránea id_caso.
- id_caso referenciará caso(id_caso).

### 17.6. Decisiones pendientes

- Confirmar qué tipos de casos pueden generar hitos.
- Definir los valores permitidos para concepto.
- Validar si todos los hitos requieren solicitud de concepto.
- Determinar si se necesita registrar el responsable del seguimiento.

### 17.7. Estado del diseño

Atributos identificados a partir del diagrama de clases. Clave primaria y relación con Caso propuestas. Reglas de seguimiento pendientes de validación.

## 18. Tabla: aviso_faltantes

### 18.1. Descripción

La tabla aviso_faltantes almacenará los avisos enviados a los colaboradores para solicitar documentos pendientes de incapacidades o licencias.

### 18.2. Atributos identificados

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_aviso | BIGINT | PK, autogenerado |
| id_caso | BIGINT | FK, NOT NULL |
| numero_llamado | INTEGER | NOT NULL |
| fecha_envio | DATE | NOT NULL |
| fecha_limite | DATE | NOT NULL |
| documentos_faltantes | TEXT | NOT NULL |
| citado | BOOLEAN | NOT NULL, DEFAULT FALSE |

### 18.3. Propuesta de clave primaria

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_aviso | BIGINT | PRIMARY KEY, GENERATED ALWAYS AS IDENTITY |

### 18.4. Reglas y observaciones

- Cada aviso tendrá un identificador único.
- Un caso podrá tener varios avisos de documentos pendientes.
- numero_llamado permitirá identificar el número de requerimiento realizado.
- fecha_envio registrará cuándo se envió el aviso.
- fecha_limite indicará el plazo para entregar los documentos.
- documentos_faltantes describirá los soportes pendientes.
- citado indicará si el colaborador fue citado.

### 18.5. Relación con Caso

Un caso podrá tener cero o muchos avisos de faltantes.

Cada aviso estará asociado a un único caso.

Propuesta:
- aviso_faltantes tendrá una clave foránea id_caso.
- id_caso referenciará caso(id_caso).

### 18.6. Decisiones pendientes

- Confirmar si numero_llamado debe ser único por caso.
- Definir si los documentos faltantes se almacenarán como texto o mediante una relación con Documento.
- Validar si se necesita registrar el medio de envío.
- Determinar si se almacenará la fecha de respuesta del colaborador.

### 18.7. Estado del diseño

Atributos identificados a partir del diagrama de clases. Clave primaria y relación con Caso propuestas. Reglas de notificación pendientes de validación.

## 19. Tabla: asignacion_reemplazo

### 19.1. Descripción

La tabla asignacion_reemplazo almacenará las asignaciones realizadas para cubrir temporalmente las funciones de un colaborador.

### 19.2. Atributos identificados

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_asignacion | BIGINT | PK, autogenerado |
| id_colaborador_reemplazado | INTEGER | FK, NOT NULL |
| id_colaborador_reemplazo | INTEGER | FK, NOT NULL |
| desde | DATE | NOT NULL |
| hasta | DATE | NULL |
| registrada_por | VARCHAR(150) | NULL |

### 19.3. Propuesta de clave primaria

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_asignacion | BIGINT | PRIMARY KEY, GENERATED ALWAYS AS IDENTITY |

### 19.4. Reglas y observaciones

- Cada asignación tendrá un identificador único.
- Se registrará el colaborador que necesita reemplazo.
- Se registrará el colaborador que asumirá el reemplazo.
- desde indicará el inicio de la asignación.
- hasta indicará su fecha de finalización, cuando corresponda.
- registrada_por permitirá identificar quién realizó el registro. Está pendiente definir si se almacenará como texto o mediante una relación con Usuario.

### 19.5. Relaciones propuestas

- id_colaborador_reemplazado referenciará colaborador(id_colaborador).
- id_colaborador_reemplazo referenciará colaborador(id_colaborador).

### 19.6. Decisiones pendientes

- Confirmar si un colaborador puede tener varios reemplazos simultáneos.
- Definir si la fecha hasta puede quedar vacía mientras la asignación esté activa.
- Determinar si registrada_por debe relacionarse con usuario.
- Validar las reglas para evitar asignaciones contradictorias.

### 19.7. Estado del diseño

Atributos identificados a partir del diagrama de clases. Clave primaria y dos relaciones con Colaborador propuestas. Reglas de asignación pendientes de validación.

## 20. Tabla: conciliacion

### 20.1. Descripción

La tabla conciliacion almacenará los procesos de comparación entre los pagos reportados por las entidades externas y los valores registrados en SIGI.

### 20.2. Atributos identificados

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_conciliacion | BIGINT | PK, autogenerado |
| id_entidad | INTEGER | FK, NOT NULL |
| periodo | VARCHAR(20) | NOT NULL |
| fecha | DATE | NOT NULL |
| estado | VARCHAR(40) | NOT NULL |
| total_pagado | NUMERIC(14,2) | NOT NULL |
| diferencia | NUMERIC(14,2) | NOT NULL |

### 20.3. Propuesta de clave primaria

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_conciliacion | BIGINT | PRIMARY KEY, GENERATED ALWAYS AS IDENTITY |

### 20.4. Reglas y observaciones

- Cada conciliación tendrá un identificador único.
- La entidad permitirá identificar con quién se realiza la comparación.
- El periodo indicará el intervalo que se está conciliando.
- El estado permitirá controlar el avance del proceso.
- total_pagado almacenará el valor reportado.
- diferencia registrará la diferencia encontrada durante la comparación.

### 20.5. Relación con Entidad

Una entidad podrá tener cero o muchas conciliaciones.

Cada conciliación estará asociada a una única entidad.

Propuesta:
- conciliacion tendrá una clave foránea id_entidad.
- id_entidad referenciará entidad(id_entidad).

### 20.6. Decisiones pendientes

- Definir el formato del periodo.
- Confirmar los estados permitidos para la conciliación.
- Determinar cómo se calculará la diferencia.
- Validar si se requiere registrar el usuario responsable.
- Confirmar si una conciliación puede incluir pagos de varios casos.

### 20.7. Estado del diseño

Atributos identificados a partir del diagrama de clases. Clave primaria y relación con Entidad propuestas. Reglas de comparación pendientes de validación.

## 21. Tabla: detalle_conciliacion

### 21.1. Descripción

La tabla detalle_conciliacion almacenará los resultados individuales de la comparación realizada durante una conciliación.

### 21.2. Atributos identificados

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_detalle | BIGINT | PK, autogenerado |
| id_conciliacion | BIGINT | FK, NOT NULL |
| valor_contabilidad | NUMERIC(14,2) | NOT NULL |
| valor_registrado | NUMERIC(14,2) | NOT NULL |
| diferencia | NUMERIC(14,2) | NOT NULL |
| identificado | BOOLEAN | NOT NULL, DEFAULT FALSE |

### 21.3. Propuesta de clave primaria

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_detalle | BIGINT | PRIMARY KEY, GENERATED ALWAYS AS IDENTITY |

### 21.4. Reglas y observaciones

- Cada detalle tendrá un identificador único.
- Una conciliación podrá tener varios detalles.
- valor_contabilidad almacenará el valor reportado por contabilidad.
- valor_registrado almacenará el valor existente en SIGI.
- diferencia representará la variación entre ambos valores.
- identificado indicará si la diferencia ya fue explicada o reconocida.

### 21.5. Relación con Conciliacion

Una conciliación tendrá uno o varios detalles.

Cada detalle pertenecerá a una única conciliación.

Propuesta:
- detalle_conciliacion tendrá una clave foránea id_conciliacion.
- id_conciliacion referenciará conciliacion(id_conciliacion).

### 21.6. Decisiones pendientes

- Confirmar si cada detalle debe relacionarse también con un caso o pago.
- Definir cómo se calculará la diferencia.
- Determinar si se almacenará una descripción de las diferencias.
- Validar si todos los detalles deben estar identificados antes de cerrar una conciliación.

### 21.7. Estado del diseño

Atributos identificados a partir del diagrama de clases. Clave primaria y relación con Conciliacion propuestas. Reglas de identificación y trazabilidad pendientes de validación.

## 22. Tabla: causal_rechazo

### 22.1. Descripción

La tabla causal_rechazo almacenará los motivos por los cuales una entidad puede rechazar una incapacidad o licencia.

### 22.2. Atributos identificados

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| codigo | VARCHAR(30) | PK |
| descripcion | VARCHAR(255) | NOT NULL |
| subsanable | BOOLEAN | NOT NULL |

### 22.3. Propuesta de clave primaria

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| codigo | VARCHAR(30) | PRIMARY KEY |

### 22.4. Reglas y observaciones

- Cada causal tendrá un código único.
- La descripción permitirá explicar el motivo del rechazo.
- subsanable indicará si el motivo puede corregirse para continuar con el trámite.

### 22.5. Relación con Rechazo

Una causal podrá estar asociada a varios rechazos.

Cada rechazo podrá tener una causal.

Propuesta:
- rechazo tendrá una clave foránea codigo_causal.
- codigo_causal referenciará causal_rechazo(codigo).

### 22.6. Decisiones pendientes

- Definir el catálogo inicial de causales.
- Confirmar si la descripción debe ser única.
- Validar si las causales podrán modificarse o desactivarse.
- Confirmar si todos los rechazos deben tener una causal.

### 22.7. Estado del diseño

Atributos identificados a partir del diagrama de clases. Clave primaria y relación con Rechazo propuestas. Catálogo definitivo pendiente de validación.


## 23. Tabla: requisito_documental

### 23.1. Descripción

La tabla requisito_documental almacenará los requisitos documentales asociados a los tipos de novedad y permitirá establecer qué documentos son obligatorios y bajo qué condiciones.

### 23.2. Atributos identificados

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_requisito | BIGINT | PK, autogenerado |
| codigo_tipo_novedad | VARCHAR(30) | FK, NOT NULL |
| tipo_documento | VARCHAR(50) | NOT NULL |
| obligatorio | BOOLEAN | NOT NULL |
| condicion | TEXT | NULL |

### 23.3. Propuesta de clave primaria

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_requisito | BIGINT | PRIMARY KEY, GENERATED ALWAYS AS IDENTITY |


### 23.4. Reglas y observaciones

- Cada requisito tendrá un identificador único.
- codigo_tipo_novedad permitirá asociar el requisito con un tipo de novedad.
- tipo_documento identificará el documento solicitado y deberá utilizar los valores definidos en la enumeración TipoDocumento.
- obligatorio indicará si el documento es indispensable.
- condicion describirá las circunstancias especiales en las que se exige el documento.
- Un mismo tipo de documento podrá ser requerido por diferentes tipos de novedad.

### 23.5. Relación con TipoNovedad

Un tipo de novedad podrá tener varios requisitos documentales.

Cada requisito documental estará asociado a un único tipo de novedad.

Propuesta:
- requisito_documental tendrá una clave foránea codigo_tipo_novedad.
- codigo_tipo_novedad referenciará tipo_novedad(codigo).

### 23.6. Decisiones pendientes

- Confirmar los tipos de documentos permitidos.
- Validar si un mismo documento puede aparecer en varios tipos de novedad.
- Definir las condiciones especiales de obligatoriedad.
- Confirmar si los requisitos podrán modificarse o desactivarse.

### 23.7. Estado del diseño

Atributos identificados a partir del diagrama de clases. Clave primaria y relación con TipoNovedad propuestas. Reglas documentales pendientes de validación.


## 24. Tabla: plazo_transcripcion

### 24.1. Descripción

La tabla plazo_transcripcion almacenará los plazos establecidos para realizar la transcripción de incapacidades y licencias según la entidad y el tipo de novedad.

### 24.2. Atributos identificados

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_plazo | BIGINT | PK, autogenerado |
| id_entidad | INTEGER | FK, NOT NULL |
| codigo_tipo_novedad | VARCHAR(30) | FK, NOT NULL |
| valor | INTEGER | NOT NULL |
| unidad | VARCHAR(30) | NOT NULL |
| observacion | VARCHAR(255) | NULL |

### 24.3. Propuesta de clave primaria

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_plazo | BIGINT | PRIMARY KEY, GENERATED ALWAYS AS IDENTITY |

### 24.4. Reglas y observaciones

- Cada plazo tendrá un identificador único.
- El plazo estará asociado a una entidad y a un tipo de novedad.
- valor indicará la cantidad de tiempo establecida.
- unidad utilizará los valores definidos en la enumeración UnidadPlazo: DIAS_CALENDARIO, MESES o ANIOS.
- observacion permitirá registrar aclaraciones sobre el plazo.

### 24.5. Relaciones

- id_entidad referenciará entidad(id_entidad).
- codigo_tipo_novedad referenciará tipo_novedad(codigo).
- Una entidad podrá tener varios plazos de transcripción.
- Un tipo de novedad podrá tener diferentes plazos según la entidad.

### 24.6. Decisiones pendientes

- Confirmar si todas las entidades tienen plazos diferentes.
- Definir si los plazos se cuentan en días calendario o hábiles.
- Validar si se requiere registrar la vigencia de cada plazo.
- Confirmar si puede existir más de un plazo para la misma combinación de entidad y tipo de novedad.

### 24.7. Estado del diseño

Atributos identificados a partir del diagrama de clases. Clave primaria y relaciones propuestas. Reglas de cálculo y vigencia pendientes de validación.
## 25. Tabla: extraccion_ia

### 25.1. Descripción

La tabla extraccion_ia almacenará los resultados de los procesos de extracción de información realizados mediante inteligencia artificial sobre
los documentos de un caso.

### 25.2. Atributos identificados

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_extraccion | BIGINT | PK, autogenerado |
| id_caso | BIGINT | NULL, relación pendiente de validación |
| fecha | TIMESTAMP | NOT NULL |
| confianza | NUMERIC(5,2) | NOT NULL |
| campos_propuestos | INTEGER | NOT NULL |
| campos_corregidos | INTEGER | NOT NULL |
| documentos_incompletos | BOOLEAN | NOT NULL |
| aceptada | BOOLEAN | NOT NULL |

### 25.3. Propuesta de clave primaria

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_extraccion | BIGINT | PRIMARY KEY, GENERATED ALWAYS AS IDENTITY |

### 25.4. Reglas y observaciones

- Cada extracción tendrá un identificador único.
- id_caso permitirá relacionar la extracción con el caso correspondiente.
- confianza almacenará el nivel de confianza obtenido por la IA.
- campos_propuestos indicará la cantidad de campos identificados.
- campos_corregidos registrará cuántos campos necesitaron corrección.
- documentos_incompletos indicará si se detectaron documentos incompletos.
- aceptada indicará si el resultado de la extracción fue aceptado.

### 25.5. Relaciones propuestas

La relación de ExtraccionIA con Documento y Caso está pendiente de validación con el diagrama UML.

Se debe confirmar si cada extracción corresponde a un documento específico, a un caso completo o si se relaciona con ambas entidades.

No se establece una clave foránea definitiva hasta validar esta relación.

### 25.6. Decisiones pendientes

- Confirmar si se conservará el resultado completo de cada extracción.
- Definir el rango permitido para el porcentaje de confianza.
- Confirmar si se necesita relacionar cada extracción con un documento específico.
- Definir si una extracción puede repetirse sobre el mismo documento.

### 25.7. Estado del diseño

Atributos identificados a partir del diagrama de clases. Clave primaria propuesta. Relación con Documento y Caso pendiente de validación. Almacenamiento de resultados y reglas de validación pendientes.

## 26. Tabla: reporte

### 26.1. Descripción

La clase Reporte representa la información necesaria para generar informes del sistema SIGI. Está pendiente definir si los reportes se almacenarán permanentemente en la base de datos o si se generarán dinámicamente cuando el usuario los solicite.

### 26.2. Atributos identificados

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_reporte | BIGINT | PK, autogenerado |
| tipo | VARCHAR(50) | NOT NULL |
| periodo | VARCHAR(30) | NOT NULL |
| fecha_generacion | TIMESTAMP | NOT NULL |
| formato | VARCHAR(20) | NOT NULL |

### 26.3. Propuesta de clave primaria

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_reporte | BIGINT | PRIMARY KEY, GENERATED ALWAYS AS IDENTITY |

### 26.4. Reglas y observaciones

- Cada reporte tendrá un identificador único.
- tipo identificará la categoría del reporte generado.
- periodo indicará el intervalo de información consultado.
- fecha_generacion registrará cuándo se creó el reporte.
- formato indicará el tipo de archivo generado.

### 26.5. Decisiones pendientes

- Confirmar si los reportes generados deben conservarse en la base de datos.
- Definir los tipos de reporte permitidos.
- Definir los formatos de archivo permitidos.
- Confirmar si se almacenará la ruta del archivo generado.

### 26.6. Estado del diseño

Atributos identificados a partir del diagrama de clases. Clave primaria propuesta. Persistencia y almacenamiento de archivos pendientes de validación.

## 27. Tabla: carga_historica

### 27.1. Descripción

La tabla carga_historica almacenará el registro de las importaciones históricas realizadas en el sistema, permitiendo consultar los resultados de cada proceso de carga.

### 27.2. Atributos identificados

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_carga | BIGINT | PK, autogenerado |
| fecha | TIMESTAMP | NOT NULL |
| archivo | VARCHAR(255) | NOT NULL |
| filas_totales | INTEGER | NOT NULL |
| filas_aceptadas | INTEGER | NOT NULL |
| filas_con_error | INTEGER | NOT NULL |

### 27.3. Propuesta de clave primaria

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_carga | BIGINT | PRIMARY KEY, GENERATED ALWAYS AS IDENTITY |

### 27.4. Reglas y observaciones

### 27.4. Reglas y observaciones

- Cada carga histórica tendrá un identificador único.
- fecha registrará el momento en que se realizó la carga.
- archivo almacenará el nombre o referencia del archivo importado.
- filas_totales indicará la cantidad total de registros procesados.
- filas_aceptadas indicará cuántos registros fueron importados correctamente.
- filas_con_error indicará cuántos registros presentaron problemas.
- Se debe definir cómo se almacenará el detalle de los errores encontrados.
- Se debe validar si la carga histórica estará relacionada con el usuario que la realizó.

### 27.5. Decisiones pendientes

- Confirmar si se almacenará la ruta del archivo importado.
- Definir si se conservará el detalle de los errores.
- Confirmar si cada carga histórica debe relacionarse con un usuario.
- Definir si se permitirá repetir la importación de un mismo archivo.

### 27.6. Estado del diseño

Atributos identificados a partir del diagrama de clases. Clave primaria propuesta. Relaciones y reglas de importación pendientes de validación.

## 28. Tabla: registro_auditoria

### 28.1. Descripción

La tabla registro_auditoria almacenará información sobre las acciones realizadas dentro del sistema SIGI, permitiendo consultar eventos importantes y mantener un historial de actividad.

### 28.2. Atributos identificados

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_auditoria | BIGINT | PK, autogenerado |
| fecha | TIMESTAMP | NOT NULL |
| accion | VARCHAR(100) | NOT NULL |
| objeto | VARCHAR(150) | NOT NULL |

### 28.3. Propuesta de clave primaria

| Campo | Tipo PostgreSQL | Restricción |
|---|---|---|
| id_auditoria | BIGINT | PRIMARY KEY, GENERATED ALWAYS AS IDENTITY |

### 28.4. Reglas y observaciones

- Cada registro de auditoría tendrá un identificador único.
- fecha almacenará el momento en que ocurrió la acción.
- accion describirá la operación realizada.
- objeto identificará el elemento del sistema sobre el cual se realizó la acción.

### 28.5. Relaciones propuestas

Se debe validar si cada registro de auditoría estará relacionado con el usuario que realizó la acción.

También se debe determinar si objeto almacenará una referencia al registro afectado o solamente su identificación descriptiva.

### 28.6. Estado del diseño

Atributos identificados a partir del diagrama de clases. Clave primaria propuesta. Relaciones y nivel de detalle de la auditoría pendientes de validación.