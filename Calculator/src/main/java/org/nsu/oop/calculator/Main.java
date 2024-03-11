package org.nsu.oop.calculator;


public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        //Calculator calculator = new Calculator("");
        calculator.run("src/main/resources/instructions.txt");
    }
}
