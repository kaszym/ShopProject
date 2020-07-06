package com.project.shop.Repositories;

import com.project.shop.Entities.ProductCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {

    @Query("SELECT c FROM ProductCategory c WHERE c.name = ?1")
    Optional<ProductCategory> findCategoryByName(String name);
}
