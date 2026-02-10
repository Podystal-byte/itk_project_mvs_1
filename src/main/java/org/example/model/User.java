package org.example.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Email
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orderList;

    public User() {}

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @JsonView(Views.Public.class)
    public Long getId() {
        return id;
    }

    @JsonView(Views.Public.class)
    public String getName() {
        return name;
    }

    @JsonView(Views.Public.class)
    public String getEmail() {
        return email;
    }

    @JsonView(Views.Internal.class)
    public List<Order> getOrderList() {
        return orderList;
    }
}
