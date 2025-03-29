// задание 3

import java.util.Scanner;

public class Main {

    public static void third(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку ");
        String string = scanner.nextLine();
        String formattedString = string.replaceAll("[aeiouAEIOU]", "");
        String formattedString2 = formattedString.replaceAll("[аеёийоуыэюяАЕЁИЙОУЫЭЮЯ]", "");
        System.out.println("Строка без гласных " + formattedString2);
   }
}