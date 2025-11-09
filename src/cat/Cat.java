// src/cat/Cat.java
package cat;

// Класс кота с базовой функциональностью
// Реализует интерфейс Meowable
public class Cat implements Meowable {
  private String name; // Имя кота

  // Конструктор кота

  public Cat(String name) {
    this.name = name;
  }

  // Метод мяуканья - выводит сообщение в консоль

  @Override
  public void meow() {
    System.out.println(name + ": мяу!");
  }

  @Override
  public String toString() {
    return "кот: " + name;
  }

  // Геттер для имени кота
  public String getName() {
    return name;
  }
}