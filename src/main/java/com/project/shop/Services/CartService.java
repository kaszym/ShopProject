package com.project.shop.Services;

import com.project.shop.Entities.Cart;
import com.project.shop.Entities.User;
import com.project.shop.Repositories.CartRepository;
import com.project.shop.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public List<Cart> findAll() {
        return (List<Cart>) cartRepository.findAll();
    }

    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    public Boolean updateCart(Cart cart) {
        try {
            cartRepository.save(cart);
        } catch (Exception ex){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean deleteCart(Long id) {
        try {
            cartRepository.deleteById(id);
        } catch (Exception ex){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}
