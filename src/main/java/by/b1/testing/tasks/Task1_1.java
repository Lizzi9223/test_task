package by.b1.testing.tasks;

import static by.b1.testing.consts.Parameters.Task1_1.FILES_COUNT;
import static by.b1.testing.consts.Parameters.Task1_1.FLOAT_NUMBER_ACCURACY;
import static by.b1.testing.consts.Parameters.Task1_1.RANDOM_FLOAT_NUMBER_MAXIMUM;
import static by.b1.testing.consts.Parameters.Task1_1.RANDOM_FLOAT_NUMBER_MINIMUM;
import static by.b1.testing.consts.Parameters.Task1_1.RANDOM_INTEGER_NUMBER_MAXIMUM;
import static by.b1.testing.consts.Parameters.Task1_1.RANDOM_INTEGER_NUMBER_MINIMUM;
import static by.b1.testing.consts.Parameters.Task1_1.RANDOM_STRING_LENGTH;
import static by.b1.testing.consts.Parameters.Task1_1.STRINGS_COUNT;
import static by.b1.testing.consts.Parameters.Task1_1.YEARS_RANGE;
import static by.b1.testing.consts.Parameters.Task1_2.FILE_EXTENSION;
import static by.b1.testing.consts.Parameters.Task1_2.FILE_NAME;
import static by.b1.testing.consts.Parameters.Task1_2.FOLDER_NAME;

import by.b1.testing.consts.Consts;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.Year;
import java.util.Random;

/**
 * Task 1.1. Generate 100 text files,<br>
 * each containing 100 000 lines<br>
 * with the given structure
 *
 * @author Lizaveta Yakauleva
 * @version 1.0
 */
public class Task1_1 {
  /** Generate files with the given structure */
  public static void generateFiles() {
    File dir = new File(FOLDER_NAME);
    // creates folder to store files, if it doesn't exist yet
    boolean created = dir.mkdir();
    if (created) System.out.println("Folder '" + FOLDER_NAME + "' has been created");
    else System.out.println("Folder '" + FOLDER_NAME + "' already exists");

    for (int fileIndex = 0; fileIndex < FILES_COUNT; fileIndex++) {
      try {
        // forms file name
        StringBuilder fileName =
            new StringBuilder(FOLDER_NAME)
                .append(FILE_NAME)
                .append(fileIndex + 1)
                .append(FILE_EXTENSION);
        // created object to write to file
        PrintWriter writer = new PrintWriter(fileName.toString(), StandardCharsets.UTF_8);

        // writes generated strings to file
        for (int stringIndex = 0; stringIndex < STRINGS_COUNT; stringIndex++)
          writer.println(generateString());

        writer.close();
        System.out.println("File '" + fileName + "' is created.");
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  /**
   * Forms string with the given structure<br>
   * ( random date || random english string || random russian string<br>
   * || random integer number || random double number)
   *
   * @return formed string
   */
  private static String generateString() {
    // forms result string
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

  /**
   * Generates random date within last given number of last years
   *
   * @param withinHowManyYears number of years to form range to generate from
   * @return generated date
   * @throws IllegalArgumentException if parameter is less than 1
   */
  private static String generateRandomDate(int withinHowManyYears) {
    if (withinHowManyYears < 1) throw new IllegalArgumentException();

    // generates day number for the date
    int day =
        Integer.parseInt(
            generateRandomNumber(
                Consts.MINIMUM_DAY, Consts.MAXIMUM_DAY, Consts.INTEGER_NUMBER_ACCURACY));

    // generates month number for the date
    int month =
        Integer.parseInt(
            generateRandomNumber(
                Consts.MINIMUM_MONTH, Consts.MAXIMUM_MONTH, Consts.INTEGER_NUMBER_ACCURACY));

    // generates year number for the date
    int year =
        Integer.parseInt(
            generateRandomNumber(
                Year.now().getValue() - withinHowManyYears,
                Year.now().getValue(),
                Consts.INTEGER_NUMBER_ACCURACY));

    // forms result date
    return String.format(Consts.TWO_DIGITS_NUMBER_FORMATTER, day)
        + Consts.SEPARATOR_FOR_DATE
        + String.format(Consts.TWO_DIGITS_NUMBER_FORMATTER, month)
        + Consts.SEPARATOR_FOR_DATE
        + year;
  }

  /**
   * Generates random russian string with the given length
   *
   * @param length length of the result string
   * @return generated string
   * @throws IllegalArgumentException if length is less than 1
   */
  private static String generateRandomRussianString(int length) {
    if (length < 1) throw new IllegalArgumentException();
    return generateRandomString(length, Consts.RUSSIAN_ALPHABET);
  }

  /**
   * Generates random english string with the given length
   *
   * @param length length of the result string
   * @return generated string
   * @throws IllegalArgumentException if length is less than 1
   */
  private static String generateRandomEnglishString(int length) {
    if (length < 1) throw new IllegalArgumentException();
    return generateRandomString(length, Consts.ENGLISH_ALPHABET);
  }

  /**
   * Generates random string with the given length from the given sequence
   *
   * @param length length of the result string
   * @param alphabetString characters sequence to generate string
   * @return generated string
   */
  private static String generateRandomString(int length, String alphabetString) {
    Random random = new Random();
    char[] alphabet = alphabetString.toCharArray();
    char[] result = new char[length];
    for (int index = 0; index < result.length; index++)
      // generates random character for the result string
      result[index] = alphabet[random.nextInt(alphabet.length)];
    return new String(result);
  }

  /**
   * Generates random number
   *
   * @param from left border of the range for generation
   * @param to right border of the range for generation
   * @param accuracy a number of symbols after comma
   * @return generated number
   * @throws IllegalArgumentException if left border is higher than the right one<br>
   *     or if number's accuracy is less than 0
   */
  private static String generateRandomNumber(int from, int to, int accuracy) {
    if (from > to) throw new IllegalArgumentException();
    if (accuracy < 0) throw new IllegalArgumentException();
    if (accuracy == 0) {
      //generated integer number
      return String.valueOf((int) (Math.random() * (to - from + 1) + from));
    } else {
      //generated double number with given accuracy
      return String.format("%." + accuracy + "f", Math.random() * (to - from + 1) + from);
    }
  }
}
