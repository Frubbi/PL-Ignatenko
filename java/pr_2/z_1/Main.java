import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner scanner = new Scanner(System.in);

        System.out.print("Введите строку: ");
        String text = scanner.nextLine();
        System.out.print("Введите сдвиг: ");
        int shift = scanner.nextInt();
        System.out.print("Введите направление (влево/вправо): ");
        String direction = scanner.next();

        System.out.println("Результат: " + caesar(text, shift, direction));

	}
	public static String caesar(String text, int shift, String direction) {
        if (direction.equalsIgnoreCase("влево")) shift = -shift;
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'А' : 'а';
                result.append((char) (base + (c - base + shift + 26) % 26));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
	
}