package com.example.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class BookData {
    
    @NotBlank(message = "Title is Required")
    private String title;

    @NotBlank(message = "Author is Required")
    private String author;

    @NotBlank(message = "Category is Required")
    private String category;
    
    @NotNull(message = "Published Date is Required")
    private LocalDate publishedDate;

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

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    
}
