package com.project.shop.Services;

import com.project.shop.Entities.Product;
import com.project.shop.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findByCategory(String name) {
        return productRepository.findByCategory(name);
    }

    public Slice<Product> findByName(String name, Pageable pageable) {
        return productRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    public Boolean deleteById(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception ex){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean updateProduct(Product product) {
        try {
            productRepository.save(product);
        } catch (Exception ex){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
