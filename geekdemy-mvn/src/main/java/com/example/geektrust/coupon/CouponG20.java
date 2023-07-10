package main.java.com.example.geektrust.coupon;

import java.util.Comparator;
import java.util.List;

import main.java.com.example.geektrust.cart.BillDetails;
import main.java.com.example.geektrust.cart.Cart;
import main.java.com.example.geektrust.discount.strategy.DiscountStrategy;
import main.java.com.example.geektrust.program.Program;

public class CouponG20 implements DiscountStrategy {
  public static final double DISCOUNT_PERCENTAGE = 0.20;
  public static final double MINIMUM_CART_VALUE = 10000.00;

  @Override
  public double getDiscount(Cart cart, BillDetails billDetails) {
    if (billDetails.getSubtotal() < MINIMUM_CART_VALUE)
      return NoDiscount.DEFAULT_DISCOUNT;
    return billDetails.getSubtotal() * DISCOUNT_PERCENTAGE;
  }
}
