package main.java.com.example.geektrust.program;

import main.java.com.example.geektrust.program.Certification;
import main.java.com.example.geektrust.program.Degree;
import main.java.com.example.geektrust.program.Diploma;
import main.java.com.example.geektrust.program.Program;

public class ProgramFactory {
  public Program getProgram(ProgramType type) {
    switch (type) {
      case CERTIFICATION:
        return new Certification();
      
      case DIPLOMA:
        return new Diploma();

      case DEGREE:
        return new Degree();
      
      default:
        throw new IllegalArgumentException("Invalid program type: " + type);
    }
  }
}
