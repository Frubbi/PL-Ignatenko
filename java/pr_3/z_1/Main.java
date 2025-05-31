public class Main {

    // Массивы для соответствия арабских и римских чисел
    private static final int[] ARABIC_VALUES = {
        1000, 900, 500, 400,
        100, 90, 50, 40,
        10, 9, 5, 4, 1
    };

    private static final String[] ROMAN_NUMERALS = {
        "M", "CM", "D", "CD",
        "C", "XC", "L", "XL",
        "X", "IX", "V", "IV", "I"
    };

    public static String toRoman(int number) {
        if (number <= 0 || number >= 10000) {
            throw new IllegalArgumentException("Число должно быть в диапазоне от 1 до 9999.");
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < ARABIC_VALUES.length; i++) {
            while (number >= ARABIC_VALUES[i]) {
                result.append(ROMAN_NUMERALS[i]);
                number -= ARABIC_VALUES[i];
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        int year = 2023;
        String romanYear = toRoman(year);
        System.out.println("Год " + year + " в римских цифрах: " + romanYear);
    }
}