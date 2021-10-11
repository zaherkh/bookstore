package com.zaher.bookstore.bookstore.user;

import com.zaher.bookstore.bookstore.book.Book;
import com.zaher.bookstore.bookstore.book.BookService;
import com.zaher.bookstore.bookstore.dto.PreviousOrderDto;
import com.zaher.bookstore.bookstore.order.Order;
import com.zaher.bookstore.bookstore.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    OrderService orderService;

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
    public ResponseEntity<Page<PreviousOrderDto>> getOrders(@RequestParam String customerId, Pageable pageable) {
        Page<Order> orders = userService.findOrdersByUserid(customerId, pageable);

        List<PreviousOrderDto> previousOrderDtos = new ArrayList<>();
        Page<PreviousOrderDto> dtoPage = orders.map(new Function<Order, PreviousOrderDto>() {
            @Override
            public PreviousOrderDto apply(Order order) {
                PreviousOrderDto dto = orderService.mapOrderToPreviousOrderDto(order);
                return dto;
            }
        });
        return new ResponseEntity<>(dtoPage, HttpStatus.OK);
    }

}
