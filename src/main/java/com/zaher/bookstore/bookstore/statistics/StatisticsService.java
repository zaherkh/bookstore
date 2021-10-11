package com.zaher.bookstore.bookstore.statistics;

import com.zaher.bookstore.bookstore.book.BookService;
import com.zaher.bookstore.bookstore.order.Order;
import com.zaher.bookstore.bookstore.order.OrderService;
import com.zaher.bookstore.bookstore.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsService {
    @Autowired
    BookService bookService;

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    public Map<Object, List<Order>> getStatistics(String customerId) {
        List<Order> orders = orderService.findOrderByCustomerId(customerId);
        Map<Object, List<Order>> result = orders.stream().collect(Collectors.groupingBy(item -> {
            LocalDateTime createdAt = item.getCreatedAt();
            createdAt = createdAt.truncatedTo(ChronoUnit.DAYS);
            item.setCreatedAt(createdAt);
            return item.getCreatedAt().with(TemporalAdjusters.firstDayOfMonth());
        }));
        return result;
    }
}
