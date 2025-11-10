
package point;

// Класс линии на плоскости
// Представляет отрезок между двумя точками
public class Line {
  private Point start;
  private Point end;

  // Конструктор линии

  public Line(Point start, Point end) {
    this.start = start;
    this.end = end;
  }

  @Override
  public String toString() {
    return "Линия от " + start + " до " + end;
  }
}