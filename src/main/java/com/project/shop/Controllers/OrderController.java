package com.project.shop.Controllers;

import com.project.shop.Entities.Order;
import com.project.shop.Enums.OrderStatus;
import com.project.shop.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shop/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public Order addOrder(@RequestBody Order order) {return orderService.createOrder(order); }


    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping(value = "/byStatus/{orderStatus}", produces = "application/json")
    public ResponseEntity<List<Order>> getByStatus(@PathVariable OrderStatus orderStatus) {
        return new ResponseEntity<>(orderService.findByStatus(orderStatus), HttpStatus.OK);
    }

    @GetMapping(value = "/byId/{id}", produces = "application/json")
    public ResponseEntity<Optional<Order>> getById(@PathVariable Long id) {
        Optional<Order> order = orderService.findById(id);

        if (order.isPresent()) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "/byUserName/{userName}", produces = "application/json")
    public ResponseEntity<Optional<Order>> getByUserName(@PathVariable String userName) {
        Optional<Order> order = orderService.findByUserName(userName);

        if(order.isPresent()) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping(value = "/changeOrderStatus/{orderId}/{orderStatus}")
    public ResponseEntity<Order> changeOrderStatus(@PathVariable Long orderId, @PathVariable OrderStatus orderStatus) {
        Optional<Order> order = orderService.findById(orderId);

        if (order.isPresent()) {
            order.get().setOrderStatus(orderStatus);
            orderService.updateOrder(order.get());
            return new ResponseEntity<>(order.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


//    @DeleteMapping(value = "/delete/{id}")
//    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
//        Optional<Order> order = orderService.findById(id);
//
//        Boolean isDeleted = false;
//
//        if (order.isPresent())
//            isDeleted = orderService.deleteOrder(id);
//            if (isDeleted)
//                return ResponseEntity.ok(id);
//        return (ResponseEntity<?>) ResponseEntity.badRequest();
//
//    }

}
