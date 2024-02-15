package com.API_REST.controllers;

import com.API_REST.persistence.DTO.UserDTO;
import com.API_REST.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para manejar operaciones relacionadas con usuarios.
 */
@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;



    /**
     * Controlador para buscar un usuario por su nombre de usuario.
     *
     * @param username El nombre de usuario del usuario a buscar.
     * @return ResponseEntity con el usuario encontrado o un estado HTTP 404 (No encontrado) si no se encuentra.
     */
    @GetMapping("/public/user/{username}")
    @Operation(summary = "Buscar usuario por username")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        UserDTO user = userService.searchUserByUsername(username);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Controlador para actualizar la descripción de un usuario.
     *
     * @param newDescription La nueva descripción del usuario.
     * @param userDetails    Los detalles del usuario autenticado.
     * @return ResponseEntity con el estado HTTP correspondiente.
     */
    @PutMapping("/update-description")
    @Operation(summary = "Actualizar descripcion")
    public ResponseEntity<Void> updateDescription(@RequestParam String newDescription,
                                                  @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        userService.updateDescription(username, newDescription);
        return ResponseEntity.noContent().build();
    }

}
