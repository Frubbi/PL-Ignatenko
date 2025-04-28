import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
	public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество палочек: ");
        long n = scanner.nextLong();

        System.out.println("Количество палочек у Тани: " + countSticksForTanya(n));
	}
	 public static long countSticksForTanya(long n) {
        long tanyaSticks = 0;
        boolean isTanyaTurn = true; // Таня начинает игру

        while (n > 0) {
            if (n % 2 == 0) {
                // Четное количество палочек
                if (isTanyaTurn) {
                    // Таня забирает либо одну палочку, либо половину всех
                    // Для оптимальности она должна забрать половину (максимальное количество)
                    tanyaSticks += n / 2;
                    n -= n / 2;
                } else {
                    // Саша забирает половину (максимум)
                    n -= n / 2;
                }
            } else {
                // Нечетное количество палочек
                if (isTanyaTurn) {
                    // Таня забирает 1 палочку
                    tanyaSticks += 1;
                    n -= 1;
                } else {
                    // Саша забирает 1 палочку
                    n -= 1;
                }
            }
            isTanyaTurn = !isTanyaTurn; // Меняем очередь
        }

        return tanyaSticks;
    }
}