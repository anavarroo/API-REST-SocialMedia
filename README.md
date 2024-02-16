# API Twitter

API Twitter es una aplicación de redes sociales que proporciona una plataforma para que los usuarios publiquen y compartan sus pensamientos, imágenes y más. Esta API proporciona servicios para la autenticación de usuarios, publicación de mensajes, seguimiento de usuarios y más.

## Indice
1. [Tecnologias Utilizadas](#tecnologías-utilizadas)
2. [Requisitos minimos](#requisitos-minimos)
     - [Estructura](#estructura)
     - [BBDD MySQL](#bbdd-mysql)
     - [Entidades](#entidades)
     - [DTO](#dto)
     - [Servicios/Controladores](#servicioscontroladores)
     - [Spring Security JWT](#spring-security-jwt)
       
4. [Requisitos EXTRAS](#requisitos-extras)
     - [Swagger](#swagger)
     - [ControllerAdvice](#controlleradive)
     - [Comentarios e imagenes](#comentarios-e-imagenes)
5. [Configuracion](#configuración)
6. [Requisitos Previos](#requisitos-previos)
   

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

![image](https://github.com/anavarroo/API-REST-SocialMedia/assets/117681310/631fd1f3-3350-49da-bbe5-254e2fd8ea8f)

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


### BBDD MySQL
![image](https://github.com/anavarroo/API-REST-SocialMedia/assets/117681310/694f2398-baf6-49d5-b496-215488099f55)

![image](https://github.com/anavarroo/API-REST-SocialMedia/assets/117681310/0fcbd16b-d1ca-4f85-a0f2-576b0e24ed7d)

![image](https://github.com/anavarroo/API-REST-SocialMedia/assets/117681310/71f9aafa-ada7-4346-b06e-a768d1a24ae6)

![image](https://github.com/anavarroo/API-REST-SocialMedia/assets/117681310/20246913-d351-4a21-879f-10b9099f7773)

![image](https://github.com/anavarroo/API-REST-SocialMedia/assets/117681310/ce05a3c4-435b-4f36-96ce-4a93839a35af)

**Breve Explicación:**
1. users:

- Esta tabla almacena la información de los usuarios de la plataforma.
- Campos importantes incluyen id (identificador único del usuario), email, password, username, role (rol del usuario, por ejemplo, "ADMIN" o "USER").
- La tabla tiene índices únicos en los campos email y username, lo que garantiza que no haya duplicados en esos campos.

2. publications:

- Almacena las publicaciones realizadas por los usuarios.
- Contiene detalles como la fecha de creación (creation_date), la fecha de edición (edit_date), el texto de la publicación (text), y una URL de imagen (image_Url) opcional.
- Tiene una relación con la tabla users a través del campo user_id, lo que indica qué usuario creó la publicación.

3. comments:

- Esta tabla guarda los comentarios realizados en las publicaciones.
- Contiene el texto del comentario (text), la fecha de creación (creation_date), y las claves foráneas user_id y publication_id que hacen referencia a los usuarios que realizaron el comentario y la publicación en la que se hizo, respectivamente.
  
4. follows:

- Registra las relaciones de seguimiento entre usuarios.
- Tiene dos campos, following_id y follower_id, que representan quién sigue a quién.
- Ambos campos son claves foráneas que apuntan a la tabla users, lo que indica que las dos entidades están relacionadas con los usuarios.
  
En resumen, estas tablas permiten el funcionamiento básico de una red social donde los usuarios pueden registrarse, hacer publicaciones, comentar en publicaciones de otros usuarios y seguir a otros usuarios para ver sus actualizaciones.

### Entidades

![image](https://github.com/anavarroo/API-REST-SocialMedia/assets/117681310/61eaf8f9-12ac-470f-ac9a-153bc6877b36)

**Breve Explicación:**

1, Comment:

- Representa los comentarios realizados por los usuarios en las publicaciones.
- Tiene campos para el identificador del comentario (commentId), el usuario que realizó el comentario (user), la publicación a la que se refiere el comentario (publication), el texto del comentario (text), y la fecha de creación (creationDate).
- Utiliza anotaciones de Lombok para generar constructores y métodos getter/setter automáticamente, así como anotaciones de Hibernate para el mapeo ORM.
  
2. Follow:

- Modela las relaciones de seguimiento entre usuarios.
- Contiene un identificador (id), un seguidor (follower), y un usuario seguido (following).
- También hace uso de anotaciones de Lombok y Hibernate para la generación de código y el mapeo ORM.
  
3. Publication:

- Representa las publicaciones realizadas por los usuarios.
- Contiene campos para el identificador de la publicación (id), el autor de la publicación (author), el texto de la publicación (text), la URL de la imagen asociada (imageUrl), y las fechas de creación y edición (creationDate y editDate, respectivamente).
- Incluye una relación de uno a muchos con los comentarios realizados en esa publicación (comments).
- Al igual que los otros modelos, hace uso de anotaciones para la generación de código y el mapeo ORM.
  
4. User:

- Representa los usuarios de la plataforma.
- Contiene campos para el identificador de usuario (id), nombre de usuario (username), correo electrónico (email), contraseña (password), descripción (description), fecha de creación (creationDate), y un rol (role).
- Tiene una relación de uno a muchos con las publicaciones realizadas por el usuario (publications).
- Implementa la interfaz UserDetails de Spring Security para gestionar la autenticación y autorización.
- Al igual que los otros modelos, utiliza anotaciones para la generación de código y el mapeo ORM.
  
En general, estos modelos de entidad forman la base de datos subyacente para la aplicación de API REST, permitiendo la gestión de usuarios, publicaciones, comentarios y relaciones de seguimiento entre usuarios.

### DTO

![image](https://github.com/anavarroo/API-REST-SocialMedia/assets/117681310/871ba922-33f5-40db-a5cd-f42106a44a9d)

**Breve Explicación:**

1. CommentDTO:

- Este DTO se utiliza para transferir datos de comentarios entre el cliente y el servidor.
- Contiene campos como commentId (identificador del comentario), username (nombre de usuario del que realizó el comentario), text (contenido del comentario) y creationDate (fecha de creación del comentario).
- Se centra en la información esencial relacionada con los comentarios y proporciona los datos necesarios para mostrar un comentario en la interfaz de usuario.

2. PublicationDTO:

- Utilizado para transferir datos de publicaciones entre el cliente y el servidor.
- Contiene campos como id (identificador de la publicación), author (DTO de usuario que creó la publicación), text (contenido de la publicación), imageUrl (URL de la imagen asociada), creationDate (fecha de -creación de la publicación) y editDate (fecha de edición de la publicación).
- También incluye una lista de CommentDTO para representar los comentarios asociados con la publicación.
- Proporciona la información necesaria para visualizar una publicación, incluidos los comentarios relacionados.

3. UserDTO:

- Se utiliza para transferir datos de usuarios entre el cliente y el servidor.
- Contiene campos como username (nombre de usuario), desc (descripción del usuario) y role (rol del usuario).
- Este DTO puede utilizarse para representar usuarios en diferentes contextos, como al mostrar detalles de usuario o al listar usuarios.
- Proporciona los datos básicos del usuario necesarios para las operaciones de la interfaz de usuario sin incluir detalles complejos.

En resumen, estos conjuntos de DTOs simplifican la transferencia de datos entre las capas de la aplicación al transmitir solo la información relevante y necesaria para operaciones específicas, como la visualización de publicaciones, comentarios y usuarios en una aplicación de red social.

### Servicios/Controladores

![image](https://github.com/anavarroo/API-REST-SocialMedia/assets/117681310/0012f5a3-4da2-4245-9a8e-ac948307aec6)

![image](https://github.com/anavarroo/API-REST-SocialMedia/assets/117681310/33a9cca9-d872-40c1-8644-cfbc96f21afa)

**Breve Explicación:**

1. CommentController:

- Este controlador maneja operaciones relacionadas con los comentarios en la API REST.
- Tiene un endpoint createComment para crear un comentario en una publicación específica.
- Utiliza el servicio CommentService para procesar las solicitudes de comentarios.
- Requiere autenticación del usuario y utiliza los detalles del usuario autenticado para obtener el nombre de usuario asociado al comentario.
- Retorna una respuesta con el comentario creado y el estado HTTP 201 (Creado) en caso de éxito.

2. FollowController:

- Este controlador maneja operaciones relacionadas con seguir y dejar de seguir a otros usuarios.
- Incluye endpoints para seguir a un usuario, dejar de seguir a un usuario, obtener los seguidores de un usuario y obtener los usuarios seguidos por un usuario.
- Utiliza los servicios FollowService y UserService para procesar las solicitudes relacionadas con los seguidores.
- Requiere autenticación del usuario y utiliza los detalles del usuario autenticado para realizar operaciones relacionadas con seguir y dejar de seguir usuarios.
- Retorna respuestas con mensajes indicando el resultado de las operaciones y los estados HTTP correspondientes.

3. PublicationController:

- Este controlador maneja operaciones relacionadas con las publicaciones en la API REST.
- Incluye endpoints para crear, eliminar, editar y obtener publicaciones.
- También proporciona endpoints para obtener publicaciones de un usuario específico y publicaciones de los usuarios seguidos por un usuario.
- Utiliza el servicio PublicationService para procesar las solicitudes relacionadas con las publicaciones.
- Requiere autenticación del usuario y utiliza los detalles del usuario autenticado para realizar operaciones relacionadas con las publicaciones.
- Retorna respuestas con las publicaciones solicitadas y los estados HTTP correspondientes.


4. UserController:

- Este controlador maneja operaciones relacionadas con usuarios en la API REST.
- Incluye endpoints para buscar un usuario por su nombre de usuario y para actualizar la descripción de un usuario.
- Utiliza el servicio UserService para procesar las solicitudes relacionadas con los usuarios.
- Requiere autenticación del usuario para actualizar la descripción y utiliza los detalles del usuario autenticado para realizar esta operación.
- Retorna respuestas con el usuario encontrado o un estado HTTP 404 si no se encuentra, así como con el estado HTTP correspondiente para la actualización de la descripción.



### Spring Security JWT

![image](https://github.com/anavarroo/API-REST-SocialMedia/assets/117681310/872ca207-4a9e-4a9e-ae34-cf768e08a93d)

**Breve Explicación:**

El paquete security contiene clases relacionadas con la autenticación y seguridad en la API REST:

- AuthController: Maneja inicio de sesión y registro de usuarios.
- AuthResponse: Representa la respuesta de autenticación con el token JWT.
- LoginRequest: Contiene datos de inicio de sesión como nombre de usuario y contraseña.
- PublicationRequest: Contiene datos para crear publicaciones.
- RegisterRequest: Contiene datos para registrar nuevos usuarios.
- AuthService: Procesa inicio de sesión y registro de usuarios.
- JWTService: Genera y valida tokens JWT para la autenticación.
- ApplicationConfig: Configura la autenticación y codificación de contraseñas.
- SecurityConfig: Configura reglas de autorización y manejo de autenticación con JWT.
- JWTAuthenticationFilter: Filtra y valida tokens JWT en las solicitudes entrantes.


## Requisitos EXTRAS

### Swagger

![image](https://github.com/anavarroo/API-REST-SocialMedia/assets/117681310/0437fcdc-9e72-4f5c-9171-72fad0dc4702)
![image](https://github.com/anavarroo/API-REST-SocialMedia/assets/117681310/febea121-72bb-401b-aaaf-ba7a7b7c9b11)
![image](https://github.com/anavarroo/API-REST-SocialMedia/assets/117681310/d93583d4-8bc8-4423-9c17-7c6da05a26ec)

Para acceder a el, levanta la api y entra en: http://localhost:8080/swagger-ui/index.html

### ControllerAdive
```java
package com.API_REST.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request: " + e.getMessage());
    }


}
```

**Breve Explicación:**

Esta clase anotada con @ControllerAdvice maneja excepciones a nivel global en la aplicación. Contiene dos métodos anotados con @ExceptionHandler, uno para manejar excepciones de tipo Exception y otro para manejar excepciones de tipo IllegalArgumentException. Ambos métodos devuelven una respuesta HTTP con el código de estado correspondiente y un mensaje que describe el error ocurrido. Las excepciones de tipo Exception se manejan como errores internos del servidor (HTTP 500), mientras que las excepciones de tipo IllegalArgumentException se manejan como solicitudes incorrectas del cliente (HTTP 400).

### Comentarios e imagenes

![image](https://github.com/anavarroo/API-REST-SocialMedia/assets/117681310/d92e5cd3-d598-4d49-87b1-42709e7b9adc)

### Front con Flutter


## Requisitos Previos
- Java JDK 8 o superior
- MySQL Server
- Maven

## Configuración
1. Clona el repositorio desde GitHub.
2. Configura la base de datos MySQL y actualiza las credenciales en el archivo application.properties.
3. Ejecuta el proyecto usando Maven o tu IDE preferido.
   
## Contribuir
Si quieres contribuir a este proyecto, por favor sigue estos pasos:

1. Haz un fork del proyecto.
2. Crea una nueva rama (git checkout -b feature/feature-name).
3. Haz tus cambios y realiza commits (git commit -am 'Add new feature').
4. Sube tus cambios al repositorio (git push origin feature/feature-name).
5. Crea un nuevo pull request.
   
## Autores
Alberto Navarro Vega, Emilio Orduña Peña


