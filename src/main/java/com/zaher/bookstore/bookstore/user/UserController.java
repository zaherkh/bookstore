package com.zaher.bookstore.bookstore.user;

import com.zaher.bookstore.bookstore.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User userByUsername = userService.findUserByUsername(user.getUsername());
        User userByEmail = userService.findUserByEmail(user.getEmail());
        if( !ObjectUtils.isEmpty(userByUsername) || !ObjectUtils.isEmpty(userByEmail)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        user.setRole("customer");
        User savedUser = userService.save(user);
        return new ResponseEntity<User>(savedUser, HttpStatus.OK);
    }

    @GetMapping("orders")
    public ResponseEntity<Page<Order>> getOrders(@RequestParam String customerId, Pageable pageable) {
        Page<Order> orders = userService.findOrdersByUserid(customerId, pageable);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

}
