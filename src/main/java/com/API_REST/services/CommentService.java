package com.API_REST.services;

import com.API_REST.persistence.DTO.CommentDTO;
import com.API_REST.security.auth.model.CommentRequest;

public interface CommentService {
    CommentDTO createComment(Long publicationId, CommentRequest request, String username);
}