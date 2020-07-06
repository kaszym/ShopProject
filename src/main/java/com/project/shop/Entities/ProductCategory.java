package com.project.shop.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_category")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_cat_id")
    private Long id;

    @Column(name = "pro_cat_name", nullable = false, unique = true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productCategory", cascade = CascadeType.ALL)
    List<Product> products;

    public ProductCategory() {}

    public ProductCategory(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addProduct(Product product) {
        if(this.products == null) {
            this.products = new ArrayList<>();
        }
        this.products.add(product);
        product.setProductCategory(this);
    }

}
