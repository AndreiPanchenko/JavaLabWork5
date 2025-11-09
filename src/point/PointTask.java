// src/point/PointTask.java
package point;

import main.InputChecker;
import java.util.*;
import java.util.stream.Collectors;

//Задание 7.1: Обработка точек и ломаной с использованием Stream API

public class PointTask {

  // Основной метод выполнения задания

  public void execute() {
    System.out.println("=== Задание 7.1: Обработка точек и ломаной (Stream API) ===");

    // Создание списка точек с вводом от пользователя
    List<Point> points = createPoints();

    System.out.println("Исходные точки: " + points);

    // Обработка точек с использованием Stream API
    Polyline polyline = processPointsWithStream(points);

    System.out.println("Результирующая ломаная: " + polyline);

    // Дополнительная демонстрация - создание линий между точками
    demonstrateLines(polyline);
  }

  // Создает список точек с координатами, введенными пользователем

  private List<Point> createPoints() {
    List<Point> points = new ArrayList<>();
    int n = InputChecker.getIntInRange("Введите количество точек: ", 1, 10);

    System.out.println("Введите координаты точек:");
    for (int i = 0; i < n; i++) {
      System.out.println("--- Точка " + (i + 1) + " ---");
      double x = InputChecker.getDouble("X: ");
      double y = InputChecker.getDouble("Y: ");
      points.add(new Point(x, y));
    }

    return points;
  }

  // Обрабатывает точки с использованием Stream API согласно условию задания

  private Polyline processPointsWithStream(List<Point> points) {
    List<Point> processed = points.stream()
        .map(p -> new Point(p.getX(), Math.abs(p.getY()))) // Делаем Y положительными
        .distinct()                                        // Убираем дубликаты
        .sorted(Comparator.comparingDouble(Point::getX))   // Сортируем по X
        .collect(Collectors.toList());                     // Собираем в список

    return new Polyline(processed);
  }

  // Демонстрирует создание линий между точками ломаной

  private void demonstrateLines(Polyline polyline) {
    System.out.println("--- Линии ломаной ---");

    // Для демонстрации создаем список точек из ломаной
    List<Point> processedPoints = new ArrayList<>();
    for (String point : polyline.toString().replace("Ломаная линия [", "")
        .replace("]", "")
        .split(", ")) {
      String[] coords = point.replace("{", "").replace("}", "").split(";");
      double x = Double.parseDouble(coords[0]);
      double y = Double.parseDouble(coords[1]);
      processedPoints.add(new Point(x, y));
    }

    // Сортируем точки по X для правильного построения линий
    processedPoints.sort(Comparator.comparingDouble(Point::getX));

    // Создаем и выводим линии между последовательными точками
    for (int i = 0; i < processedPoints.size() - 1; i++) {
      Line line = new Line(processedPoints.get(i), processedPoints.get(i + 1));
      System.out.println(line);
    }
  }
}