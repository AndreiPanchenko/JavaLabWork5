package cat;

//Класс Funs с методом meowsCare согласно примеру из задания

public class Funs {

  // Метод meowsCare для одного мяукающего объекта
  // Соответствует примеру: Funs.meowsCare(m)
  // Вызывает мяуканье 5 раз (как показано в примере вывода)
  public static void meowsCare(Meowable meowable) {
    for (int i = 0; i < 5; i++) {
      meowable.meow();
    }
  }
}