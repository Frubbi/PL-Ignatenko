import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите число: ");
        int n = scanner.nextInt();
        System.out.print("Введите начальный множитель s: ");
        int s = scanner.nextInt();

        if (checkPowerSum(n, s)) {
            System.out.println("Да, возможно!");
        } else {
            System.out.println("Нет, невозможно.");
        }
	}
	public static boolean checkPowerSum(int n, int startPower) {
        String numStr = String.valueOf(n);
        int sum = 0;
        int power = startPower;

        for (char c : numStr.toCharArray()) {
            int digit = Character.getNumericValue(c);
            sum += Math.pow(digit, power);
            power++;
        }

        // Проверяем делится ли сумма на n без остатка
        if (sum % n == 0) {
            int k = sum / n;
            System.out.println("sum = " + sum + ", n = " + n + ", k = " + k);
            return true;
        }

        return false;
    }
	
}