package com.domain.models.entities;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "tbl_product") // nama table yang akan muncul di database
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id // id dari database nya
    @GeneratedValue(strategy = GenerationType.IDENTITY) // yang bikin jadi auto increment
    private long id; // private = aksesibilitas, long = type data primitive yg lebih panjang dari int, id nama dari variabel

    @NotEmpty(message = "Name is Required") // logic agar si entitinya ngga boleh kosong, nanti ada pesannya di "message"
    @Column(name = "product_name", length = 100) // keterangan dari entitinya
    private String name; // private = aksesibilitas, String = type data primitive yg untuk huruf, name nama dari variabel

    @NotEmpty(message = "Description is Required") 
    @Column(name = "product_description", length = 500)
    private String description;

    private double price; // private = aksesibilitas, double = type data primitive untuk desimal, price nama dari variabel

    @ManyToOne
    private Category category;

    @ManyToMany
    @JoinTable(name= "tbl_product_supplier",
    joinColumns = @JoinColumn(name= "product_id"),
    inverseJoinColumns = @JoinColumn(name= "supplier_id"))
    private Set<Supplier> suppliers;

    // Default constructor
    public Product() {
    }

    // Constructor with parameters
    public Product(long id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    
}
