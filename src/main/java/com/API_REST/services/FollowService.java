package com.API_REST.services;

import com.API_REST.persistence.DTO.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface FollowService {


    void followUser(String followerUsername, String followingUsername);


    List<UserDTO> getFollowing(String followerUsername);

    List<UserDTO> getFollowers(String followingUsername);


    void unfollowUser(String followingUsername, UserDetails userDetails);
}
