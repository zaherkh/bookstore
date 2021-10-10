package com.zaher.bookstore.bookstore.book;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/book")
public class BookController {

    @PostMapping("add")
    public ResponseEntity<Book> add(@RequestBody Book book) {
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @PutMapping("updatestock/{bookId}/{stockValue}")
    public ResponseEntity<Long> updateStock(@PathVariable Long bookId, @PathVariable Long stockValue) {
        return new ResponseEntity<>(stockValue, HttpStatus.OK);
    }

}
