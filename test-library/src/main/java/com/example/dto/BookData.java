package com.example.dto;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;

public class BookData {
    
    @NotBlank(message = "Title is Required")
    private String title;

    @NotBlank(message = "Author is Required")
    private String author;

    @NotBlank(message = "Category is Required")
    private String category;
    
    @Temporal(TemporalType.DATE)
    private Date publishedDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    
}
