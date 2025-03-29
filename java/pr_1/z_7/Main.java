// задание 7

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку из повторяющихся слов: ");
        String input = scanner.nextLine();
        String[] words = input.split(" ");
        Set<String> uniqueWords = new LinkedHashSet<>();
        for (String word : words) {
            uniqueWords.add(word);
        }
        String result = String.join(" ", uniqueWords);
        System.out.println("Строка без повторяющихся слов: " + result);
        scanner.close();
    }
}
