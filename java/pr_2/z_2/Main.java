import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число n: ");
        int n = scanner.nextInt();

        if (n <= 0 || n > 100000) {
            System.out.println("Число должно быть в диапазоне (0 < n <= 100000)");
            return;
        }

        if ((n - 1) % 2 != 0) {
            System.out.println("Решения нет (n - 1 должно делиться на 2 без остатка).");
            return;
        }

        int a = (n - 1) / 2;
        int firstSquare = a * a;
        int secondSquare = (a + 1) * (a + 1);

        System.out.println(n + " = " + secondSquare + " - " + firstSquare);
    }
	
}