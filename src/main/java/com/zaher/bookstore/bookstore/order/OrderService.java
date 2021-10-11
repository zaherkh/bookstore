package com.zaher.bookstore.bookstore.order;

import com.zaher.bookstore.bookstore.book.Book;
import com.zaher.bookstore.bookstore.book.BookService;
import com.zaher.bookstore.bookstore.dto.PreviousOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    BookService bookService;

    public Order save(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        return savedOrder;
    }

    public Order findById(String orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        return orderOptional.isPresent()? orderOptional.get() : null;
    }

    public Page<Order> findOrderByCustomerId(String customerId, Pageable pageable) {
        Page<Order> orders = orderRepository.findOrderByCustomerId(customerId, pageable);
        return orders;
    }

    public List<Order> findOrderByCustomerId(String customerId) {
        List<Order> orders = orderRepository.findOrderByCustomerId(customerId);
        return orders;
    }

    public PreviousOrderDto mapOrderToPreviousOrderDto(Order order) {
        Book book = bookService.findById(order.getBookId());
        PreviousOrderDto previousOrderDto = new PreviousOrderDto();
        previousOrderDto.setBookName(book.getName());
        previousOrderDto.setCreatedAt(order.getCreatedAt());
        previousOrderDto.setQuantity(order.getQuantity());
        return previousOrderDto;
    }

}
