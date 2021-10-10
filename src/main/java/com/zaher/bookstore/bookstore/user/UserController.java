package com.zaher.bookstore.bookstore.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("orders/{userId}")
    public ResponseEntity<Long> getOrders(@PathVariable Long userId) {
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

}
