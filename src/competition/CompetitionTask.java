package competition;

import main.InputChecker;
import java.util.*;
import java.io.*;

// Задание 4.3: Результаты соревнований по школьному многоборью
// Обработка результатов и определение победителей с использованием Map

public class CompetitionTask {

  // Основной метод выполнения задания

  public void execute() {
    System.out.println("=== Задание 4.3: Результаты соревнований (Map) ===");

    System.out.println("1 - Использовать готовый файл competitions.txt");
    System.out.println("2 - Ввести данные вручную");
    int choice = InputChecker.getIntInRange("Выберите вариант: ", 1, 2);

    List<StudentResult> results = new ArrayList<>();

    if (choice == 1) {
      results = readFromFile("competitions.txt");
    } else {
      results = enterManually();
    }

    if (results.isEmpty()) {
      System.out.println("Нет данных для обработки.");
      return;
    }

    // Обработка и вывод результатов
    processAndDisplayResults(results);
  }

  // Читает результаты из файла

  private List<StudentResult> readFromFile(String filename) {
    List<StudentResult> results = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;
      int lineCount = 0;
      while ((line = reader.readLine()) != null && lineCount < 100) {
        lineCount++;
        try {
          String[] parts = line.split("\\s+");
          if (parts.length >= 6) {
            String lastName = parts[0];
            String firstName = parts[1];

            // Проверка длины фамилии (не более 20 символов)
            if (lastName.length() > 20) {
              System.out.println("Предупреждение: фамилия '" + lastName + "' превышает 20 символов в строке: " + line);
              lastName = lastName.substring(0, 20);
            }

            // Проверка длины имени (не более 15 символов)
            if (firstName.length() > 15) {
              System.out.println("Предупреждение: имя '" + firstName + "' превышает 15 символов в строке: " + line);
              firstName = firstName.substring(0, 15);
            }

            int[] scores = new int[4];
            boolean validScores = true;

            // Проверка баллов (от 0 до 10)
            for (int i = 0; i < 4; i++) {
              int score = Integer.parseInt(parts[2 + i]);
              if (score < 0 || score > 10) {
                System.out.println("Ошибка: балл " + score + " вне диапазона 0-10 в строке: " + line);
                validScores = false;
                break;
              }
              scores[i] = score;
            }

            if (validScores) {
              results.add(new StudentResult(lastName, firstName, scores));
            }
          } else {
            System.out.println("Ошибка: недостаточно данных в строке: " + line);
          }
        } catch (NumberFormatException e) {
          System.out.println("Ошибка в формате чисел в строке: " + line);
        }
      }

      if (lineCount >= 100) {
        System.out.println("Внимание: обработано максимальное количество участников (100)");
      }

      System.out.println("Прочитано " + results.size() + " корректных записей из файла.");
    } catch (IOException e) {
      System.out.println("Ошибка чтения файла: " + e.getMessage());
      System.out.println("Создаем тестовые данные...");
      results = createTestData();
    }

    return results;
  }

  // Ввод результатов вручную с клавиатуры

  private List<StudentResult> enterManually() {
    List<StudentResult> results = new ArrayList<>();
    int n = InputChecker.getIntInRange("Введите количество учеников (1-100): ", 1, 100);

    for (int i = 0; i < n; i++) {
      System.out.println("\n--- Ученик " + (i + 1) + " ---");

      // Ввод и проверка фамилии (не более 20 символов)
      String lastName;
      while (true) {
        lastName = InputChecker.getNonEmptyString("Фамилия: ");
        if (lastName.length() > 20) {
          System.out.println("Ошибка: фамилия не должна превышать 20 символов");
        } else {
          break;
        }
      }

      // Ввод и проверка имени (не более 15 символов)
      String firstName;
      while (true) {
        firstName = InputChecker.getNonEmptyString("Имя: ");
        if (firstName.length() > 15) {
          System.out.println("Ошибка: имя не должно превышать 15 символов");
        } else {
          break;
        }
      }

      int[] scores = new int[4];
      System.out.println("Введите баллы по 4 видам спорта (0-10):");
      for (int j = 0; j < 4; j++) {
        scores[j] = InputChecker.getIntInRange("Вид " + (j + 1) + ": ", 0, 10);
      }

      results.add(new StudentResult(lastName, firstName, scores));
    }
    return results;
  }

  // Обрабатывает результаты и выводит лучших участников

  private void processAndDisplayResults(List<StudentResult> results) {
    // Используем TreeMap с обратным порядком для автоматической сортировки по убыванию баллов
    Map<Integer, List<StudentResult>> resultsByScore = new TreeMap<>(Collections.reverseOrder());

    // Группируем учеников по сумме баллов
    for (StudentResult result : results) {
      int totalScore = result.getTotalScore();
      if (!resultsByScore.containsKey(totalScore)) {
        resultsByScore.put(totalScore, new ArrayList<>());
      }
      resultsByScore.get(totalScore).add(result);
    }

    // Выводим трех лучших участников (и всех с одинаковыми баллами)
    System.out.println("=== РЕЗУЛЬТАТЫ СОРЕВНОВАНИЙ ===");
    displayTopResults(resultsByScore);
  }

  // Выводит трех лучших участников и всех с одинаковыми баллами

  private void displayTopResults(Map<Integer, List<StudentResult>> resultsByScore) {
    int count = 0;
    int thirdBestScore = -1;

    for (Map.Entry<Integer, List<StudentResult>> entry : resultsByScore.entrySet()) {
      if (count < 3) {
        System.out.println("--- " + (count + 1) + " место ---");
        thirdBestScore = entry.getKey();
      } else if (entry.getKey() < thirdBestScore) {
        break; // Останавливаемся после третьего лучшего результата
      }

      // Выводим всех учеников с текущим баллом
      for (StudentResult result : entry.getValue()) {
        System.out.printf("%s %s - %d баллов (баллы: %s)%n",
            result.getLastName(), result.getFirstName(),
            result.getTotalScore(), Arrays.toString(result.getScores()));
      }
      count += entry.getValue().size();
    }
  }

  private List<StudentResult> createTestData() {
    return Arrays.asList(
        new StudentResult("Иванова", "Мария", new int[]{5, 8, 6, 3}),
        new StudentResult("Петров", "Сергей", new int[]{9, 9, 5, 7}),
        new StudentResult("Сидорова", "Анна", new int[]{8, 7, 9, 6}),
        new StudentResult("Козлов", "Дмитрий", new int[]{7, 6, 8, 9}),
        new StudentResult("Николаева", "Ольга", new int[]{9, 9, 9, 9})
    );
  }
}