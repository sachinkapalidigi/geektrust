package main.java.com.example.geektrust.discount.strategy;

import main.java.com.example.geektrust.cart.BillDetails;
import main.java.com.example.geektrust.cart.Cart;

public interface DiscountStrategy {
  double getDiscount(Cart cart, BillDetails billDetails);
}

// B4G1, DEAL_G20, DEAL_G5
