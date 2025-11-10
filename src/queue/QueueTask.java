package queue;

import main.InputChecker;
import java.util.*;

//Задание 6.2: Построение очереди по списку

public class QueueTask {

  // Основной метод выполнения задания

  public void execute() {
    System.out.println("=== Задание 6.2: Построение очереди ===");

    // Создание списка с вводом от пользователя
    List<Integer> list = createList();

    System.out.println("Исходный список: " + list);

    // Построение очереди по заданному алгоритму
    Queue<Integer> queue = buildQueue(list);

    System.out.println("Построенная очередь: " + queue);

  }

  // Создает список с элементами, введенными пользователем

  private List<Integer> createList() {
    List<Integer> list = new ArrayList<>();
    int n = InputChecker.getIntInRange("Введите количество элементов в списке: ", 1, 10);

    System.out.println("Введите элементы списка:");
    for (int i = 0; i < n; i++) {
      int value = InputChecker.getInt("Элемент " + (i + 1) + ": ");
      list.add(value);
    }

    return list;
  }

  // Строит очередь по алгоритму: исходный список + обратный список

  private Queue<Integer> buildQueue(List<Integer> list) {
    Queue<Integer> queue = new LinkedList<>();

    // Добавляем элементы в прямом порядке
    for (Integer item : list) {
      queue.offer(item);
    }

    // Добавляем элементы в обратном порядке
    List<Integer> reversed = new ArrayList<>(list);
    Collections.reverse(reversed);
    for (Integer item : reversed) {
      queue.offer(item);
    }

    return queue;
  }

}