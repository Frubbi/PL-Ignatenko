import java.util.*;

class Main {
	   public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод текста
        System.out.println("Введите текст:");
        String inputText = scanner.nextLine();

        // Обрабатываем текст
        String transformedText = transformText(inputText);

        // Выводим результат
        System.out.println("Изменённый текст:");
        System.out.println(transformedText);
    }

    // Метод для обработки текста
    public static String transformText(String inputText) {
        // Регулярное выражение для разделения текста на слова и знаки препинания
        String[] words = inputText.split("(?=[\\p{Punct}\\s])|(?<=[\\p{Punct}\\s])");

        StringBuilder result = new StringBuilder();

        for (String word : words) {
            // Если это слово (не знак препинания)
            if (word.matches("[a-zA-Zа-яА-ЯёЁ]+")) {
                // Перемещаем первую букву в конец и добавляем "ауч"
                String transformedWord = word.substring(1) + word.charAt(0) + "ауч";
                result.append(transformedWord);
            } else {
                // Просто добавляем знак препинания или пробел
                result.append(word);
            }
        }
        return result.toString();
    }
}