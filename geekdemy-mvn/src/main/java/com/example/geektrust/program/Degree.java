package main.java.com.example.geektrust.program;

public class Degree extends Program {
  public static final int PRICE = 5000;
  public static final double PRO_MEMBERSHIP_DISCOUNT_PERCENT = 0.03;

  public Degree() {
    super();
    this.name = "Degree";
    this.price = PRICE;
    this.proMembershipDiscountPercent = PRO_MEMBERSHIP_DISCOUNT_PERCENT;
  }
}
