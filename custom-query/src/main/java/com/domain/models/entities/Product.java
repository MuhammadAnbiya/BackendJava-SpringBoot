package com.domain.models.entities;

import java.io.Serializable;
import java.util.Locale.Category;
import java.util.Set;
import java.util.function.Supplier;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;


// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.JoinTable;

@Entity
@Table(name = "tbl_product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "product_name", length = 100)
    private String name;

    @Column(name = "product_description", length = 500)
    private String description;

    @ManyToMany
    private Category category;

    // private double price;

    @SuppressWarnings("rawtypes")
    @ManyToMany
    @JoinTable(name = "tbl_product_supplier", joinColumns = @JoinColumn(name =
    "produdct_id"), inverseJoinColumns = @JoinColumn(name="supplier_id"))
    // @JsonManageRefrence
    private Set<Supplier> suppliers;
    
    // public Product{

    // }
}
