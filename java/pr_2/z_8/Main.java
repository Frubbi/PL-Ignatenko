import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
	public static void main(String[] args) {
        String input = "566574 100 99 68 86 180 90";
        String sortedWeights = sortWeightsByDigitSum(input);
        System.out.println(sortedWeights);
    }

    public static String sortWeightsByDigitSum(String input) {
        // Разделяем входную строку на массив строк
        String[] weights = input.split(" ");
        
        // Сортируем массив с использованием компаратора
        Arrays.sort(weights, new Comparator<String>() {
            @Override
            public int compare(String w1, String w2) {
                // Вычисляем сумму цифр для каждого веса
                int sum1 = digitSum(w1);
                int sum2 = digitSum(w2);
                
                // Сравниваем суммы цифр
                return Integer.compare(sum1, sum2);
            }
        });
        
        // Объединяем отсортированный массив обратно в строку
        return String.join(" ", weights);
    }

    // Метод для вычисления суммы цифр веса
    private static int digitSum(String weight) {
        int sum = 0;
        for (char digit : weight.toCharArray()) {
            sum += Character.getNumericValue(digit);
        }
        return sum;
    }
}