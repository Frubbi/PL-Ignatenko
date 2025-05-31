import java.util.*;

public class Main {

    // Метод генерации отсортированного массива-змейки
    public static int[] snailSort(int[][] matrix) {
        int n = matrix.length;
        List<Integer> result = new ArrayList<>();

        int top = 0, bottom = n - 1;
        int left = 0, right = n - 1;

        while (top <= bottom && left <= right) {
            // Вправо
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;

            // Вниз
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            // Влево
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }

            // Вверх
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }

        // Преобразуем список в массив
        return result.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество чисел (N): ");
        int N = scanner.nextInt();

        int size = (int) Math.ceil(Math.sqrt(N)); // Определяем размер квадратной матрицы
        int total = size * size;

        // Генерация случайных чисел
        Random rand = new Random();
        int[] numbers = new int[total];
        for (int i = 0; i < N; i++) {
            numbers[i] = rand.nextInt(100); // Генерация чисел от 0 до 99
        }
        for (int i = N; i < total; i++) {
            numbers[i] = 0; // Дополняем нулями, если нужно
        }

        Arrays.sort(numbers); // Сортировка массива

        // Заполнение матрицы отсортированными числами
        int[][] matrix = new int[size][size];
        int index = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = numbers[index++];
            }
        }

        // Вывод матрицы
        System.out.println("Матрица:");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

        // Вывод змейки
        int[] result = snailSort(matrix);
        System.out.println("Змейка:");
        System.out.println(Arrays.toString(result));
    }
}