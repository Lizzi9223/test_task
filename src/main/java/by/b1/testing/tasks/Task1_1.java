package by.b1.testing.tasks;

import static by.b1.testing.consts.Parameters.Task1_1.FILES_COUNT;
import static by.b1.testing.consts.Parameters.Task1_2.FILE_EXTENSION;
import static by.b1.testing.consts.Parameters.Task1_2.FILE_NAME;
import static by.b1.testing.consts.Parameters.Task1_1.FLOAT_NUMBER_ACCURACY;
import static by.b1.testing.consts.Parameters.Task1_2.FOLDER_NAME;
import static by.b1.testing.consts.Parameters.Task1_1.RANDOM_FLOAT_NUMBER_MAXIMUM;
import static by.b1.testing.consts.Parameters.Task1_1.RANDOM_FLOAT_NUMBER_MINIMUM;
import static by.b1.testing.consts.Parameters.Task1_1.RANDOM_INTEGER_NUMBER_MAXIMUM;
import static by.b1.testing.consts.Parameters.Task1_1.RANDOM_INTEGER_NUMBER_MINIMUM;
import static by.b1.testing.consts.Parameters.Task1_1.RANDOM_STRING_LENGTH;
import static by.b1.testing.consts.Parameters.Task1_1.STRINGS_COUNT;
import static by.b1.testing.consts.Parameters.Task1_1.YEARS_RANGE;

import by.b1.testing.consts.Consts;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.Year;
import java.util.Random;

public class Task1_1 {
  public static void generateFiles() {
    File dir = new File(FOLDER_NAME);
    boolean created = dir.mkdir();
    if (created) System.out.println("Folder '" + FOLDER_NAME + "' has been created");
    else System.out.println("Folder '" + FOLDER_NAME + "' already exists");

    for (int fileIndex = 0; fileIndex < FILES_COUNT; fileIndex++) {
      try {
        StringBuilder fileName =
            new StringBuilder(FOLDER_NAME)
                .append(FILE_NAME)
                .append(fileIndex + 1)
                .append(FILE_EXTENSION);
        PrintWriter writer = new PrintWriter(fileName.toString(), StandardCharsets.UTF_8);

        for (int stringIndex = 0; stringIndex < STRINGS_COUNT; stringIndex++)
          writer.println(generateString());

        writer.close();
        System.out.println("File '" + fileName + "' is created.");
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  private static String generateString() {
    return generateRandomDate(YEARS_RANGE)
        + Consts.SEPARATOR_FOR_STRING
        + generateRandomEnglishString(RANDOM_STRING_LENGTH)
        + Consts.SEPARATOR_FOR_STRING
        + generateRandomRussianString(RANDOM_STRING_LENGTH)
        + Consts.SEPARATOR_FOR_STRING
        + generateRandomNumber(
            RANDOM_INTEGER_NUMBER_MINIMUM,
            RANDOM_INTEGER_NUMBER_MAXIMUM,
            Consts.INTEGER_NUMBER_ACCURACY)
        + Consts.SEPARATOR_FOR_STRING
        + generateRandomNumber(
            RANDOM_FLOAT_NUMBER_MINIMUM, RANDOM_FLOAT_NUMBER_MAXIMUM, FLOAT_NUMBER_ACCURACY)
        + Consts.SEPARATOR_FOR_STRING;
  }

  private static String generateRandomDate(int withinHowManyYears) {
    if (withinHowManyYears < 1) throw new IllegalArgumentException();
    int day =
        Integer.parseInt(
            generateRandomNumber(
                Consts.MINIMUM_DAY, Consts.MAXIMUM_DAY, Consts.INTEGER_NUMBER_ACCURACY));
    int month =
        Integer.parseInt(
            generateRandomNumber(
                Consts.MINIMUM_MONTH, Consts.MAXIMUM_MONTH, Consts.INTEGER_NUMBER_ACCURACY));
    int year =
        Integer.parseInt(
            generateRandomNumber(
                Year.now().getValue() - withinHowManyYears,
                Year.now().getValue(),
                Consts.INTEGER_NUMBER_ACCURACY));
    return String.format(Consts.TWO_DIGITS_NUMBER_FORMATTER, day)
        + Consts.SEPARATOR_FOR_DATE
        + String.format(Consts.TWO_DIGITS_NUMBER_FORMATTER, month)
        + Consts.SEPARATOR_FOR_DATE
        + year;
  }

  private static String generateRandomRussianString(int length) {
    if (length < 1) throw new IllegalArgumentException();
    return generateRandomString(length, Consts.RUSSIAN_ALPHABET);
  }

  private static String generateRandomEnglishString(int length) {
    if (length < 1) throw new IllegalArgumentException();
    return generateRandomString(length, Consts.ENGLISH_ALPHABET);
  }

  private static String generateRandomString(int length, String alphabetString) {
    Random random = new Random();
    char[] alphabet = alphabetString.toCharArray();
    char[] result = new char[length];
    for (int index = 0; index < result.length; index++)
      result[index] = alphabet[random.nextInt(alphabet.length)];
    return new String(result);
  }

  private static String generateRandomNumber(int from, int to, int accuracy) {
    if (from > to) throw new IllegalArgumentException();
    if (accuracy < 0) throw new IllegalArgumentException();
    if (accuracy == 0) {
      return String.valueOf((int) (Math.random() * (to - from + 1) + from));
    } else {
      return String.format("%." + accuracy + "f", Math.random() * (to - from + 1) + from);
    }
  }
}
