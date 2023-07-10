package main.java.com.example.geektrust.cart;

import java.util.List;

import main.java.com.example.geektrust.coupon.CouponB4G1;
import main.java.com.example.geektrust.coupon.CouponG20;
import main.java.com.example.geektrust.coupon.CouponG5;
import main.java.com.example.geektrust.coupon.CouponTypes;
import main.java.com.example.geektrust.program.Program;

public class BillCalculator {
  BillDetails billDetails = new BillDetails();

  private void applyPromembershipFee(Cart cart) {
    if (cart.isProMembership())
      billDetails.setProMembershipFee(Cart.PRO_MEMBERSHIP_FEE);
  }

  private void calculateSubTotal(Cart cart) {

    double programPrice = 0.00;
    for (Program pg : cart.programs) {
      billDetails.setSubtotal(billDetails.getSubtotal() + pg.getPrice(cart.proMembership));
      programPrice += pg.getPrice();
    }
    billDetails.setProDiscount(programPrice - billDetails.getSubtotal());
    billDetails.setSubtotal(billDetails.getSubtotal() + billDetails.getProMembershipFee());
  }

  private boolean canApplyCoupon(CouponTypes c, List<Program> programs, List<String> appliedCoupons) {
    switch (c) {
      case B4G1:
        return programs.size() >= CouponB4G1.MINIMUM_PROGRAMS_NEEDED;
      case DEAL_G20:
        return appliedCoupons.contains(CouponTypes.DEAL_G20.toString())
            && billDetails.getSubtotal() >= CouponG20.MINIMUM_CART_VALUE;
      case DEAL_G5:
        return appliedCoupons.contains(CouponTypes.DEAL_G5.toString()) && programs.size() >= 2;
      default:
        return false;
    }
  }

  private void applyDiscountStrategy(Cart cart) {
    if (canApplyCoupon(CouponTypes.B4G1, cart.getPrograms(), cart.getAppliedCoupons())) {
      billDetails.setDiscountStrategy(new CouponB4G1());
      billDetails.setCoupon(CouponTypes.B4G1.toString());
    } else if (canApplyCoupon(CouponTypes.DEAL_G20, cart.getPrograms(), cart.getAppliedCoupons())) {
      billDetails.setDiscountStrategy(new CouponG20());
      billDetails.setCoupon(CouponTypes.DEAL_G20.toString());
    } else if (canApplyCoupon(CouponTypes.DEAL_G5, cart.getPrograms(), cart.getAppliedCoupons())) {
      billDetails.setDiscountStrategy(new CouponG5());
      billDetails.setCoupon(CouponTypes.DEAL_G5.toString());
    }
    billDetails.setCouponDiscount(billDetails.getDiscountStrategy().getDiscount(cart, billDetails));
  }

  private void applyEnrollmentFee() {
    double totalBeforeEnrollmentFee = billDetails.getSubtotal();
    totalBeforeEnrollmentFee -= billDetails.getCouponDiscount();
    if (totalBeforeEnrollmentFee < Cart.ENROLLMENT_FEE_WAIVER_TOTAL) {
      billDetails.setEnrollmentFee(Cart.ENROLLMENT_FEE);
    }
  }

  public BillDetails calculate(Cart cart) {

    this.applyPromembershipFee(cart);

    this.calculateSubTotal(cart);

    // apply coupon discount
    this.applyDiscountStrategy(cart);

    // apply enrollment fee
    this.applyEnrollmentFee();

    // calculate total
    billDetails.setTotal(billDetails.getSubtotal() + billDetails.getEnrollmentFee() - billDetails.getCouponDiscount());

    return billDetails;
  }
}
