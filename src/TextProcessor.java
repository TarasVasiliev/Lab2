import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessor {

    public static void main(String[] args) {
        // Початкові налаштування
        String text = "Чи ти вже читав нову книгу, яку я тобі рекомендував? Які фільми ти плануєш подивитися в ці вихідні? Сьогодні надзвичайно гарна погода. " +
                      "Чи подобається тобі гуляти на свіжому повітрі? Вечірка в суботу буде цікавою.";
        int wordLength = 2;  // Задана довжина слова для пошуку

        try {
            // Викликаємо метод обробки тексту
            processQuestions(text, wordLength);
        } catch (Exception e) {
            System.out.println("Виникла помилка: " + e.getMessage());
        }
    }


    public static void processQuestions(String text, int wordLength) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Текст не може бути порожнім або null.");
        }
        if (wordLength <= 0) {
            throw new IllegalArgumentException("Довжина слова має бути більшою за нуль.");
        }

        // Шаблон для розділення тексту на речення та знаходження питальних речень
        Pattern questionPattern = Pattern.compile("[^.!?]*\\?");

        Matcher matcher = questionPattern.matcher(text);
        HashSet<String> uniqueWords = new HashSet<>();

        // Переглядаємо всі речення
        while (matcher.find()) {
            String питання = matcher.group().trim();

            // Використовуємо StringBuilder для обробки кожного речення
            StringBuilder sentenceBuilder = new StringBuilder(питання);

            // Видаляємо зайві пробіли
            while (sentenceBuilder.length() > 0 && Character.isWhitespace(sentenceBuilder.charAt(0))) {
                sentenceBuilder.deleteCharAt(0);
            }
            while (sentenceBuilder.length() > 0 && Character.isWhitespace(sentenceBuilder.charAt(sentenceBuilder.length() - 1))) {
                sentenceBuilder.deleteCharAt(sentenceBuilder.length() - 1);
            }

            // Розбиваємо речення на слова
            String[] words = sentenceBuilder.toString().split("\\s+"); // Змінено на \s+ для поділу лише пробілами

            // Перевіряємо довжину кожного слова та додаємо унікальні слова до HashSet
            for (String word : words) {
                if (word.length() == wordLength) {
                    uniqueWords.add(word.toLowerCase());
                }
            }
        }

        // Виводимо результати
        if (uniqueWords.isEmpty()) {
            System.out.println("У питальних реченнях не знайдено унікальних слів довжиною " + wordLength + " символи.");
        } else {
            System.out.println("Унікальні слова довжиною " + wordLength + " символи у питальних реченнях:");
            for (String word : uniqueWords) {
                System.out.println(word + ";");
            }
        }
    }
}
