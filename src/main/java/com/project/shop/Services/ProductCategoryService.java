package com.project.shop.Services;

import com.project.shop.Entities.Product;
import com.project.shop.Entities.ProductCategory;
import com.project.shop.Repositories.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public ProductCategory createProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    public Optional<ProductCategory> findById(Long id) {
        return productCategoryRepository.findById(id);
    }

    public List<ProductCategory> findAll() {
        return (List<ProductCategory>) productCategoryRepository.findAll();
    }

    public Optional<ProductCategory> findByName(String name) {
        return productCategoryRepository.findCategoryByName(name);
    }

    public Boolean deleteProductCategory(Long id) {
        try {
            productCategoryRepository.deleteById(id);
        } catch (Exception ex) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean updateProductCategory(ProductCategory productCategory) {
        try {
            productCategoryRepository.save(productCategory);
        } catch (Exception ex){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
