package com.zaher.bookstore.bookstore;

import com.zaher.bookstore.bookstore.book.Book;
import com.zaher.bookstore.bookstore.book.BookRepository;
import com.zaher.bookstore.bookstore.user.User;
import com.zaher.bookstore.bookstore.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BookstoreApplication {

	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	CommandLineRunner runner (BookRepository bookRepository) {
//		return args -> {
//			User user = new User("customer1", "123", "customer");
//			userService.save(user);
//		};
//	}

}
