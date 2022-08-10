package by.b1.testing.tasks;

import static by.b1.testing.consts.Database.DB_DRIVER;
import static by.b1.testing.consts.Database.DB_PASSWORD;
import static by.b1.testing.consts.Database.DB_URL;
import static by.b1.testing.consts.Database.DB_USER;
import static by.b1.testing.consts.Database.INSERT_QUERY;
import static by.b1.testing.consts.Parameters.Task1_2.FOLDER_NAME;

import by.b1.testing.model.CustomString;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

/**
 * Task 1.3. Import file content to database. <br>
 * Display the process
 *
 * @author Lizaveta Yakauleva
 * @version 1.0
 */
public class Task1_3 {
  private static int addedStringsCount = 0;
  private static int linesInFile = 0;

  /** Inputs all line from file with the specific structure to database */
  public static void importFileToDatabase() {
    File folder = new File(FOLDER_NAME);
    for (File inputFile : Objects.requireNonNull(folder.listFiles())) {
      System.out.println("\n\nFile ' " + inputFile.getName() + "' is processing.");
      try {
        FileReader fileReader = new FileReader(inputFile);
        BufferedReader reader = new BufferedReader(fileReader);
        countLinesInFile(inputFile);
        // reads file line by line
        String line = reader.readLine();
        while (line != null) {
          // converts line to model object
          CustomString customString = convertLineToModel(line);
          // adds formed object to database
          addObjectToDatabase(customString);
          line = reader.readLine();
        }
        reader.close();
        fileReader.close();
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  /**
   * Converts random string to model
   *
   * @param line string to convert
   * @return formed object
   */
  private static CustomString convertLineToModel(String line) {
    // splits given string into string array by regular expression
    String[] strings = line.split("\\|\\|");
    CustomString customString = new CustomString();
    // sets values to model
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    customString.setDate(LocalDate.parse(strings[0], formatter));
    customString.setEnglishString(strings[1]);
    customString.setRussianString(strings[2]);
    customString.setIntNumber(Integer.parseInt(strings[3]));
    // parses double number with comma as a separator
    NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
    Number number = null;
    try {
      number = format.parse(strings[4]);
    } catch (ParseException e) {
      System.out.println(e.getMessage());
    }
    customString.setDoubleNumber(Objects.requireNonNull(number).doubleValue());
    return customString;
  }

  /**
   * Adds provided object to database
   *
   * @param customString object to add
   */
  private static void addObjectToDatabase(CustomString customString) {
    Connection con = null;
    PreparedStatement st = null;
    try {
      Class.forName(DB_DRIVER);
      // creates connection to database
      con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
      // creates query
      st = con.prepareStatement(INSERT_QUERY);
      // sets values to input
      st.setDate(1, java.sql.Date.valueOf(customString.getDate()));
      st.setString(2, customString.getEnglishString());
      st.setString(3, customString.getRussianString());
      st.setInt(4, customString.getIntNumber());
      st.setDouble(5, customString.getDoubleNumber());
      st.executeUpdate();
      addedStringsCount++;
      System.out.println(
          addedStringsCount
              + " lines added to database. "
              + (linesInFile - addedStringsCount)
              + " lines left.");
    } catch (ClassNotFoundException | SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        // closes statement
        Objects.requireNonNull(st).close();
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
      try {
        // closes connection
        Objects.requireNonNull(con).close();
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  /**
   * Counts total number of line in the file
   *
   * @param inputFile file to count line in
   */
  private static void countLinesInFile(File inputFile) {
    try {
      FileReader fileReader = new FileReader(inputFile);
      BufferedReader reader = new BufferedReader(fileReader);
      linesInFile = 0;
      // reads line by line and counts
      while (reader.readLine() != null) linesInFile++;
      reader.close();
      fileReader.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
