import java.util.HashMap;
import java.util.Map;

public class Main {

    // Карта соответствия римских символов их арабским значениям
    private static final Map<Character, Integer> ROMAN_MAP = new HashMap<>();

    static {
        ROMAN_MAP.put('I', 1);
        ROMAN_MAP.put('V', 5);
        ROMAN_MAP.put('X', 10);
        ROMAN_MAP.put('L', 50);
        ROMAN_MAP.put('C', 100);
        ROMAN_MAP.put('D', 500);
        ROMAN_MAP.put('M', 1000);
    }

    public static int toArabic(String roman) {
        int total = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            char c = roman.charAt(i);
            int value = ROMAN_MAP.getOrDefault(c, -1);

            if (value == -1) {
                throw new IllegalArgumentException("Недопустимый символ в римском числе: " + c);
            }

            if (value < prevValue) {
                total -= value; // вычитаем если меньше следующего
            } else {
                total += value;
            }

            prevValue = value;
        }

        if (total <= 0 || total >= 10000) {
            throw new IllegalArgumentException("Результат вне допустимого диапазона (1 < n < 10000): " + total);
        }

        return total;
    }

    public static void main(String[] args) {
        String romanYear = "MMXXIV";
        int arabicYear = toArabic(romanYear);
        System.out.println("Год " + romanYear + " в арабских цифрах: " + arabicYear);
    }
}