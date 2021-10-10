package com.zaher.bookstore.bookstore.user;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("orders/{userId}")
    public ResponseEntity<Long> getOrders(@PathVariable Long userId) {
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

}
