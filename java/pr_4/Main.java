import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class Main {

    private static final String HISTORY_FILE = "history.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Java калькулятор. Введите выражение или 'история' для открытия истории, или 'выход' — чтобы выйти.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("выход")) break;
            if (input.equalsIgnoreCase("история")) {
                showHistory();
                continue;
            }

            try {
                double result = evaluateExpression(input);
                System.out.println("Результат: " + result);
                saveToHistory(input, result);
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        scanner.close();
    }

    // Вывод истории
    private static void showHistory() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(HISTORY_FILE));
            if (lines.isEmpty()) {
                System.out.println("История пуста.");
            } else {
                System.out.println("История:");
                lines.forEach(System.out::println);
            }
        } catch (IOException e) {
            System.out.println("Не удалось прочитать историю.");
        }
    }

    // Сохранение выражения и результата в файл
    private static void saveToHistory(String expression, double result) {
        try (FileWriter fw = new FileWriter(HISTORY_FILE, true)) {
            fw.write(expression + " = " + result + "\n");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в историю.");
        }
    }

    // Оценка выражения
    public static double evaluateExpression(String expr) throws Exception {
        expr = expr.replaceAll("\\s+", ""); // убрать пробелы
        expr = preprocessAbs(expr);         // обработка |x|
        expr = expr.replaceAll("//", "÷");  // заменить // на символ ÷

        List<String> tokens = tokenize(expr);
        List<String> postfix = toPostfix(tokens);
        return evaluatePostfix(postfix);
    }

    // Обработка |x| → abs(x)
    private static String preprocessAbs(String expr) {
        while (expr.contains("|")) {
            int start = expr.indexOf("|");
            int end = expr.indexOf("|", start + 1);
            if (end == -1) throw new IllegalArgumentException("Непарный модуль (|)");
            String inside = expr.substring(start + 1, end);
            expr = expr.substring(0, start) + "abs(" + inside + ")" + expr.substring(end + 1);
        }
        return expr;
    }

    // Токенизация выражения
    private static List<String> tokenize(String expr) {
        List<String> tokens = new ArrayList<>();
        Matcher matcher = Pattern.compile(
                "(\\d+\\.?\\d*|\\.\\d+|[+\\-*/%^()÷]|abs|\\b)").matcher(expr);
        while (matcher.find()) {
            if (!matcher.group().isEmpty())
                tokens.add(matcher.group());
        }
        return tokens;
    }

    // Приоритет операторов
    private static int precedence(String op) {
        return switch (op) {
            case "abs" -> 5;
            case "^" -> 4;
            case "*", "/", "%", "÷" -> 3;
            case "+", "-" -> 2;
            default -> 0;
        };
    }

    // Обработка инфиксной записи в постфиксную (Reverse Polish Notation)
    private static List<String> toPostfix(List<String> tokens) {
        List<String> output = new ArrayList<>();
        Stack<String> ops = new Stack<>();

        for (String token : tokens) {
            if (token.matches("\\d+\\.?\\d*|\\.\\d+")) {
                output.add(token);
            } else if (token.equals("(")) {
                ops.push(token);
            } else if (token.equals(")")) {
                while (!ops.peek().equals("(")) {
                    output.add(ops.pop());
                }
                ops.pop(); // Удаляем (
                if (!ops.isEmpty() && ops.peek().equals("abs")) {
                    output.add(ops.pop());
                }
            } else if (token.equals("abs")) {
                ops.push(token);
            } else { // оператор
                while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(token)) {
                    output.add(ops.pop());
                }
                ops.push(token);
            }
        }

        while (!ops.isEmpty()) output.add(ops.pop());

        return output;
    }

    // Вычисление постфиксного выражения
    private static double evaluatePostfix(List<String> postfix) {
        Stack<Double> stack = new Stack<>();

        for (String token : postfix) {
            switch (token) {
                case "+" -> stack.push(stack.pop() + stack.pop());
                case "-" -> {
                    double b = stack.pop(), a = stack.pop();
                    stack.push(a - b);
                }
                case "*" -> stack.push(stack.pop() * stack.pop());
                case "/" -> {
                    double b = stack.pop(), a = stack.pop();
                    stack.push(a / b);
                }
                case "÷" -> {
                    double b = stack.pop(), a = stack.pop();
                    stack.push(Math.floor(a / b)); // Целое деление
                }
                case "%" -> {
                    double b = stack.pop(), a = stack.pop();
                    stack.push(a % b);
                }
                case "^" -> {
                    double b = stack.pop(), a = stack.pop();
                    stack.push(Math.pow(a, b));
                }
                case "abs" -> stack.push(Math.abs(stack.pop()));
                default -> stack.push(Double.parseDouble(token));
            }
        }

        return stack.pop();
    }
}