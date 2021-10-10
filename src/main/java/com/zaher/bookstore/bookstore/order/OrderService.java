package com.zaher.bookstore.bookstore.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public Order save(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        return savedOrder;
    }

    public Order findById(String orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        return orderOptional.isPresent()? orderOptional.get() : null;
    }

}
