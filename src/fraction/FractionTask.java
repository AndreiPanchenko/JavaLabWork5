
package fraction;

import main.InputChecker;

// Задание 1: Демонстрация работы с дробями и кэшированием

public class FractionTask {

  public void execute() {
    System.out.println("=== Задание 1.1: Дроби с кэшированием (Шаблон Proxy) ===");

    System.out.println("--- Создание дроби ---");
    int numerator = InputChecker.getInt("Введите числитель: ");

    int denominator;
    do {
      denominator = InputChecker.getInt("Введите знаменатель: ");
      if (denominator == 0) {
        System.out.println("Ошибка! Знаменатель не может быть равен 0.");
      }
    } while (denominator == 0);

    Fraction fraction = new Fraction(numerator, denominator);
    System.out.println("Создана дробь: " + fraction);

    demonstrateCaching(fraction);

    changeFraction(fraction);

    compareFractions(fraction);

    demonstrateNegativeValues();
  }

  // Демонстрирует работу кэширования вычислений

  private void demonstrateCaching(Fraction fraction) {
    System.out.println("--- Демонстрация кэширования ---");

    System.out.println("Первое вычисление десятичного значения:");
    double value1 = fraction.getDecimalValue();
    System.out.println("Результат: " + value1);

    System.out.println("\nВторое вычисление (должен использоваться кэш):");
    double value2 = fraction.getDecimalValue();
    System.out.println("Результат: " + value2);
  }

  // Демонстрирует изменение дроби и сброс кэша

  private void changeFraction(Fraction fraction) {
    System.out.println("--- Изменение дроби ---");
    System.out.println("Текущая дробь: " + fraction);

    int newNumerator = InputChecker.getInt("Введите новый числитель: ");
    fraction.setNumerator(newNumerator);

    int newDenominator;
    do {
      newDenominator = InputChecker.getInt("Введите новый знаменатель: ");
      if (newDenominator == 0) {
        System.out.println("Ошибка! Знаменатель не может быть равен 0.");
      }
    } while (newDenominator == 0);
    fraction.setDenominator(newDenominator);

    System.out.println("Измененная дробь: " + fraction);

    System.out.println("Вычисление после изменения (кэш должен сброситься):");
    double value3 = fraction.getDecimalValue();
    System.out.println("Результат: " + value3);
  }

  // Демонстрирует сравнение дробей

  private void compareFractions(Fraction fraction) {
    System.out.println("--- Сравнение дробей ---");
    System.out.println("Создадим вторую дробь для сравнения:");

    int numerator2 = InputChecker.getInt("Введите числитель второй дроби: ");
    int denominator2;
    do {
      denominator2 = InputChecker.getInt("Введите знаменатель второй дроби: ");
      if (denominator2 == 0) {
        System.out.println("Ошибка! Знаменатель не может быть равен 0.");
      }
    } while (denominator2 == 0);

    Fraction fraction2 = new Fraction(numerator2, denominator2);
    System.out.println("Вторая дробь: " + fraction2);

    System.out.println("\nСравнение дробей:");
    System.out.println("Дробь 1: " + fraction);
    System.out.println("Дробь 2: " + fraction2);
    System.out.println("Дроби равны: " + fraction.equals(fraction2));
  }

  // Демонстрирует работу с отрицательными значениями

  private void demonstrateNegativeValues() {
    System.out.println("--- Работа с отрицательными значениями ---");
    System.out.println("Создадим дробь с отрицательным знаменателем:");

    Fraction negativeFraction = new Fraction(3, -4);
    System.out.println("Дробь с отрицательным знаменателем: " + negativeFraction);
    System.out.println("Десятичное значение: " + negativeFraction.getDecimalValue());
  }
}