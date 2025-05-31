import java.util.*;

public class Main {

    public static List<Integer> generateSequence(int n) {
        List<Integer> result = new ArrayList<>();
        TreeSet<Integer> queue = new TreeSet<>(); // автоматическая сортировка + уникальность

        queue.add(1);

        while (result.size() < n) {
            int current = queue.pollFirst(); // Наименьший элемент
            result.add(current);

            queue.add(2 * current + 1);
            queue.add(3 * current + 1);
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов последовательности (n): ");
        int n = scanner.nextInt();

        List<Integer> sequence = generateSequence(n);
        System.out.println("Последовательность u:");
        System.out.println(sequence);
    }
}