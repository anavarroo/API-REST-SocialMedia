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

### DTO

![image](https://github.com/anavarroo/API-REST-SocialMedia/assets/117681310/871ba922-33f5-40db-a5cd-f42106a44a9d)

### Servicios/Controladores

![image](https://github.com/anavarroo/API-REST-SocialMedia/assets/117681310/0012f5a3-4da2-4245-9a8e-ac948307aec6)


### Spring Security JWT

![image](https://github.com/anavarroo/API-REST-SocialMedia/assets/117681310/872ca207-4a9e-4a9e-ae34-cf768e08a93d)


## Requisitos EXTRAS
### Swagger
### ControllerAdive
### Comentarios e imagenes
### Front con Flutter





  
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


