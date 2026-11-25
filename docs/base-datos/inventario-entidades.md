
# Inventario de entidades - SIGI

## 1. Objetivo

Identificar las entidades del sistema SIGI que representan información que debe almacenarse en PostgreSQL.

Este inventario se construye a partir del diagrama de clases, el diagrama de casos de uso y el diagrama de estados de Incapacidades INTEK.

Las entidades aquí identificadas son candidatas iniciales. Su estructura definitiva se establecerá durante el diseño del modelo entidad-relación.

## 2. Entidades identificadas

### 2.1 Personas y usuarios

| Entidad | Responsabilidad |
|---|---|
| Colaborador | Almacenar la información de los colaboradores. |
| Usuario | Almacenar los usuarios que acceden al sistema. |

### 2.2 Gestión de casos

| Entidad | Responsabilidad |
|---|---|
| Caso | Representar el registro principal de una incapacidad o licencia. |
| Incapacidad | Representar la información específica de una incapacidad. |
| Licencia | Representar la información específica de una licencia. |

### 2.3 Entidades y catálogos

| Entidad | Responsabilidad |
|---|---|
| Entidad | Registrar las entidades relacionadas con los casos. |
| TipoNovedad | Clasificar los tipos de novedades. |
| RequisitoDocumental | Definir los documentos requeridos. |
| CausalRechazo | Clasificar las causas de rechazo. |
| PlazoTranscripcion | Definir plazos de transcripción. |
| TipoDocumento | Clasificar los documentos. |

### 2.4 Documentación y seguimiento

| Entidad | Responsabilidad |
|---|---|
| Documento | Registrar información de los documentos recibidos. |
| CambioEstado | Conservar el historial de cambios de estado de los casos. |
| Transcripcion | Registrar información de las transcripciones. |
| Alerta | Registrar alertas del sistema. |
| AvisoFaltantes | Registrar avisos de documentación faltante. |
| HitoProlongacion | Registrar hitos relacionados con prolongaciones. |
| AsignacionReemplazo | Registrar asignaciones de reemplazo. |

### 2.5 Gestión económica

| Entidad | Responsabilidad |
|---|---|
| Pago | Registrar los pagos relacionados con los casos. |
| GestionCobro | Registrar las gestiones realizadas para recuperar pagos. |
| Conciliacion | Registrar los procesos de conciliación. |
| DetalleConciliacion | Registrar los detalles de cada conciliación. |
| Rechazo | Registrar información de los rechazos. |

### 2.6 Administración y reportes

| Entidad | Responsabilidad |
|---|---|
| RegistroAuditoria | Conservar registros de las acciones realizadas. |
| CargaHistorica | Registrar información de las cargas históricas. |
| Reporte | Representar la información de los reportes generados. |

### 2.7 Procesamiento mediante IA

| Entidad | Responsabilidad |
|---|---|
| ExtraccionIA | Registrar los resultados de extracción de información mediante IA. |

## 3. Estados del caso

Según el diagrama de estados, Caso maneja los siguientes valores:

- REGISTRADA
- PENDIENTE_DOCUMENTACION
- VERIFICADA
- TRANSCRITA
- RECHAZADA
- COBRADA
- PAGADA
- ARCHIVADA

COBRADA es un estado compuesto. Sus situaciones internas se relacionan con las gestiones de cobro y no representan valores adicionales de EstadoCaso.

## 4. Decisiones pendientes

Antes de crear las tablas debemos determinar:

1. Las claves primarias de cada entidad.
2. Las relaciones y cardinalidades.
3. Los atributos y tipos de datos.
4. Las claves foráneas.
5. La representación de la herencia entre Caso, Incapacidad y Licencia.
6. Las restricciones de integridad.
7. Las reglas de almacenamiento de documentos.
8. La representación de usuarios, roles y permisos.

## 5. Consideraciones del sistema

- La información extraída mediante IA debe ser validada por una persona.
- El diagnóstico y la historia clínica están excluidos del almacenamiento.
- Los cambios de estado deben conservar su historial.
- Los subestados de COBRADA se gestionan mediante las gestiones de cobro.
- Los plazos y requisitos documentales deben poder administrarse.

## 6. Estado del diseño

Inventario inicial elaborado. Pendiente de revisión y validación con el equipo.
## Clasificación de clases del UML

Las clases identificadas en el diagrama se clasifican según su responsabilidad dentro del sistema:

### 1. Entidades candidatas a persistencia
Usuario, Colaborador, Caso, Incapacidad, Licencia, TipoNovedad, RequisitoDocumental, Entidad, PlazoTranscripcion, CausalRechazo, Documento, HitoProlongacion, AsignacionReemplazo, AvisoFaltantes, ExtraccionIA, Transcripcion, Rechazo, Pago, GestionCobro, CambioEstado, Alerta, Conciliacion, DetalleConciliacion, Reporte, CargaHistorica y RegistroAuditoria.

### 2. Enumeraciones
EstadoCaso, CanalRecepcion, TipoEntidad, MedioTranscripcion, TipoAlerta, Rol, TipoGestionCobro, ResultadoConcepto, TipoReporte, UnidadPlazo, TipoDocumento, OrigenIncapacidad y TipoLicencia.

### 3. Servicios e interfaces
IServicioIA y ServicioIA.

### 4. Clases de control
ControlCasos, ControlSeguimiento, ControlConciliacion, ControlAlertas, ControlReportes y ControlAdministracion.

**Nota:** Las entidades candidatas no necesariamente se convertirán todas en tablas independientes. Su estructura definitiva dependerá del análisis de relaciones, cardinalidades y reglas del negocio.