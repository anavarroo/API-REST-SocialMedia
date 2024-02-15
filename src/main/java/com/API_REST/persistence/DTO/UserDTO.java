package com.API_REST.persistence.DTO;

import com.API_REST.persistence.model.Role;
import com.API_REST.persistence.model.User;


public class UserDTO {
    String username;
    String desc;
    Role role;


    public UserDTO() {

    }

    public UserDTO(String username, String desc, Role role) {
        this.username = username;
        this.desc = desc;
        this.role = role;
    }

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.desc = user.getDescription();
        this.role = user.getRole();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
