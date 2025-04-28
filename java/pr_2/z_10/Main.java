import java.util.*;
import java.lang.*;
import java.nio.file.*;
import java.io.*;
import java.net.URL;

class Main {
	 public static void main(String[] args) {
        // Генерируем случайный код из 4 чисел
        Random random = new Random();
        int[] secretCode = new int[4];
        for (int i = 0; i < 4; i++) {
            secretCode[i] = random.nextInt(10); // Числа от 0 до 9
        }

        System.out.println("Игра началась! Угадайте 4-значный код за не более чем 20 попыток.");
        
        // Игровой цикл
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;
        while (attempts < 20) {
            System.out.print("Попытка #" + (attempts + 1) + ": Введите 4 числа через пробел: ");
            String input = scanner.nextLine();
            String[] inputArray = input.split(" ");

            // Проверка на правильность ввода
            if (inputArray.length != 4) {
                System.out.println("Неверный ввод. Пожалуйста, введите 4 числа.");
                continue;
            }

            // Преобразуем введенные числа в массив
            int[] guess = new int[4];
            try {
                for (int i = 0; i < 4; i++) {
                    guess[i] = Integer.parseInt(inputArray[i]);
                    if (guess[i] < 0 || guess[i] > 9) {
                        System.out.println("Число должно быть от 0 до 9.");
                        continue;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Попробуйте снова.");
                continue;
            }

            // Считаем количество совпадений
            int matches = countMatches(secretCode, guess);

            if (matches == 4) {
                System.out.println("Поздравляем! Вы угадали код.");
                break;
            } else {
                System.out.println("Количество совпадений: " + matches);
            }

            attempts++;
        }

        if (attempts == 20) {
            System.out.println("Вы не угадали код за 20 попыток. Код был: " + Arrays.toString(secretCode));
        }
    }

    // Метод для подсчета количества совпадений
    public static int countMatches(int[] secretCode, int[] guess) {
        int matches = 0;
        for (int i = 0; i < 4; i++) {
            if (secretCode[i] == guess[i]) {
                matches++;
            }
        }
        return matches;
    }
}