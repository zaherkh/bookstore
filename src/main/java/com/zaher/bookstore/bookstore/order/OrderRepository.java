package com.zaher.bookstore.bookstore.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {

    Page<Order> findOrderByCustomerId(String customerId, Pageable pageable);

}
