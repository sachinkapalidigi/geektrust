package main.java.com.example.geektrust.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.com.example.geektrust.coupon.CouponB4G1;
import main.java.com.example.geektrust.coupon.CouponG20;
import main.java.com.example.geektrust.coupon.CouponG5;
import main.java.com.example.geektrust.coupon.CouponTypes;
import main.java.com.example.geektrust.coupon.NoDiscount;
import main.java.com.example.geektrust.discount.strategy.DiscountStrategy;
import main.java.com.example.geektrust.program.Program;

public class Cart {
  List<Program> programs;
  boolean proMembership = false;
  List<String> appliedCoupons;
  public static final double ENROLLMENT_FEE = 500.00;
  public static final double ENROLLMENT_FEE_WAIVER_TOTAL = 6666.00;
  public static final double PRO_MEMBERSHIP_FEE = 200.00;

  private BillCalculator billCalculator = new BillCalculator();

  public boolean isProMembership() {
    return proMembership;
  }

  public Cart() {
    this.programs = new ArrayList<>();
    this.appliedCoupons = new ArrayList<>();
  }

  public void addToCart(Program program) {
    this.programs.add(program);
  }

  public void addProMembership() {
    this.proMembership = true;
  }

  public void applyCoupon(String coupon) {
    this.appliedCoupons.add(coupon);
  }

  public List<Program> getPrograms() {
    return programs;
  }

  public List<String> getAppliedCoupons() {
    return appliedCoupons;
  }

  public BillDetails generateBill() {
    return this.billCalculator.calculate(this);
  }
}
