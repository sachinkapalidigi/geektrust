package main.java.com.example.geektrust.coupon;

import java.util.Comparator;
import java.util.List;

import main.java.com.example.geektrust.cart.BillDetails;
import main.java.com.example.geektrust.cart.Cart;
import main.java.com.example.geektrust.discount.strategy.DiscountStrategy;
import main.java.com.example.geektrust.program.Program;

public class CouponB4G1 implements DiscountStrategy {
  public static final int MINIMUM_PROGRAMS_NEEDED = 4;

  @Override
  public double getDiscount(Cart cart, BillDetails billDetails) {
    List<Program> programs = cart.getPrograms();
    if (programs.size() >= MINIMUM_PROGRAMS_NEEDED) {
      programs.sort(new Comparator<Program>() {
        @Override
        public int compare(Program p1, Program p2) {
          return ((int) p1.getPrice() - (int) p2.getPrice());
        }
      });
      return programs.get(0).getPrice(cart.isProMembership());
    }
    return NoDiscount.DEFAULT_DISCOUNT;
  }
}