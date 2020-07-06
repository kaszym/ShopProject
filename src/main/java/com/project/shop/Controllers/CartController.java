package com.project.shop.Controllers;

import com.project.shop.Entities.Cart;
import com.project.shop.Entities.Product;
import com.project.shop.Services.CartService;
import com.project.shop.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shop/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/add", produces = "application/json")
    public Cart addCart(@RequestBody Cart cart) {return cartService.createCart(cart); }

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<Cart>> getAllCarts() {return ResponseEntity.ok(cartService.findAll()); }

    @GetMapping(value = "/byId/{id}", produces = "application/json")
    public ResponseEntity<Cart> getById(@PathVariable Long id) {

        Optional<Cart> cart = cartService.findById(id);

        if (cart.isPresent())
            return new ResponseEntity<>(cart.get(), HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }

    @PatchMapping("/addProductToCart/{cartId}/{productId}")
    public ResponseEntity<Cart> addCartItem(@PathVariable Long cartId, @PathVariable Long productId) {

        Optional<Product> product = productService.findById(productId);
        Optional<Cart> cart = cartService.findById(cartId);

        if (product.isPresent() && cart.isPresent()) {
            cart.get().addProduct(product.get());
            cartService.updateCart(cart.get());
            productService.updateProduct(product.get());
            return new ResponseEntity<>(cart.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/removeProductFromCart/{cartId}/{productId}")
    public ResponseEntity<Cart> removeCartItem(@PathVariable Long cartId, @PathVariable Long productId) {

        Optional<Product> product = productService.findById(productId);
        Optional<Cart> cart = cartService.findById(cartId);
        List <Product> products = cart.get().getProducts();

        if (product.isPresent() && cart.isPresent() && products.contains(product.get())) {
            cart.get().removeProduct(product.get());
            cartService.updateCart(cart.get());
            productService.updateProduct(product.get());
            return new ResponseEntity<>(cart.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable Long id) {
        Optional<Cart> cart = cartService.findById(id);

        Boolean isDeleted = false;

        if (cart.isPresent())
            isDeleted = cartService.deleteCart(id);

        if (isDeleted)
            return ResponseEntity.ok("Deleted Cart, id: " + id);
        return (ResponseEntity<?>) ResponseEntity.badRequest();
    }
}
