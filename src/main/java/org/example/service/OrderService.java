package org.example.service;

import org.example.repository.OrderRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }


}
