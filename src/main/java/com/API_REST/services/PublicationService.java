package com.API_REST.services;

import com.API_REST.persistence.DTO.PublicationDTO;
import com.API_REST.security.auth.model.PublicationRequest;

import java.util.List;

public interface PublicationService {

    PublicationDTO createPublication(PublicationRequest request, String username);

    List<PublicationDTO> getAllPublications();

    void deletePublication(Long publicationId);

    PublicationDTO updatePublication(Long publicationId, PublicationRequest request);

    List<PublicationDTO> getAllPublicationsByUser(String username);

    List<PublicationDTO> getPublicationsFromFollowedUsers(String followerUsername);





}
