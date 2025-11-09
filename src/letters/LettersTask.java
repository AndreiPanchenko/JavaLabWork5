package letters;

import main.InputChecker;
import java.util.*;
import java.io.*;

// Задание 5.7: Подсчет уникальных букв в тексте
// Используется Set для хранения уникальных символов
public class LettersTask {

  //Основной метод выполнения задания

  public void execute() {
    System.out.println("=== Задание 5.7: Подсчет уникальных букв (Set) ===");

    System.out.println("1 - Использовать готовый файл text.txt");
    System.out.println("2 - Ввести текст вручную");
    int choice = InputChecker.getIntInRange("Выберите вариант: ", 1, 2);

    String text = "";

    if (choice == 1) {
      text = readTextFromFile("text.txt");
    } else {
      text = InputChecker.getString("Введите текст: ");
    }

    if (text.isEmpty()) {
      System.out.println("Текст для анализа отсутствует.");
      return;
    }

    analyzeText(text);
  }

  // Читает текст из файла

  private String readTextFromFile(String filename) {
    StringBuilder content = new StringBuilder();

    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = reader.readLine()) != null) {
        content.append(line).append("\n");
      }
      System.out.println("Текст прочитан из файла.");
    } catch (IOException e) {
      System.out.println("Ошибка чтения файла: " + e.getMessage());
      System.out.println("Используем тестовый текст...");
      content.append("Шаблоны проектирования - это проверенные решения распространенных проблем в программировании.");
    }

    return content.toString();
  }

  // Анализирует текст и подсчитывает уникальные буквы

  private void analyzeText(String text) {
    // Используем HashSet для хранения уникальных букв
    Set<Character> uniqueLetters = new HashSet<>();
    Set<Character> uniqueCyrillicLetters = new HashSet<>();

    // Проходим по всем символам текста
    for (char c : text.toCharArray()) {
      if (Character.isLetter(c)) {
        uniqueLetters.add(Character.toLowerCase(c));
        // Проверяем, является ли буква кириллической
        if (isCyrillic(c)) {
          uniqueCyrillicLetters.add(Character.toLowerCase(c));
        }
      }
    }

    // Выводим результаты анализа
    displayResults(uniqueLetters, uniqueCyrillicLetters);
  }

  // Проверяет, является ли символ буквой кириллицы

  private boolean isCyrillic(char c) {
    return Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CYRILLIC;
  }

  // Выводит результаты анализа текста

  private void displayResults(Set<Character> uniqueLetters, Set<Character> uniqueCyrillicLetters) {
    System.out.println("=== РЕЗУЛЬТАТЫ АНАЛИЗА ===");
    System.out.println("Общее количество уникальных букв: " + uniqueLetters.size());
    System.out.println("Количество уникальных кириллических букв: " + uniqueCyrillicLetters.size());

    // Выводим отсортированный список кириллических букв
    List<Character> sortedCyrillicLetters = new ArrayList<>(uniqueCyrillicLetters);
    Collections.sort(sortedCyrillicLetters);

    System.out.println("Уникальные кириллические буквы: " + sortedCyrillicLetters);

    // Выводим все уникальные буквы
    List<Character> sortedAllLetters = new ArrayList<>(uniqueLetters);
    Collections.sort(sortedAllLetters);
    System.out.println("Все уникальные буквы: " + sortedAllLetters);
  }
}