package com.API_REST.services;

import com.API_REST.persistence.DTO.CommentDTO;
import com.API_REST.persistence.model.Comment;
import com.API_REST.persistence.model.Publication;
import com.API_REST.persistence.model.User;
import com.API_REST.persistence.repository.CommentRepositoryI;
import com.API_REST.persistence.repository.PublicationRepositoryI;
import com.API_REST.persistence.repository.UserRepositoryI;
import com.API_REST.security.auth.model.CommentRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
/**
 * Implementación del servicio para operaciones relacionadas con comentarios.
 */
@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    private CommentRepositoryI commentRepository;

    @Autowired
    private PublicationRepositoryI publicationRepository;

    @Autowired
    private UserRepositoryI userRepository;


    /**
     * Método para crear un nuevo comentario en una publicación.
     *
     * @param publicationId El ID de la publicación en la que se va a comentar.
     * @param request       La solicitud de comentario.
     * @param username      El nombre de usuario del usuario que realiza el comentario.
     * @return DTO del comentario creado.
     * @throws IllegalArgumentException Si el usuario o la publicación no se encuentran.
     */
    @Override
    public CommentDTO createComment(Long publicationId, CommentRequest request, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + username));

        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new IllegalArgumentException("Publicación no encontrada: " + publicationId));

        Comment comment = new Comment();
        comment.setPublication(publication);
        comment.setUser(user);
        comment.setText(request.getText());
        comment.setCreationDate(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);

        CommentDTO commentDTO = new CommentDTO();
        BeanUtils.copyProperties(savedComment, commentDTO);
        commentDTO.setUsername(username);

        return commentDTO;
    }
}
