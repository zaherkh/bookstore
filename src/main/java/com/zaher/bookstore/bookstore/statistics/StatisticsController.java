package com.zaher.bookstore.bookstore.statistics;

import com.zaher.bookstore.bookstore.book.Book;
import com.zaher.bookstore.bookstore.book.BookService;
import com.zaher.bookstore.bookstore.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("api/v1/statistics")
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;

    @Autowired
    BookService bookService;

    @GetMapping("get")
    public ResponseEntity<HashMap<String, Statistics>> get(@RequestParam String customerId) {
        Map<Object, List<Order>> statistics = statisticsService.getStatistics(customerId);
        HashMap<String, Statistics> result = new HashMap<>();
        statistics.forEach(new BiConsumer<Object, List<Order>>() {
            @Override
            public void accept(Object o, List<Order> orders) {
                LocalDateTime ldt = (LocalDateTime) o;
                String month = ldt.getMonth().getDisplayName(TextStyle.FULL, Locale.US);
                Statistics statistics1 = new Statistics();
                statistics1.setMonth(month);
                statistics1.setTotalOrderCount(orders.size());
                final AtomicReference<Integer>[] totalBookCount = new AtomicReference[]{new AtomicReference<>(0)};
                final AtomicReference<Float>[] totalPurchasedAmount = new AtomicReference[]{new AtomicReference<>(0f)};
                orders.forEach( order -> {
                    totalBookCount[0].updateAndGet(v -> v + order.getQuantity());
                    Book book = bookService.findById(order.getBookId());
                    totalPurchasedAmount[0].updateAndGet(v -> v + order.getQuantity() * book.getPrice());
                });
                statistics1.setTotalBookCount(totalBookCount[0].get());
                statistics1.setTotalPurchasedAmount(totalPurchasedAmount[0].get());
                result.put(month, statistics1);
            }
        });
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
