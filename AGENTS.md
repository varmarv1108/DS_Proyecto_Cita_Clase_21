# Reglas del proyecto Cotiza

## Producto
Backend de cotizaciones para Roble & Pino, taller de muebles a la medida.
Flujo del MVP: solicitud → borrador con IA → aprobación de Patricia → envío.
La IA solo PROPONE. Ninguna cotización se envía sin aprobación humana.

## Stack
- Java 21, Spring Boot 4.1.1, Maven, MySQL.
- Configuración en application.properties (nunca application.yml).
- Sin Lombok.

## Arquitectura
Paquetes por módulo dentro de com.devsenior.cotiza:
cliente, catalogo, solicitud, cotizacion, ia, comun.
Cada módulo tiene su entidad, repositorio, servicio, controlador y carpeta dto.

## Reglas de código
- Inyección por constructor. Nunca @Autowired en atributos.
- Los controladores reciben y devuelven DTOs (record). Nunca entidades.
- Validar la entrada con @Valid y anotaciones de Jakarta Validation.
- Las reglas de negocio van en el servicio, no en el controlador.
- Nombres de clases, métodos y variables en español.
- Comentarios solo cuando expliquen una decisión.

## Lo que NO haces
- No escribes contraseñas ni llaves de API en el código.
- No modificas módulos que no se mencionen en la tarea.
- No agregas dependencias al pom.xml sin preguntar.

## Definición de Hecho
Antes de terminar, revisa: compila, respeta los paquetes, usa DTOs,
valida la entrada (400 si es inválida) y lista los archivos que creaste o cambiaste.