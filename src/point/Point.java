
package point;

// Класс точки на плоскости
// Представляет точку с координатами X и Y

public class Point {
  private double x;
  private double y;

  // Конструктор точки

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  // Геттеры и сеттеры
  public double getX() { return x; }
  public double getY() { return y; }
  public void setY(double y) { this.y = y; }

  @Override
  public String toString() {
    return "{" + x + ";" + y + "}";
  }

  // Сравнение точек по координатам

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Point point = (Point) obj;
    return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
  }

  @Override
  public int hashCode() {
    return 31 * Double.hashCode(x) + Double.hashCode(y);
  }
}