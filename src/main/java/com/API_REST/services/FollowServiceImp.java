package com.API_REST.services;

import com.API_REST.persistence.DTO.UserDTO;
import com.API_REST.persistence.model.Follow;
import com.API_REST.persistence.model.User;
import com.API_REST.persistence.repository.FollowRepositoryI;
import com.API_REST.persistence.repository.UserRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para operaciones relacionadas con seguir y dejar de seguir usuarios.
 */
@Service
public class FollowServiceImp implements FollowService {


    @Autowired
    private UserRepositoryI userRepository;

    @Autowired
    private FollowRepositoryI followRepository;


    /**
     * Método para que un usuario siga a otro usuario.
     *
     * @param followerUsername   El nombre de usuario del seguidor.
     * @param followingUsername  El nombre de usuario del usuario a seguir.
     * @throws IllegalArgumentException Si no se encuentra el usuario seguidor o el usuario a seguir.
     */
    @Override
    public void followUser(String followerUsername, String followingUsername) {
        // Obtener los usuarios correspondientes
        User follower = userRepository.findByUsername(followerUsername)
                .orElseThrow(() -> new IllegalArgumentException("Usuario seguidor no encontrado: " + followerUsername));

        User following = userRepository.findByUsername(followingUsername)
                .orElseThrow(() -> new IllegalArgumentException("Usuario a seguir no encontrado: " + followingUsername));

        // Verificar si ya están siguiéndose

        // Crear y guardar la relación de seguimiento
        Follow follow = new Follow(follower, following);
        followRepository.save(follow);
    }


    /**
     * Método para obtener los usuarios seguidos por un usuario.
     *
     * @param followerUsername El nombre de usuario del seguidor.
     * @return Lista de DTO de usuarios seguidos.
     * @throws IllegalArgumentException Si no se encuentra el usuario seguidor.
     */
    @Override
    public List<UserDTO> getFollowing(String followerUsername) {
        Optional<User> optionalFollower = userRepository.findByUsername(followerUsername);

        if (optionalFollower.isPresent()) {
            User follower = optionalFollower.get();
            List<Follow> follows = followRepository.findByFollower(follower);
            return follows.stream()
                    .map(Follow::getFollowing)
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("Usuario seguidor no encontrado: " + followerUsername);
        }
    }


    /**
     * Método para obtener los seguidores de un usuario.
     *
     * @param followingUsername El nombre de usuario del usuario a seguir.
     * @return Lista de DTO de seguidores.
     * @throws IllegalArgumentException Si no se encuentra el usuario a seguir.
     */
    @Override
    public List<UserDTO> getFollowers(String followingUsername) {
        User following = userRepository.findByUsername(followingUsername)
                .orElseThrow(() -> new IllegalArgumentException("Usuario a seguir no encontrado: " + followingUsername));

        List<Follow> followers = followRepository.findByFollowing(following);
        return followers.stream()
                .map(Follow::getFollower)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }



    /**
     * Método auxiliar para convertir un objeto User en un DTO.
     *
     * @param user El usuario a convertir.
     * @return DTO del usuario.
     */
    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getUsername(), user.getDescription(), user.getRole());
    }


    /**
     * Método para dejar de seguir a un usuario.
     *
     * @param followingUsername El nombre de usuario del usuario a dejar de seguir.
     * @param userDetails       Los detalles del usuario autenticado.
     * @throws IllegalArgumentException Si no se encuentra el usuario seguidor o el usuario a dejar de seguir, o si no hay una relación de seguimiento entre ellos.
     */
    @Override
    public void unfollowUser(String followingUsername, UserDetails userDetails) {
        String followerUsername = userDetails.getUsername();

        User follower = userRepository.findByUsername(followerUsername)
                .orElseThrow(() -> new IllegalArgumentException("Usuario seguidor no encontrado: " + followerUsername));

        User following = userRepository.findByUsername(followingUsername)
                .orElseThrow(() -> new IllegalArgumentException("Usuario a dejar de seguir no encontrado: " + followingUsername));

        List<Follow> follows = followRepository.findByFollowerAndFollowing(follower, following);

        if (!follows.isEmpty()) {
            // Si hay al menos una relación de seguimiento, eliminar la primera encontrada
            Follow followToRemove = follows.get(0);
            followRepository.delete(followToRemove);
        } else {
            throw new IllegalArgumentException("No se encontró una relación de seguimiento entre " + followerUsername + " y " + followingUsername);
        }
    }

}







