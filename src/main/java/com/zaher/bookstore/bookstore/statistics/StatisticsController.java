package com.zaher.bookstore.bookstore.statistics;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/statistics")
public class StatisticsController {

    @GetMapping("get")
    public ResponseEntity<Object> get(@RequestParam Long customerId) {
        return new ResponseEntity<>(customerId, HttpStatus.OK);
    }

}
