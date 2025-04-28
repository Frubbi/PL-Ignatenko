import java.util.*;
import java.lang.*;
import java.nio.file.*;
import java.io.*;
import java.net.URL;

class Main
{
	 private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        String encryptedText = "Wklv lv d whvw phvvdjh";
        int shift = 3;
        String decryptedText = decrypt(encryptedText, shift);
        System.out.println("Расшифрованный текст: " + decryptedText);

        String dictionaryText = loadTextFromUrl("https://fish-text.ru");
        System.out.println("Текст из словаря: " + dictionaryText);
    }

    public static String decrypt(String text, int shift) {
        StringBuilder decrypted = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                int originalPosition = character - base;
                int newPosition = (originalPosition - shift + 26) % 26;
                decrypted.append((char) (base + newPosition));
            } else {
                decrypted.append(character); 
            }
        }

        return decrypted.toString();
    }

    private static String loadTextFromUrl(String urlString) {
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(urlString);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine).append("\n");
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }
}