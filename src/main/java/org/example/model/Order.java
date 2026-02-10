package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public String getName() {
        return name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
