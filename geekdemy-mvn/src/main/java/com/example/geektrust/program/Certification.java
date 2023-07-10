package main.java.com.example.geektrust.program;

public class Certification extends Program {
  public static final int PRICE = 3000;
  public static final double PRO_MEMBERSHIP_DISCOUNT_PERCENT = 0.02;

  public Certification() {
    super();
    this.name = "Certification";
    this.price = PRICE;
    this.proMembershipDiscountPercent = PRO_MEMBERSHIP_DISCOUNT_PERCENT;
  }
}
