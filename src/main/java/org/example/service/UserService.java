package org.example.service;

import jakarta.validation.ValidationException;
import org.example.exceptions.NotFoundException;
import org.example.model.Order;
import org.example.model.User;
import org.example.repository.OrderRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public UserService(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;

    }

    public List<User> allUsers(){
        return userRepository.findAll();
    }

    public User addUser(User user){
        if (user.getEmail().isEmpty() || user.getName().isEmpty()){
            throw new ValidationException("Не заполнено имя или эмейл");
        }

        userRepository.save(user);

        return user;
    }

    public User getUserWithDetails(Long id) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()){
            throw new NotFoundException("Данного пользователя не существует");
        }

        return user.get();
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    private void addOrderToUser(User user, Order order) {
        if (order.getName() == null || order.getName().isBlank() || order.getAmount() <= 0) {
            throw new ValidationException("Данные заказа некорректны");
        }
        user.getOrderList().add(order);
    }

    public User addOrder(Long userId, Order order) throws NotFoundException {
        User user = getUserWithDetails(userId);

        order.setUser(user);
        addOrderToUser(user, order);

        orderRepository.save(order);
        return userRepository.save(user);
    }
}
