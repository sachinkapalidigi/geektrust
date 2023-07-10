package main.java.com.example.geektrust.student;

import java.util.UUID;

import main.java.com.example.geektrust.cart.Cart;

public class Student {
  public static final String DEFAULT_NAME = "Guest";
  String name;
  String id;
  Cart cart;

  public Student() {
    this.name = DEFAULT_NAME;
    this.id = UUID.randomUUID().toString();
    this.cart = new Cart();
  }

  public Cart getCart() {
    return this.cart;
  }
}
