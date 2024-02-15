package com.API_REST.persistence.repository;

import com.API_REST.persistence.model.Publication;
import com.API_REST.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicationRepositoryI extends JpaRepository<Publication,Long> {

    List<Publication> findByAuthor(User author);

    List<Publication> findByAuthorInOrderByCreationDateDesc(List<User> authors);

    List<Publication> findAllByOrderByCreationDateDesc();

}
