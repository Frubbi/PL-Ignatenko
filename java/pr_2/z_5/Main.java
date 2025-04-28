import java.util.*;
import java.lang.*;
import java.math.BigInteger;
import java.io.*;

class Main
{
	public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);

        System.out.print("Введите число от 10 до 100000: ");
        int i = scanner.nextInt();
        
        Result result = findMaxDigitInFibonacci(i);

        System.out.println("Количество: " + result.count + ", Цифра: " + result.digit);
	}
	public static Result findMaxDigitInFibonacci(int n) {
        if (n < 10 || n > 100000) {
            throw new IllegalArgumentException("i должно быть от 10 до 100000");
        }

        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;

        for (int j = 2; j <= n; j++) {
            BigInteger temp = b;
            b = b.add(a);
            a = temp;
        }

        // Подсчёт цифр
        int[] counts = new int[10];
        String number = b.toString();
        for (char c : number.toCharArray()) {
            counts[c - '0']++;
        }

        // Поиск цифры с максимальной частотой
        int maxCount = 0;
        int maxDigit = 0;
        for (int digit = 0; digit <= 9; digit++) {
            if (counts[digit] > maxCount || (counts[digit] == maxCount && digit > maxDigit)) {
                maxCount = counts[digit];
                maxDigit = digit;
            }
        }

        return new Result(maxCount, maxDigit);
    }

    static class Result {
        int count;
        int digit;

        Result(int count, int digit) {
            this.count = count;
            this.digit = digit;
        }
    }
}