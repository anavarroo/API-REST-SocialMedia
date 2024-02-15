#API Twitter

API Twitter es una aplicación de redes sociales que proporciona una plataforma para que los usuarios publiquen y compartan sus pensamientos, imágenes y más. Esta API proporciona servicios para la autenticación de usuarios, publicación de mensajes, seguimiento de usuarios y más.

Tecnologías Utilizadas
Java
Spring Boot
Spring Security
Hibernate
MySQL
JSON Web Tokens (JWT)
Lombok
Estructura del Proyecto
El proyecto está dividido en varios paquetes, cada uno con su responsabilidad específica:

com.API_Twitter.controllers: Contiene los controladores REST para manejar las solicitudes HTTP.
com.API_Twitter.persistence.model: Define las entidades de la base de datos utilizando JPA.
com.API_Twitter.persistence.repository: Define los repositorios JPA para interactuar con la base de datos.
com.API_Twitter.services: Contiene la lógica de negocio de la aplicación.
com.API_Twitter.security: Configuración y servicios relacionados con la seguridad de la aplicación.
com.API_Twitter.security.auth.model: Define los modelos de datos para la autenticación y autorización.
com.API_Twitter.security.auth.services: Servicios relacionados con la autenticación y generación de tokens JWT.
com.API_Twitter.security.config: Configuración de Spring Security y JWT.
com.API_Twitter.security.jwt: Filtros para la autenticación basada en JWT.
com.API_Twitter.security.auth.controllers: Controladores para el manejo de la autenticación y autorización.
com.API_Twitter.persistence.DTO: Objetos de transferencia de datos utilizados para intercambiar información entre capas.
Requisitos Previos
Java JDK 8 o superior
MySQL Server
Maven
Configuración
Clona el repositorio desde GitHub.
Configura la base de datos MySQL y actualiza las credenciales en el archivo application.properties.
Ejecuta el proyecto usando Maven o tu IDE preferido.
Uso
Realiza una solicitud de registro (/auth/register) con un nombre de usuario, correo electrónico y contraseña para crear una cuenta.
Inicia sesión (/auth/login) con tus credenciales para obtener un token JWT.
Usa el token JWT en las solicitudes posteriores para acceder a los recursos protegidos.
Contribuir
Si quieres contribuir a este proyecto, por favor sigue estos pasos:

Haz un fork del proyecto.
Crea una nueva rama (git checkout -b feature/feature-name).
Haz tus cambios y realiza commits (git commit -am 'Add new feature').
Sube tus cambios al repositorio (git push origin feature/feature-name).
Crea un nuevo pull request.
Autores
Nombre del Autor
Licencia
Este proyecto está bajo la Licencia MIT - consulta el archivo LICENSE.md para más detalles.
