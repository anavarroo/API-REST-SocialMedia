package com.API_REST.services;

import com.API_REST.persistence.DTO.UserDTO;
import com.API_REST.persistence.model.User;


public interface UserService {

        User getUserByUsername(String username);
        UserDTO searchUserByUsername(String username);


        void updateDescription(String username, String newDescription);


}
