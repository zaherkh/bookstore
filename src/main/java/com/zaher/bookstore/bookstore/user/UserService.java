package com.zaher.bookstore.bookstore.user;

import com.zaher.bookstore.bookstore.order.Order;
import com.zaher.bookstore.bookstore.order.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    OrderService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        if(!userOptional.isPresent()) {
            logger.error("user not found");
            throw new UsernameNotFoundException("User not found in db");
        }
        User user = userOptional.get();
        logger.info("user found: {}", username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add( new SimpleGrantedAuthority(user.getRole()) );
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public User findUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        return userOptional.isPresent()? userOptional.get() : null;
    }

    public User findUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        return userOptional.isPresent()? userOptional.get() : null;
    }

    public User save(User user) {
        logger.info("creating new user");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.insert(user);
    }

    public Page<Order> findOrdersByUserid(String customerId, Pageable pageable) {
        Page<Order> orders = userService.findOrderByCustomerId(customerId, pageable);
        return orders;
    }
}
