package com.API_REST.services;

import com.API_REST.persistence.DTO.UserDTO;
import com.API_REST.persistence.model.User;
import com.API_REST.persistence.repository.FollowRepositoryI;
import com.API_REST.persistence.repository.UserRepositoryI;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepositoryI userRepo;

    @Autowired
    private FollowRepositoryI followRepository;



    /**
     * Método para obtener un usuario por su nombre de usuario.
     *
     * @param username El nombre de usuario del usuario a buscar.
     * @return El usuario encontrado o null si no se encuentra.
     */
    @Override
    public User getUserByUsername(String username) {
        Optional<User> userOptional = userRepo.findByUsername(username);

        return userOptional.orElse(null);
    }

    /**
     * Método para buscar un usuario por su nombre de usuario y convertirlo a un DTO.
     *
     * @param username El nombre de usuario del usuario a buscar.
     * @return DTO del usuario encontrado o null si no se encuentra.
     */
    @Override
    public UserDTO searchUserByUsername(String username) {
        Optional<User> userOptional = userRepo.findByUsername(username);

        return userOptional.map(this::convertToDTO).orElse(null);
    }

    /**
     * Método para actualizar la descripción de un usuario.
     *
     * @param username      El nombre de usuario del usuario cuya descripción se actualizará.
     * @param newDescription La nueva descripción del usuario.
     */
    @Override
    @Transactional
    public void updateDescription(String username, String newDescription) {
        userRepo.updateDescription(username, newDescription);
    }

    /**
     * Método auxiliar para convertir un objeto User en un DTO.
     *
     * @param user El usuario a convertir.
     * @return DTO del usuario.
     */
    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO(user.getUsername(), user.getDescription(), user.getRole());
        return userDTO;
    }




}
