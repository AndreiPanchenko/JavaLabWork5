package point;

import java.util.List;

// Класс ломаной линии
// Представляет последовательность точек, соединенных отрезками

public class Polyline {
  private List<Point> points;

  // Конструктор ломаной линии

  public Polyline(List<Point> points) {
    this.points = points;
  }

  // Позволяет получить список точек без возможности его изменения извне
  public List<Point> getPoints() {
    return points;
  }
  // Строковое представление ломаной линии

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Ломаная линия [");
    for (int i = 0; i < points.size(); i++) {
      sb.append(points.get(i));
      if (i < points.size() - 1) {
        sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString();
  }
}
