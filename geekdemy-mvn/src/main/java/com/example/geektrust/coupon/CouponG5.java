package main.java.com.example.geektrust.coupon;

import main.java.com.example.geektrust.cart.BillDetails;
import main.java.com.example.geektrust.cart.Cart;
import main.java.com.example.geektrust.discount.strategy.DiscountStrategy;

public class CouponG5 implements DiscountStrategy {
  public static final int MINIMUM_PROGRAMS_NEEDED = 2;
  public static final double DISCOUNT_PERCENTAGE = 0.05;

  @Override
  public double getDiscount(Cart cart, BillDetails billDetails) {
    if (cart.getPrograms().size() < MINIMUM_PROGRAMS_NEEDED)
      return NoDiscount.DEFAULT_DISCOUNT;
    return billDetails.getSubtotal() * DISCOUNT_PERCENTAGE;
  }
}