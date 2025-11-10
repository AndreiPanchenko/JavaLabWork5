package cat;

// Декоратор для кота с подсчетом мяуканий
// Шаблон Decorator - добавляет новую функциональность
// без изменения исходного класса Cat

public class CountingCat implements Meowable {
  private Cat cat;
  private int meowCount;

  // Конструктор декоратора

  public CountingCat(Cat cat) {
    this.cat = cat;
    this.meowCount = 0;
  }

  // Мяуканье с подсчетом
  // Делегирует вызов оригинальному коту и увеличивает счетчик

  @Override
  public void meow() {
    cat.meow();
    meowCount++;
  }

  // Возвращает количество выполненных мяуканий

  public int getMeowCount() {
    return meowCount;
  }

  @Override
  public String toString() {
    return cat.toString() + " (мяукнул: " + meowCount + " раз)";
  }
}