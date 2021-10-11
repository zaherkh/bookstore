package com.zaher.bookstore.bookstore;

import com.zaher.bookstore.bookstore.book.Book;
import com.zaher.bookstore.bookstore.book.BookRepository;
import com.zaher.bookstore.bookstore.user.User;
import com.zaher.bookstore.bookstore.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;

@SpringBootApplication
public class BookstoreApplication {

	@Autowired
	UserService userService;

	Logger logger = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner runner (BookRepository bookRepository) {
		return args -> {
			User user = userService.findUserByRole("manager");
			if(ObjectUtils.isEmpty(user)) {
				logger.info("Creating user");
				user = new User("zaher", "123", "manager");
				userService.save(user);
			} else {
				logger.info("Not creating user");
			}
		};
	}

}
