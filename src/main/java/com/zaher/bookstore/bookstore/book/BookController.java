package com.zaher.bookstore.bookstore.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("add")
    public ResponseEntity<Book> add(@RequestBody Book book) {
        Book savedBook = bookService.save(book);
        return new ResponseEntity<Book>(savedBook, HttpStatus.OK);
    }

    @PutMapping("updatestock/{bookId}/{stockValue}")
    public ResponseEntity<Book> updateStock(@PathVariable String bookId, @PathVariable Integer stockValue) {
        Book book = bookService.updateBookStock(bookId, stockValue);
        if(ObjectUtils.isEmpty(book)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

}
