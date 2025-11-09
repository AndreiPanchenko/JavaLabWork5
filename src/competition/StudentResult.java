package competition;

import java.util.Arrays;

// Класс для хранения результатов ученика в соревнованиях

public class StudentResult {
  private String lastName;
  private String firstName;
  private int[] scores;

  // Конструктор результата ученика

  public StudentResult(String lastName, String firstName, int[] scores) {
    this.lastName = lastName;
    this.firstName = firstName;
    this.scores = scores;
  }

  // Вычисляет общую сумму баллов ученика

  public int getTotalScore() {
    int sum = 0;
    for (int score : scores) {
      sum += score;
    }
    return sum;
  }

  // Геттеры для доступа к полям
  public String getLastName() { return lastName; }
  public String getFirstName() { return firstName; }
  public int[] getScores() { return scores; }

  @Override
  public String toString() {
    return lastName + " " + firstName + " " + Arrays.toString(scores) +
        " (сумма: " + getTotalScore() + ")";
  }
}