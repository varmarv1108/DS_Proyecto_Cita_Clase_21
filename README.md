# Cotiza

Backend de cotizaciones con IA para talleres de muebles a la medida.

**Estado:** 🚧 Sprint 0 · versión 0.1.0

## El problema
Roble & Pino tarda 1 o 2 días en responder cada cotización y pierde clientes.
Cotiza arma un borrador con IA usando los precios del taller; una persona lo revisa y lo envía el mismo día.

## Stack
Java 21 · Spring Boot 4.1.1 · Maven · MySQL · Gemini

## Cómo ejecutarlo
1. Crea un usuario de MySQL y pon sus datos en `src/main/resources/application.properties`.
2. Ejecuta `CotizaApplication`.
3. Abre `http://localhost:8080/api/estado`.

## Endpoints disponibles
| Método | Ruta | Qué hace |
|---|---|---|
| GET | `/api/estado` | Confirma que el servicio está activo |
| POST | `/api/clientes` | Registra un cliente |
| GET | `/api/clientes` | Lista los clientes |
| GET | `/api/clientes/{id}` | Busca un cliente |

## Roadmap
- [x] Sprint 0: proyecto, reglas y clientes
- [ ] Sprint 1: catálogo, solicitudes, borrador con IA, aprobación y deploy