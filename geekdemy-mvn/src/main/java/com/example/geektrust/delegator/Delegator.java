package main.java.com.example.geektrust.delegator;

public interface Delegator {
  void execute(CommandType command, String[] args);
}
