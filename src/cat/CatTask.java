package cat;

import main.InputChecker;

// Задание 2.1: Подсчет мяуканий

public class CatTask {

  public void execute() {
    System.out.println("=== Задание 2.1: Подсчет мяуканий (Шаблон Decorator) ===");

    // Ввод имени кота с клавиатуры
    String name = InputChecker.getNonEmptyString("Введите имя кота: ");

    System.out.println("--- Точное соответствие примеру из задания ---");

    // "Meowable m = ... // создаём кота"
    Cat cat = new Cat(name);
    CountingCat countingCat = new CountingCat(cat);
    Meowable m = countingCat; // Работа через интерфейс

    System.out.println("Создан: " + m);
    System.out.println("Количество мяуканий до вызова: " + countingCat.getMeowCount());

    // "Funs.meowsCare(m);"
    Funs.meowsCare(m);

    // "System.out.println(...) // кот мяукал 5 раз"
    System.out.println("Результат: кот мяукал " + countingCat.getMeowCount() + " раз");

    // Дополнительная демонстрация работы декоратора
    demonstrateDecoratorWork(countingCat);
  }

  // Демонстрирует дополнительную работу с декоратором

  private void demonstrateDecoratorWork(CountingCat countingCat) {
    System.out.println("--- Дополнительная демонстрация ---");

    System.out.println("Вызываем еще несколько мяуканий:");
    countingCat.meow();
    countingCat.meow();

    System.out.println("Общее количество мяуканий: " + countingCat.getMeowCount());
    System.out.println("Финальное состояние: " + countingCat);
  }
}