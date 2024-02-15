package com.API_REST.controllers;

import com.API_REST.persistence.DTO.CommentDTO;
import com.API_REST.security.auth.model.CommentRequest;
import com.API_REST.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * Clase controladora para manejar operaciones relacionadas con comentarios.
 */
@RestController
@RequestMapping("/api/v1/comments")
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * Endpoint para crear un comentario para una publicación específica.
     *
     * @param publicationId El ID de la publicación en la que se va a comentar.
     * @param request       La solicitud que contiene el contenido del comentario.
     * @param userDetails   Los detalles del usuario autenticado.
     * @return ResponseEntity con el comentario creado y el estado HTTP 201 (Creado).
     */
    @PostMapping("/{publicationId}")
    public ResponseEntity<CommentDTO> createComment(@PathVariable Long publicationId,
                                                    @RequestBody CommentRequest request,
                                                    @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        CommentDTO createdComment = commentService.createComment(publicationId, request, username);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }
}

