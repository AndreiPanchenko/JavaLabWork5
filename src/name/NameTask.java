package name;

import main.InputChecker;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

// Задание 7.2: Обработка файла с именами с использованием Stream API

public class NameTask {

  // Основной метод выполнения задания

  public void execute() {
    System.out.println("=== Задание 7.2: Обработка файла с именами (Stream API) ===");

    System.out.println("1 - Использовать готовый файл names.txt");
    System.out.println("2 - Ввести данные вручную");
    int choice = InputChecker.getIntInRange("Выберите вариант: ", 1, 2);

    Map<Integer, List<String>> result;

    if (choice == 1) {
      result = processFileWithStream("names.txt");
    } else {
      result = processManualInput();
    }

    // Вывод результатов
    displayResults(result);
  }

  // Обрабатывает файл с именами с использованием Stream API

  private Map<Integer, List<String>> processFileWithStream(String filename) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      return reader.lines()
          .map(line -> line.split(":"))                    // Разделяем по двоеточию
          .filter(parts -> parts.length == 2)             // Убираем строки без номера
          .map(parts -> new String[]{capitalize(parts[0].trim()), parts[1].trim()})
          .filter(parts -> !parts[1].isEmpty())           // Убираем людей без номеров
          .collect(Collectors.groupingBy(
              parts -> Integer.parseInt(parts[1]),        // Группируем по номеру
              Collectors.mapping(parts -> parts[0], Collectors.toList())
          ));
    } catch (IOException e) {
      System.out.println("Ошибка чтения файла: " + e.getMessage());
      System.out.println("Используем тестовые данные...");
      return createTestData();
    }
  }

  // Обрабатывает ручной ввод данных

  private Map<Integer, List<String>> processManualInput() {
    List<String[]> data = new ArrayList<>();
    int n = InputChecker.getIntInRange("Введите количество записей: ", 1, 10);

    for (int i = 0; i < n; i++) {
      System.out.println("--- Запись " + (i + 1) + " ---");
      String name = InputChecker.getNonEmptyString("Имя: ");
      String numberStr = InputChecker.getString("Номер (оставьте пустым если нет номера): ");

      if (!numberStr.trim().isEmpty()) {
        try {
          int number = Integer.parseInt(numberStr.trim());
          data.add(new String[]{capitalize(name), String.valueOf(number)});
        } catch (NumberFormatException e) {
          System.out.println("Некорректный номер, запись пропущена.");
        }
      }
    }

    // Используем Stream API для группировки
    return data.stream()
        .collect(Collectors.groupingBy(
            parts -> Integer.parseInt(parts[1]),
            Collectors.mapping(parts -> parts[0], Collectors.toList())
        ));
  }

  // Приводит имя к правильному регистру: первая буква заглавная, остальные строчные

  private String capitalize(String name) {
    if (name == null || name.isEmpty()) {
      return name;
    }
    return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
  }

  // Выводит результаты обработки

  private void displayResults(Map<Integer, List<String>> result) {
    System.out.println("=== РЕЗУЛЬТАТ ГРУППИРОВКИ ===");
    System.out.println("Сырой вывод: " + result);

    System.out.println("--- Форматированный вывод ---");
    for (Map.Entry<Integer, List<String>> entry : result.entrySet()) {
      System.out.println("Номер " + entry.getKey() + ": " + entry.getValue());
    }

//    // Дополнительная статистика
//    System.out.println("--- Статистика ---");
//    System.out.println("Всего номеров: " + result.size());
//    int totalPeople = result.values().stream().mapToInt(List::size).sum();
//    System.out.println("Всего людей: " + totalPeople);
  }

  // Создает тестовые данные для демонстрации

  private Map<Integer, List<String>> createTestData() {
    Map<Integer, List<String>> testData = new HashMap<>();
    testData.put(5, Arrays.asList("Вася", "Аня"));
    testData.put(3, Arrays.asList("Петя"));
    testData.put(7, Arrays.asList("Оля"));
    return testData;
  }
}