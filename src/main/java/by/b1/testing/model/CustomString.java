package by.b1.testing.model;

import java.time.LocalDate;

/**
 * Model to store generated random string by fields
 *
 * @author Lizaveta Yakauleva
 * @version 1.0
 */
public class CustomString {
  private LocalDate date;
  private String englishString;
  private String russianString;
  private int intNumber;
  private double doubleNumber;

  public CustomString() {}

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getEnglishString() {
    return englishString;
  }

  public void setEnglishString(String englishString) {
    this.englishString = englishString;
  }

  public String getRussianString() {
    return russianString;
  }

  public void setRussianString(String russianString) {
    this.russianString = russianString;
  }

  public int getIntNumber() {
    return intNumber;
  }

  public void setIntNumber(int intNumber) {
    this.intNumber = intNumber;
  }

  public double getDoubleNumber() {
    return doubleNumber;
  }

  public void setDoubleNumber(double doubleNumber) {
    this.doubleNumber = doubleNumber;
  }
}
