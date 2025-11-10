package fraction;

// Интерфейс для работы с дробями
// Определяет основные операции, которые должна поддерживать дробь

public interface FractionInterface {
  double getDecimalValue();
  void setNumerator(int numerator);
  void setDenominator(int denominator);
}