package com.project.shop.Services;

import com.project.shop.Entities.Order;
import com.project.shop.Entities.User;
import com.project.shop.Enums.OrderStatus;
import com.project.shop.Repositories.CartRepository;
import com.project.shop.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> findAll() {
        return (List<Order>) orderRepository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> findByStatus(OrderStatus orderStatus) {
        return orderRepository.findByStatus(orderStatus);
    }

    public Optional<Order> findByUserName(String userName) { return  orderRepository.findByUserName(userName); }

    public Boolean updateOrder(Order order) {
        try {
            orderRepository.save(order);
        } catch (Exception ex){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

//    public Boolean deleteOrder(Long id) {
//        try {
//            orderRepository.deleteById(id);
//        } catch (Exception ex){
//            return Boolean.FALSE;
//        }
//        return Boolean.TRUE;
//    }

}
