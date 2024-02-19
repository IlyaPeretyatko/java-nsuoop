package org.nsu.oop.task1;

import java.util.Random;
import java.lang.Integer;
import java.util.Scanner;
import static java.lang.Integer.valueOf;

public class BullsAndCows {
    private final int hiddenValue;

    BullsAndCows() {
        hiddenValue = generateValue();
        System.out.print("Число сгенерированно.\n");
    }

    private static int generateValue() {
        StringBuilder number = new StringBuilder();
        out:
        while (number.length() != 4) {
            Random random = new Random();
            Integer value = random.nextInt(10);
            if (number.isEmpty() && value == 0) {
                continue;
            }
            for (int i = 0; i < number.length(); ++i) {
                if (number.toString().charAt(i) == value.toString().charAt(0)) {
                    continue out;
                }
            }
            number.append(value);
        }
        return Integer.parseInt(number.toString());
    }

    private static boolean legitCheckValue(int value) {
        if (valueOf(value).toString().length() != 4) {
            System.out.println("Число должно быть 4-х разрядным.");
            return false;
        }
        if (valueOf(value).toString().chars().distinct().toArray().length != 4) {
            System.out.println("Число должно состоять из разных цифр.");
            return false;
        }
        return true;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int bulls = 0;
        int cows;
        while (bulls != 4) {
            bulls = 0;
            cows = 0;
            int value = scanner.nextInt();
            if (!legitCheckValue(value)) {
                System.out.println("Число не подходит по формату. Попробуйте ещё раз.");
                continue;
            }
            for (int i = 0; i < 4; ++i) {
                char currentSymbol = valueOf(value).toString().charAt(i);
                if (currentSymbol == valueOf(hiddenValue).toString().charAt(i)) {
                    bulls++;
                }
                for (int j = 0; j < 4; ++j) {
                    if (currentSymbol == valueOf(hiddenValue).toString().charAt(j)) {
                        cows++;
                    }
                }
            }
            System.out.println("Bulls: " + bulls + " Cows: " + cows);
        }
        System.out.println("Win!");
    }


}
