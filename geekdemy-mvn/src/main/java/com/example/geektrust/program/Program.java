package main.java.com.example.geektrust.program;

public abstract class Program {
  public static final String DEFAULT_CURRENCY = "Rs";
  protected String name;
  protected double price;
  protected String currency;
  protected double proMembershipDiscountPercent;

  public Program() {
    this.currency = DEFAULT_CURRENCY;
  }

  public String getName() {
    return this.name;
  }

  public double getPrice() {
    return this.price;
  }

  public double getPrice(boolean proMembershipDiscount) {
    if (proMembershipDiscount)
      return this.price - this.price * this.proMembershipDiscountPercent;
    return getPrice();
  }

  public String getCurrency() {
    return this.currency;
  }
}