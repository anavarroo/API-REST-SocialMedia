package com.API_REST.services;

import com.API_REST.persistence.DTO.CommentDTO;
import com.API_REST.persistence.DTO.PublicationDTO;
import com.API_REST.persistence.DTO.UserDTO;
import com.API_REST.persistence.model.Comment;
import com.API_REST.persistence.model.Follow;
import com.API_REST.persistence.model.Publication;
import com.API_REST.persistence.model.User;
import com.API_REST.persistence.repository.CommentRepositoryI;
import com.API_REST.persistence.repository.FollowRepositoryI;
import com.API_REST.persistence.repository.PublicationRepositoryI;
import com.API_REST.persistence.repository.UserRepositoryI;
import com.API_REST.security.auth.model.PublicationRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para operaciones relacionadas con publicaciones.
 */
@Service
public class PublicationServiceImp implements PublicationService {

    @Autowired
    private PublicationRepositoryI publicationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepositoryI userRepository;

    @Autowired
    private FollowRepositoryI followRepository;

    @Autowired
    private CommentRepositoryI commentRepository;


    /**
     * Método para crear una nueva publicación.
     *
     * @param request  La solicitud de publicación.
     * @param username El nombre de usuario del autor de la publicación.
     * @return DTO de la publicación creada.
     */
    @Override
    public PublicationDTO createPublication(PublicationRequest request, String username) {
        User user = userService.getUserByUsername(username);
        Publication publication = new Publication();
        publication.setAuthor(user);
        publication.setText(request.getText());
        publication.setImageUrl(request.getImageUrl()); // Aquí se establece la URL de la imagen
        publication.setCreationDate(LocalDateTime.now());

        Publication savedPublication = publicationRepository.save(publication);
        return convertToDTO(savedPublication);
    }

    /**
     * Método para obtener todas las publicaciones.
     *
     * @return Lista de DTO de publicaciones.
     */
    @Override
    public List<PublicationDTO> getAllPublications() {
        List<Publication> publications = publicationRepository.findAllByOrderByCreationDateDesc();
        return publications.stream()
                .map(this::convertToDTOWithComments)
                .collect(Collectors.toList());
    }

    /**
     * Método privado para convertir una publicación junto con sus comentarios a un DTO.
     *
     * @param publication La publicación a convertir.
     * @return DTO de la publicación con sus comentarios.
     */
    private PublicationDTO convertToDTOWithComments(Publication publication) {
        PublicationDTO publicationDTO = convertToDTO(publication);
        List<Comment> comments = commentRepository.findByPublication(publication);
        List<CommentDTO> commentDTOs = comments.stream()
                .map(this::convertCommentToDTO)
                .collect(Collectors.toList());
        publicationDTO.setComments(commentDTOs);
        return publicationDTO;
    }

    /**
     * Método privado para convertir una publicación a un DTO.
     *
     * @param publication La publicación a convertir.
     * @return DTO de la publicación.
     */
    private PublicationDTO convertToDTO(Publication publication) {
        PublicationDTO publicationDTO = new PublicationDTO();
        BeanUtils.copyProperties(publication, publicationDTO);

        UserDTO authorDTO = new UserDTO();
        BeanUtils.copyProperties(publication.getAuthor(), authorDTO);
        authorDTO.setDesc(publication.getAuthor().getDescription());

        publicationDTO.setAuthor(authorDTO);

        // Cargar los comentarios asociados a la publicación y convertirlos a DTO
        List<CommentDTO> commentDTOs = publication.getComments().stream()
                .map(this::convertCommentToDTO)
                .collect(Collectors.toList());
        publicationDTO.setComments(commentDTOs);

        return publicationDTO;
    }


    /**
     * Método privado para convertir un comentario a un DTO.
     *
     * @param comment El comentario a convertir.
     * @return DTO del comentario.
     */
    private CommentDTO convertCommentToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentId(comment.getCommentId());
        commentDTO.setText(comment.getText());
        commentDTO.setUsername(comment.getUser().getUsername()); // Obtener el nombre de usuario del comentario

        commentDTO.setCreationDate(comment.getCreationDate());
        // Puedes agregar más campos según sea necesario
        return commentDTO;
    }


    /**
     * Método para eliminar una publicación por su ID.
     *
     * @param publicationId El ID de la publicación a eliminar.
     */
    public void deletePublication(Long publicationId) {
        publicationRepository.deleteById(publicationId);
    }

    /**
     * Método para actualizar una publicación por su ID.
     *
     * @param publicationId El ID de la publicación a actualizar.
     * @param request       La solicitud de actualización.
     * @return DTO de la publicación actualizada.
     */
    public PublicationDTO updatePublication(Long publicationId, PublicationRequest request) {
        Optional<Publication> optionalPublication = publicationRepository.findById(publicationId);

        if (optionalPublication.isPresent()) {
            Publication existingPublication = optionalPublication.get();
            existingPublication.setText(request.getText());
            existingPublication.setEditDate(LocalDateTime.now());

            Publication updatedPublication = publicationRepository.save(existingPublication);
            return convertToDTO(updatedPublication);
        } else {
            // Manejar el caso en el que la publicación no existe
            throw new IllegalArgumentException("Publicacion no encontrada " + publicationId);
        }
    }


    /**
     * Método para obtener todas las publicaciones de un usuario por su nombre de usuario.
     *
     * @param username El nombre de usuario del autor de las publicaciones.
     * @return Lista de DTO de publicaciones del usuario.
     */
    public List<PublicationDTO> getAllPublicationsByUser(String username) {
        User user = userService.getUserByUsername(username);
        List<Publication> publications = publicationRepository.findByAuthor(user);
        return publications.stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    /**
     * Método para obtener todas las publicaciones de los usuarios seguidos por un usuario.
     *
     * @param followerUsername El nombre de usuario del seguidor.
     * @return Lista de DTO de publicaciones de los usuarios seguidos.
     */
    @Override
    public List<PublicationDTO> getPublicationsFromFollowedUsers(String followerUsername) {
        User follower = userRepository.findByUsername(followerUsername)
                .orElseThrow(() -> new IllegalArgumentException("Usuario seguidor no encontrado: " + followerUsername));

        List<User> followedUsers = getFollowedUsers(follower);

        if (!followedUsers.isEmpty()) {
            List<Publication> publications = publicationRepository.findByAuthorInOrderByCreationDateDesc(followedUsers);
            return publications.stream()
                    .map(this::convertToDTO)  // Utilizar el método existente para la conversión
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    private List<User> getFollowedUsers(User follower) {
        List<Follow> follows = followRepository.findByFollower(follower);
        return follows.stream()
                .map(Follow::getFollowing)
                .collect(Collectors.toList());
    }

}



