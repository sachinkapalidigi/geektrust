package main.java.com.example.geektrust.cart;

import java.util.HashMap;
import java.util.Map;

import main.java.com.example.geektrust.coupon.CouponB4G1;
import main.java.com.example.geektrust.coupon.CouponG20;
import main.java.com.example.geektrust.coupon.CouponG5;
import main.java.com.example.geektrust.coupon.CouponTypes;
import main.java.com.example.geektrust.coupon.NoDiscount;
import main.java.com.example.geektrust.discount.strategy.DiscountStrategy;

public class BillDetails {
  private double subtotal = 0.00;
  private double couponDiscount = 0.00;
  private String coupon = "NONE";
  private double proDiscount = 0.00;
  private double proMembershipFee = 0.00;
  private double enrolmentFee = 0.00;
  private double total = 0.00;

  private DiscountStrategy discountStrategy = new NoDiscount();;
  private Map<String, DiscountStrategy> discountStrategyCoupons = new HashMap();

  public BillDetails() {
    discountStrategyCoupons.put(CouponTypes.DEAL_G20.toString(), new CouponG20());
    discountStrategyCoupons.put(CouponTypes.DEAL_G5.toString(), new CouponG5());
    discountStrategyCoupons.put(CouponTypes.B4G1.toString(), new CouponB4G1());
  }

  public double getSubtotal() {
    return subtotal;
  }

  public double getCouponDiscount() {
    return couponDiscount;
  }

  public String getCoupon() {
    return coupon;
  }

  public double getProDiscount() {
    return proDiscount;
  }

  public double getProMembershipFee() {
    return proMembershipFee;
  }

  public double getEnrollmentFee() {
    return enrolmentFee;
  }

  public double getTotal() {
    return total;
  }

  public DiscountStrategy getDiscountStrategy() {
    return discountStrategy;
  }

  public Map<String, DiscountStrategy> getDiscountStrategyCoupons() {
    return discountStrategyCoupons;
  }

  public void setSubtotal(double subtotal) {
    this.subtotal = subtotal;
  }

  public void setCouponDiscount(double couponDiscount) {
    this.couponDiscount = couponDiscount;
  }

  public void setCoupon(String coupon) {
    this.coupon = coupon;
  }

  public void setProDiscount(double proDiscount) {
    this.proDiscount = proDiscount;
  }

  public void setProMembershipFee(double proMembershipFee) {
    this.proMembershipFee = proMembershipFee;
  }

  public void setEnrollmentFee(double enrolmentFee) {
    this.enrolmentFee = enrolmentFee;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public void setDiscountStrategy(DiscountStrategy discountStrategy) {
    this.discountStrategy = discountStrategy;
  }
}
