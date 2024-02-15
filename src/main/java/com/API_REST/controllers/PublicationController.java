package com.API_REST.controllers;

import com.API_REST.persistence.DTO.PublicationDTO;
import com.API_REST.security.auth.model.PublicationRequest;
import com.API_REST.services.PublicationService;
import com.google.api.gax.rpc.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para manejar operaciones relacionadas con publicaciones.
 */
@RestController
@RequestMapping("/api/v1/publications")
@CrossOrigin
public class PublicationController {

    @Autowired
    private PublicationService publicationService;


    /**
     * Controlador para crear una publicación.
     *
     * @param request       La solicitud de la publicación.
     * @param userDetails   Los detalles del usuario autenticado.
     * @return ResponseEntity con la publicación creada y el estado HTTP 201 (Creado).
     */
    @PostMapping
    @Operation(summary = "Crear publicacion")
    public ResponseEntity<PublicationDTO> createPublication(@RequestBody PublicationRequest request,
                                                            @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        PublicationDTO createdPublication = publicationService.createPublication(request, username);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdPublication);
    }


    /**
     * Controlador para eliminar una publicación por su ID.
     *
     * @param publicationId El ID de la publicación a eliminar.
     * @return ResponseEntity con el estado HTTP correspondiente.
     */
    @DeleteMapping("/{publicationId}")
    @Operation(summary = "Eliminar una publicacion por ID")
    public ResponseEntity<Void> deletePublication(@PathVariable Long publicationId) {
        try {
            publicationService.deletePublication(publicationId);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    /**
     * Controlador para editar una publicación por su ID.
     *
     * @param publicationId El ID de la publicación a editar.
     * @param request       La solicitud de edición de la publicación.
     * @return ResponseEntity con la publicación actualizada.
     */
    @PutMapping("/{publicationId}")
    @Operation(summary = "Editar una publicacion por ID")
    public ResponseEntity<PublicationDTO> updatePublication(@PathVariable Long publicationId,
                                                            @RequestBody PublicationRequest request) {
        PublicationDTO updatedPublication = publicationService.updatePublication(publicationId, request);
        return ResponseEntity.ok(updatedPublication);
    }


    /**
     * Controlador para obtener todas las publicaciones.
     *
     * @return ResponseEntity con la lista de todas las publicaciones.
     */
    @GetMapping
    @Operation(summary = "Obtener todoas las publicaciones")
    public ResponseEntity<List<PublicationDTO>> getAllPublications() {
        try {
            List<PublicationDTO> publications = publicationService.getAllPublications();
            return ResponseEntity.ok(publications);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    /**
     * Controlador para obtener todas las publicaciones de un usuario.
     *
     * @param username El nombre de usuario del usuario del que se desean obtener las publicaciones.
     * @return ResponseEntity con la lista de publicaciones del usuario.
     */
    @GetMapping("/public/user/{username}")
    @Operation(summary = "Obtener todas las publicaciones de un usuario")
    public ResponseEntity<List<PublicationDTO>> getAllPublicationsByUser(@PathVariable String username) {
        try {
            List<PublicationDTO> publications = publicationService.getAllPublicationsByUser(username);
            return ResponseEntity.ok(publications);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    /**
     * Controlador para obtener todas las publicaciones de los usuarios seguidos por un usuario.
     *
     * @param userDetails Los detalles del usuario autenticado.
     * @return ResponseEntity con la lista de publicaciones de los usuarios seguidos.
     */
    @GetMapping("/from-followed-users")
    @Operation(summary = "Obtener todas las publicaciones de los seguidos")
    public ResponseEntity<List<PublicationDTO>> getPublicationsFromFollowedUsers(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            String followerUsername = userDetails.getUsername();
            List<PublicationDTO> publications = publicationService.getPublicationsFromFollowedUsers(followerUsername);
            return ResponseEntity.ok(publications);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }





}
