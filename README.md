# API Twitter

API Twitter es una aplicación de redes sociales que proporciona una plataforma para que los usuarios publiquen y compartan sus pensamientos, imágenes y más. Esta API proporciona servicios para la autenticación de usuarios, publicación de mensajes, seguimiento de usuarios y más.

## Indice
1. [Tecnologias Utilizadas](#tecnologías-utilizadas)
2. [Requisitos minimos](#requisitos-minimos)
     - [Estructura](#)
     - [BBDD MySQL](#)
     - [Entidades]
     - [DTO]
     - [Servicios/Controladores]
     - [Spring Security JWT]
       
4. [Requisitos EXTRAS]
     - [Swagger]
     - [ControllerAdvice]
     - [Comentarios e imagenes]
   

## Tecnologías Utilizadas
- Java
- Spring Boot
- Spring Security
- Hibernate
- MySQL
- JSON Web Tokens (JWT)
- Lombok

## Requisitos minimos

### Estructura

### BBDD MySQL
![alt text](image.png)
![alt text](image-1.png)
![alt text](image-2.png)
![alt text](image-3.png)
### Entidades
### DTO
### Servicios/Controladores
### Spring Security JWT

## Requisitos EXTRAS
### Swagger
### ControllerAdive
### Comentarios e imagenes
### Front con Flutter



## Estructura del Proyecto
El proyecto está dividido en varios paquetes, cada uno con su responsabilidad específica:

- com.API_Twitter.controllers: Contiene los controladores REST para manejar las solicitudes HTTP.
- com.API_Twitter.persistence.model: Define las entidades de la base de datos utilizando JPA.
- com.API_Twitter.persistence.repository: Define los repositorios JPA para interactuar con la base de datos.
- com.API_Twitter.services: Contiene la lógica de negocio de la aplicación.
- com.API_Twitter.security: Configuración y servicios relacionados con la seguridad de la aplicación.
- com.API_Twitter.security.auth.model: Define los modelos de datos para la autenticación y autorización.
- com.API_Twitter.security.auth.services: Servicios relacionados con la autenticación y generación de tokens JWT.
- com.API_Twitter.security.config: Configuración de Spring Security y JWT.
- com.API_Twitter.security.jwt: Filtros para la autenticación basada en JWT.
- com.API_Twitter.security.auth.controllers: Controladores para el manejo de la autenticación y autorización.
- com.API_Twitter.persistence.DTO: Objetos de transferencia de datos utilizados para intercambiar información entre capas.

  
## Requisitos Previos
- Java JDK 8 o superior
- MySQL Server
- Maven

## Configuración
1. Clona el repositorio desde GitHub.
2. Configura la base de datos MySQL y actualiza las credenciales en el archivo application.properties.
3. Ejecuta el proyecto usando Maven o tu IDE preferido.

## Uso
1. Realiza una solicitud de registro (/auth/register) con un nombre de usuario, correo  electrónico y contraseña para crear una cuenta.
2. Inicia sesión (/auth/login) con tus credenciales para obtener un token JWT.
3. Usa el token JWT en las solicitudes posteriores para acceder a los recursos protegidos.
   
## Contribuir
Si quieres contribuir a este proyecto, por favor sigue estos pasos:

1. Haz un fork del proyecto.
2. Crea una nueva rama (git checkout -b feature/feature-name).
3. Haz tus cambios y realiza commits (git commit -am 'Add new feature').
4. Sube tus cambios al repositorio (git push origin feature/feature-name).
5. Crea un nuevo pull request.
   
## Autores
Alberto Navarro Vega, Emilio Orduña Peña


