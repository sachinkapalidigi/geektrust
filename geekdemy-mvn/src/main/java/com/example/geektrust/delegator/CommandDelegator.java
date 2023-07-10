package main.java.com.example.geektrust.delegator;

import main.java.com.example.geektrust.cart.BillDetails;
import main.java.com.example.geektrust.cart.Cart;
import main.java.com.example.geektrust.program.Program;
import main.java.com.example.geektrust.program.ProgramFactory;
import main.java.com.example.geektrust.program.ProgramType;
import main.java.com.example.geektrust.student.Student;

public class CommandDelegator implements Delegator {
  private Student student;

  public CommandDelegator() {
    this.student = new Student();
  }

  public void execute(CommandType command, String[] args) {
    switch (command) {
      case ADD_PROGRAMME:
        this.addProgram(args[0], args[1]);
        break;

      case APPLY_COUPON:
        this.student.getCart().applyCoupon(args[0]);
        break;

      case ADD_PRO_MEMBERSHIP:
        this.student.getCart().addProMembership();
        break;

      case PRINT_BILL:
        this.printBillDetails();
        break;

      default:
        break;
    }
  }

  private void addProgram(String programType, String quantityStr) {
    ProgramType type = ProgramType.valueOf(programType);
    ProgramFactory pf = new ProgramFactory();
    Program program = pf.getProgram(type);
    // Add to cart
    Cart studentCart = student.getCart();
    int quantity = Integer.valueOf(quantityStr);
    for (int i = 0; i < quantity; i++) {
      studentCart.addToCart(program);
    }
  }

  private void printBillDetails() {
    /**
     * SUB_TOTAL 13000.00
     * COUPON_DISCOUNT B4G1 2500.00
     * TOTAL_PRO_DISCOUNT 0.00
     * PRO_MEMBERSHIP_FEE 0.00
     * ENROLLMENT_FEE 0.00
     * TOTAL 10500.00
     */

    BillDetails billDetails = this.student.getCart().generateBill();
    System.out.format("SUB_TOTAL %.2f\n", billDetails.getSubtotal());
    System.out.format("COUPON_DISCOUNT %s %.2f\n", billDetails.getCoupon(), billDetails.getCouponDiscount());
    System.out.format("TOTAL_PRO_DISCOUNT %.2f\n", billDetails.getProDiscount());
    System.out.format("PRO_MEMBERSHIP_FEE %.2f\n", billDetails.getProMembershipFee());
    System.out.format("ENROLLMENT_FEE %.2f\n", billDetails.getEnrollmentFee());
    System.out.format("TOTAL %.2f\n", billDetails.getTotal());
  }
}
