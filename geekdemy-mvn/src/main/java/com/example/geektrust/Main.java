package com.example.geektrust;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import main.java.com.example.geektrust.delegator.CommandDelegator;
import main.java.com.example.geektrust.delegator.CommandType;
import main.java.com.example.geektrust.delegator.Delegator;

public class Main {
    public static void main(String[] args) {

        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            Delegator delegator = new CommandDelegator();
            while (sc.hasNextLine()) {
                // Add your code here to process input commands
                String line = sc.nextLine();
                String[] cmdArgs = line.trim().split(" ");
                String[] data = Arrays.copyOfRange(cmdArgs, 1, cmdArgs.length);
                CommandType cmd = CommandType.valueOf(cmdArgs[0]);
                
                delegator.execute(cmd, data);
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
            System.out.println("Error: Invalid command"); 
        }

    }
}
