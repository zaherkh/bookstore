package com.zaher.bookstore.bookstore.order;

import com.zaher.bookstore.bookstore.book.Book;
import com.zaher.bookstore.bookstore.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    BookService bookService;

    @PostMapping("add")
    public ResponseEntity<Order> add(@RequestBody Order order) {
        if(ObjectUtils.isEmpty(order.getCustomerId()) || ObjectUtils.isEmpty(order.getBookId()) ) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if(ObjectUtils.isEmpty(order.getQuantity()) && order.getQuantity() <= 0) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        Book book = bookService.findById(order.getBookId());
        if( ObjectUtils.isEmpty(book) && book.getStock().compareTo(order.getQuantity()) <= 0  ) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        book.setStock(book.getStock() - order.getQuantity());
        bookService.save(book);
        Order savedOrder = orderService.save(order);
        return new ResponseEntity<Order>(savedOrder, HttpStatus.OK);
    }

    @GetMapping("query")
    public ResponseEntity<Order> query(@RequestParam String orderId) {
        Order order = orderService.findById(orderId);
        if(ObjectUtils.isEmpty(order)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("list")
    public ResponseEntity<List<Order>> list() {
        return new ResponseEntity<List<Order>>(new ArrayList<Order>(), HttpStatus.OK);
    }

}
