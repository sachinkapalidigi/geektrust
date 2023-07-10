package main.java.com.example.geektrust.coupon;

import main.java.com.example.geektrust.cart.BillDetails;
import main.java.com.example.geektrust.cart.Cart;
import main.java.com.example.geektrust.discount.strategy.DiscountStrategy;

public class NoDiscount implements DiscountStrategy {
  public static final double DEFAULT_DISCOUNT = 0.00;

  @Override
  public double getDiscount(Cart cart, BillDetails billDetails) {
    return DEFAULT_DISCOUNT;
  }
}