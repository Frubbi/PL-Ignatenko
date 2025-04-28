import java.util.*;
import java.lang.*;
import java.math.BigInteger;
import java.io.*;

class Main
{
	public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество палочек: ");
        long n = scanner.nextLong();

        System.out.println(playGame(n));
	}
	public static String playGame(long n) {
        boolean isSashaTurn = true; // Саша ходит первым

        while (n > 0) {
            if (n % 2 == 0) {
                // Четное количество палочек
                if (n / 2 >= 1) {
                    n /= 2;
                } else {
                    n -= 1;
                }
            } else {
                // Нечетное количество палочек
                n -= 1;
            }
            isSashaTurn = !isSashaTurn; // Меняем игрока
        }

        // Когда палочек 0 - выигрывает тот, кто сделал последний ход
        return isSashaTurn ? "Таня победила!" : "Саша победил!";
    }
}