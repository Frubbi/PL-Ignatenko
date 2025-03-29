// задание 5

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите первое число ");
        int firstNumber = scanner.nextInt();
        System.out.println("Введите второе число ");
        int secondNumber = scanner.nextInt();
        System.out.println("Введите третье число ");
        int thirdNumber = scanner.nextInt();
        int start = firstNumber;
        int step = secondNumber;
        int quantitySteps = thirdNumber;

        for (int i = 0; i <= quantitySteps; i++) {
            int currentNumber = start + i * step;
            System.out.println(currentNumber + (i < quantitySteps - 1 ? ",": ""));
        }
    }
}