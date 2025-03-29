// задание 6

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<Integer> numbers;

    public Main() {
        numbers = new ArrayList<>();
    }

    public void addNumber(int number) {
        if (numbers.size() < 3) {
            numbers.add(number);
            System.out.println("Число " + number + " добавлено.");
        } else {
            int minIndex = findMinIndex();
            System.out.println("Ячейка полна. Заменяем " + numbers.get(minIndex) + " на " + number + ".");
            numbers.set(minIndex, number);
        }
        printNumbers();
    }

    private int findMinIndex() {
        return numbers.indexOf(Collections.min(numbers));
    }

    private void printNumbers() {
        System.out.println("Текущие числа в ячейке: " + numbers);
    }

    public static void main(String[] args) {
        Main memoryCell = new Main();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Введите число (или 'exit' для выхода): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            try {
                int number = Integer.parseInt(input);
                memoryCell.addNumber(number);
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите корректное число.");
            }
        }
        scanner.close();
    }
}
