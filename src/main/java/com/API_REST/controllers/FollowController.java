package com.API_REST.controllers;

import com.API_REST.persistence.DTO.UserDTO;
import com.API_REST.persistence.model.User;
import com.API_REST.services.FollowService;
import com.API_REST.services.UserService;
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
 * Controlador para manejar operaciones relacionadas con seguir y dejar de seguir usuarios.
 */
@RestController
@RequestMapping("/api/v1/follow")
@CrossOrigin
public class FollowController {

    @Autowired
    private FollowService followService;

    @Autowired
    private UserService userService;


    /**
     * Controlador para seguir a un usuario.
     *
     * @param followingUsername Nombre de usuario del usuario que se va a seguir.
     * @param userDetails       Detalles del usuario autenticado.
     * @return ResponseEntity con un mensaje indicando el resultado de la operación.
     */
    @PostMapping("/follow")
    @Operation(summary = "Seguir a un usuario")
    public ResponseEntity<String> followUser(@RequestParam String followingUsername,
                                             @AuthenticationPrincipal UserDetails userDetails) {
        String followerUsername = userDetails.getUsername();

        // Verificar si el usuario a seguir existe
        User followingUser = userService.getUserByUsername(followingUsername);
        if (followingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario a seguir no encontrado.");
        }

        // Seguir al usuario
        try {
            followService.followUser(followerUsername, followingUsername);
            return ResponseEntity.ok("Ahora estás siguiendo a " + followingUsername);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    /**
     * Controlador para obtener los usuarios seguidos por un usuario.
     *
     * @param followerUsername Nombre de usuario del seguidor.
     * @return ResponseEntity con la lista de usuarios seguidos.
     */
    @GetMapping("/following")
    @Operation(summary = "Ver los seguidos de un usuario")
    public ResponseEntity<List<UserDTO>> getFollowing(@RequestParam String followerUsername) {
        List<UserDTO> followingList = followService.getFollowing(followerUsername);
        return ResponseEntity.ok(followingList);
    }


    /**
     * Controlador para obtener los seguidores de un usuario.
     *
     * @param followingUsername Nombre de usuario del usuario del que se desean obtener los seguidores.
     * @return ResponseEntity con la lista de seguidores.
     */
    @GetMapping("/followers")
    @Operation(summary = "Ver los seguidores de un usuario")
    public ResponseEntity<List<UserDTO>> getFollowers(@RequestParam String followingUsername) {
        List<UserDTO> followersList = followService.getFollowers(followingUsername);
        return ResponseEntity.ok(followersList);
    }



    /**
     * Controlador para dejar de seguir a un usuario.
     *
     * @param followingUsername Nombre de usuario del usuario que se dejará de seguir.
     * @param userDetails       Detalles del usuario autenticado.
     * @return ResponseEntity con un mensaje indicando el resultado de la operación.
     */    @DeleteMapping("/unfollow")
    @Operation(summary = "Dejar de seguir a un usuario")
    public ResponseEntity<String> unfollowUser(@RequestParam String followingUsername,
                                               @AuthenticationPrincipal UserDetails userDetails) {
        try {
            followService.unfollowUser(followingUsername, userDetails);
            return ResponseEntity.ok("Dejaste de seguir a " + followingUsername);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}



