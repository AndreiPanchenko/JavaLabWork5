package fraction;

// Класс дроби БЕЗ кэширования
// Реализует интерфейс FractionInterface
public class Fraction implements FractionInterface {
  private int numerator;      // Числитель дроби
  private int denominator;    // Знаменатель дроби

  // Конструктор дроби
  public Fraction(int numerator, int denominator) {
    setNumerator(numerator);
    setDenominator(denominator);
  }

  // Возвращает десятичное значение дроби БЕЗ кэширования
  @Override
  public double getDecimalValue() {
    double value = (double) numerator / denominator;
    System.out.println("Вычислено новое значение: " + value);
    return value;
  }

  // Устанавливает новый числитель
  @Override
  public void setNumerator(int numerator) {
    this.numerator = numerator;
  }

  // Устанавливает новый знаменатель
  @Override
  public void setDenominator(int denominator) {
    if (denominator == 0) {
      throw new IllegalArgumentException("Знаменатель не может быть нулем");
    }
    // Если знаменатель отрицательный, переносим знак в числитель
    if (denominator < 0) {
      this.numerator = -numerator;
      this.denominator = -denominator;
    } else {
      this.denominator = denominator;
    }
  }

  @Override
  public String toString() {
    return numerator + "/" + denominator;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Fraction fraction = (Fraction) obj;
    return numerator == fraction.numerator && denominator == fraction.denominator;
  }

  @Override
  public int hashCode() {
    return 31 * numerator + denominator;
  }
}