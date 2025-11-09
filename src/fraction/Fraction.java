package fraction;

// Класс дроби с кэшированием вычислений
// Шаблон Proxy в упрощенной форме - кэш встроен в класс

public class Fraction implements FractionInterface {
  private int numerator;      // Числитель дроби
  private int denominator;    // Знаменатель дроби (не может быть 0)
  private Double cachedValue; // Кэшированное десятичное значение

  // Конструктор дроби

  public Fraction(int numerator, int denominator) {
    setNumerator(numerator);
    setDenominator(denominator);
  }

  // Возвращает десятичное значение дроби
  // Использует кэширование
  @Override
  public double getDecimalValue() {
    if (cachedValue == null) {
      // Вычисляем значение и сохраняем в кэш
      cachedValue = (double) numerator / denominator;
      System.out.println("Вычислено новое значение: " + cachedValue);
    } else {
      // Используем значение из кэша
      System.out.println("Использовано кэшированное значение: " + cachedValue);
    }
    return cachedValue;
  }

  // Устанавливает новый числитель
  // При изменении сбрасывает кэш
  @Override
  public void setNumerator(int numerator) {
    this.numerator = numerator;
    this.cachedValue = null; // Сбрасываем кэш при изменении
  }

  // Устанавливает новый знаменатель
  // Автоматически обрабатывает отрицательные значения
  // При изменении сбрасывает кэш

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
    this.cachedValue = null; // Сбрасываем кэш при изменении
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