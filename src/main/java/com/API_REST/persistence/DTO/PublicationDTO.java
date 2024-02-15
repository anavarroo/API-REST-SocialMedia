package com.API_REST.persistence.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class PublicationDTO {

    private Long id;
    private UserDTO author;
    private String text;
    private String imageUrl; // Nuevo campo para la URL de la imagen
    private LocalDateTime creationDate;
    private LocalDateTime editDate;

    private List<CommentDTO> comments;




    public PublicationDTO() {
    }

    public PublicationDTO(Long id, UserDTO author, String text, String imageUrl, LocalDateTime creationDate, LocalDateTime editDate, List<CommentDTO> comments) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.imageUrl = imageUrl;
        this.creationDate = creationDate;
        this.editDate = editDate;
        this.comments = comments;
    }


    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getEditDate() {
        return editDate;
    }

    public void setEditDate(LocalDateTime editDate) {
        this.editDate = editDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
