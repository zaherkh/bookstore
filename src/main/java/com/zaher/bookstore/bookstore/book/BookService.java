package com.zaher.bookstore.bookstore.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book findById(String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        return bookOptional.isPresent()? bookOptional.get() : null;
    }

    public Book save(Book book) {
        Book savedBook = bookRepository.save(book);
        return savedBook;
    }

    public Book updateBookStock(String bookId, Integer stockValue) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        Book book = null;
        if(bookOptional.isPresent()) {
            book = bookOptional.get();
            book.setStock(stockValue);
            book = bookRepository.save(book);
        }
        return book;
    }
}
