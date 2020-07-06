package com.project.shop.Repositories;


import com.project.shop.Entities.Order;
import com.project.shop.Enums.OrderStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {

    @Query("SELECT o FROM Order o JOIN Cart c ON c.id = o.cart JOIN User u ON u.id = c.user WHERE u.userName = ?1")
    Optional<Order> findByUserName(String name);

    @Query("SELECT o FROM Order o WHERE o.orderStatus =?1")
    List<Order> findByStatus(OrderStatus orderStatus);


}
