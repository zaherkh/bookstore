package com.zaher.bookstore.bookstore.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    @PostMapping("add")
    public ResponseEntity<Order> add(@RequestBody Order order) {
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @GetMapping("query")
    public ResponseEntity<Long> query(@RequestParam Long orderId) {
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }

    @GetMapping("list")
    public ResponseEntity<List<Order>> list() {
        return new ResponseEntity<List<Order>>(new ArrayList<Order>(), HttpStatus.OK);
    }

}
