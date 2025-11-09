// src/main/Main.java
package main;

import fraction.FractionTask;
import cat.CatTask;
import list.ListTask;
import competition.CompetitionTask;
import letters.LettersTask;
import queue.QueueTask;
import point.PointTask;
import name.NameTask;

import java.util.*;

//Главный класс лабораторной работы №5

public class Main {

  public static void main(String[] args) {
    try {
      runMenu();
    } finally {
      InputChecker.close();
    }
  }

  // Основное меню программы

  private static void runMenu() {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("=".repeat(50));
      System.out.println("          ЛАБОРАТОРНАЯ РАБОТА №5");
      System.out.println("=".repeat(50));
      System.out.println("1. Дроби с кэшированием (Шаблон Proxy)");
      System.out.println("2. Подсчет мяуканий (Шаблон Decorator)");
      System.out.println("3. Вставка упорядоченных списков (Collections)");
      System.out.println("4. Результаты соревнований (Map)");
      System.out.println("5. Подсчет уникальных букв (Set)");
      System.out.println("6. Построение очереди (Queue)");
      System.out.println("7. Обработка точек и ломаной (Stream API)");
      System.out.println("8. Обработка файла с именами (Stream API)");
      System.out.println("0. Выход");
      System.out.println("-".repeat(50));

      int choice = InputChecker.getIntInRange("Выберите задание: ", 0, 8);

      switch (choice) {
        case 1:
          // Задание 1: Дроби с кэшированием
          new FractionTask().execute();
          break;
        case 2:
          // Задание 2: Подсчет мяуканий кота
          new CatTask().execute();
          break;
        case 3:
          // Задание 3: Вставка упорядоченных списков
          new ListTask().execute();
          break;
        case 4:
          // Задание 4: Результаты соревнований
          new CompetitionTask().execute();
          break;
        case 5:
          // Задание 5: Подсчет уникальных букв
          new LettersTask().execute();
          break;
        case 6:
          // Задание 6: Построение очереди
          new QueueTask().execute();
          break;
        case 7:
          // Задание 7: Обработка точек и ломаной
          new PointTask().execute();
          break;
        case 8:
          // Задание 8: Обработка файла с именами
          new NameTask().execute();
          break;
        case 0:
          return;
        default:
          System.out.println("Неверный выбор!");
      }

      // Пауза перед следующим выбором
      System.out.println("Нажмите Enter чтобы продолжить...");
      scanner.nextLine();
    }
  }
}