// задание 8

import java.util.Scanner;

public class StringCaseConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Введите строку символов: ");
        String input = scanner.nextLine();

        int upperCaseCount = 0;
        int lowerCaseCount = 0;

        for (char ch : input.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                upperCaseCount++;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseCount++;
            }
        }

        String result;
        if (upperCaseCount > lowerCaseCount) {
            result = input.toUpperCase();
        } else if (lowerCaseCount > upperCaseCount) {
            result = input.toLowerCase();
        } else {
            result = input.toLowerCase();
        }

        System.out.println("Результат: " + result);
    }
}