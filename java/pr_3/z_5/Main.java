import java.util.Scanner;

public class Main {

    public static int fusc(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int a = 0;
        int b = 1;

        // проходим по битам числа n с самой значащей позиции
        String binary = Integer.toBinaryString(n);

        for (int i = 1; i < binary.length(); i++) {
            char bit = binary.charAt(i);
            if (bit == '0') {
                b = a + b; // fusc(2n)
            } else {
                a = a + b; // fusc(2n + 1)
            }
        }

        return a + b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите n: ");
        int n = scanner.nextInt();
        System.out.println("fusc(" + n + ") = " + fusc(n));
    }
}