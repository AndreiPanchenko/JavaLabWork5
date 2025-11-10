package list;

import main.InputChecker;
import java.util.*;

public class ListTask {

  public void execute() {
    System.out.println("=== Задание 3.3: Вставка упорядоченных списков ===");

    // Создание двух списков с вводом от пользователя
    List<Integer> list1 = createList("L1");
    List<Integer> list2 = createList("L2");

    // Сортировка списков для обеспечения упорядоченности
    Collections.sort(list1);
    Collections.sort(list2);

    System.out.println("Исходные данные:");
    System.out.println("L1 (отсортирован): " + list1);
    System.out.println("L2 (отсортирован): " + list2);

    // Вставка L2 в L1 с сохранением упорядоченности
    List<Integer> mergedList = mergeSortedLists(list1, list2);

    System.out.println("Результат после вставки:");
    System.out.println("Объединенный список: " + mergedList);

  }

  // Создает список с элементами, введенными пользователем

  private List<Integer> createList(String listName) {
    List<Integer> list = new ArrayList<>();

    System.out.println("--- Заполнение списка " + listName + " ---");
    int n = InputChecker.getIntInRange("Введите количество элементов в " + listName + ": ", 0, 10);

    System.out.println("Введите элементы списка " + listName + ":");
    for (int i = 0; i < n; i++) {
      int value = InputChecker.getInt("Элемент " + (i + 1) + ": ");
      list.add(value);
    }

    return list;
  }

  // Объединяет два отсортированных списка в один отсортированный список

  private List<Integer> mergeSortedLists(List<Integer> list1, List<Integer> list2) {
    List<Integer> result = new ArrayList<>(list1);
    result.addAll(list2);      // Добавляем все элементы из L2
    Collections.sort(result);  // Сортируем объединенный список
    return result;
  }

}