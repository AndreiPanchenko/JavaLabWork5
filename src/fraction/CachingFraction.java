// CachingFraction.java
package fraction;

// Декоратор для дроби с кэшированием вычислений
// Шаблон Decorator - добавляет функциональность кэширования
// без изменения исходного класса Fraction
public class CachingFraction implements FractionInterface {
  private FractionInterface fraction; // Декорируемая дробь
  private Double cachedValue;         // Кэшированное значение

  // Конструктор декоратора
  public CachingFraction(FractionInterface fraction) {
    this.fraction = fraction;
    this.cachedValue = null;
  }

  // Возвращает десятичное значение с кэшированием
  @Override
  public double getDecimalValue() {
    if (cachedValue == null) {
      // Вычисляем значение и сохраняем в кэш
      cachedValue = fraction.getDecimalValue();
      System.out.println("Значение закэшировано: " + cachedValue);
    } else {
      // Используем значение из кэша
      System.out.println("Использовано кэшированное значение: " + cachedValue);
    }
    return cachedValue;
  }

  // Устанавливает новый числитель (сбрасывает кэш)
  @Override
  public void setNumerator(int numerator) {
    fraction.setNumerator(numerator);
    cachedValue = null; // Сбрасываем кэш при изменении
    System.out.println("Кэш сброшен (изменен числитель)");
  }

  // Устанавливает новый знаменатель (сбрасывает кэш)
  @Override
  public void setDenominator(int denominator) {
    fraction.setDenominator(denominator);
    cachedValue = null; // Сбрасываем кэш при изменении
    System.out.println("Кэш сброшен (изменен знаменатель)");
  }

  // Возвращает строковое представление исходной дроби
  @Override
  public String toString() {
    return fraction.toString() + " [с кэшированием]";
  }

  // Дополнительный метод для проверки статуса кэша
  public boolean isCached() {
    return cachedValue != null;
  }

  // Дополнительный метод для получения кэшированного значения (без вычисления)
  public Double getCachedValue() {
    return cachedValue;
  }
}