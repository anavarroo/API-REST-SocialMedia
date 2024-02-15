package com.API_REST.persistence.repository;

import com.API_REST.persistence.model.Comment;
import com.API_REST.persistence.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepositoryI extends JpaRepository<Comment, Long> {
    List<Comment> findByPublication(Publication publication);
}
