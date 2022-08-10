package by.b1.testing.model;

import java.time.LocalDate;
import java.util.Objects;

public class CustomString {
  private LocalDate date;
  private String englishString;
  private String russianString;
  private int intNumber;
  private double doubleNumber;

  public CustomString() {
  }

  public CustomString(LocalDate date, String englishString, String russianString, int intNumber,
      double doubleNumber) {
    this.date = date;
    this.englishString = englishString;
    this.russianString = russianString;
    this.intNumber = intNumber;
    this.doubleNumber = doubleNumber;
  }

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
