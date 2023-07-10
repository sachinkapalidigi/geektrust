package main.java.com.example.geektrust.program;

public class Diploma extends Program {
  public static final int PRICE = 2500;
  public static final double PRO_MEMBERSHIP_DISCOUNT_PERCENT = 0.01;

  public Diploma() {
    super();
    this.name = "Diploma";
    this.price = PRICE;
    this.proMembershipDiscountPercent = PRO_MEMBERSHIP_DISCOUNT_PERCENT;
  }
}
