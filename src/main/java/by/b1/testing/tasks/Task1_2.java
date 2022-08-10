package by.b1.testing.tasks;

import static by.b1.testing.consts.Parameters.Task1_2.FILE_EXTENSION;
import static by.b1.testing.consts.Parameters.Task1_2.FOLDER_NAME;
import static by.b1.testing.consts.Parameters.Task1_2.RESULT_FILE_NAME;
import static by.b1.testing.consts.Parameters.Task1_2.SUBSTRING_TO_REMOVE_LINES_WITH;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import org.apache.commons.io.FileUtils;

/**
 * Task 1.2. Delete from all files strings<br>
 * containing given substring and<br>
 * merge all files into one
 *
 * @author Lizaveta Yakauleva
 * @version 1.0
 */
public class Task1_2 {

  /** Merges all file from folder into one */
  public static void mergeFiles() {
    try {
      File outputFile = new File(RESULT_FILE_NAME + FILE_EXTENSION);
      File folder = new File(FOLDER_NAME);
      boolean isToAppendToFile = false;

      for (File inputFile : Objects.requireNonNull(folder.listFiles())) {
        System.out.println("File ' " + inputFile.getName() + "' is processing.");

        FileReader fileReader = new FileReader(inputFile);
        BufferedReader reader = new BufferedReader(fileReader);
        StringBuilder fileContent = new StringBuilder();
        // delete strings containing given substring
        boolean isFileChanged = deleteLinesBySubstring(fileContent, reader);
        // if initial file content was modified, file is overwritten
        if (isFileChanged) FileUtils.write(inputFile, fileContent, StandardCharsets.UTF_8);

        System.out.println("File ' " + inputFile.getName() + "' is appending. \n");
        // write current file content to the result file
        FileUtils.write(outputFile, fileContent, StandardCharsets.UTF_8, isToAppendToFile);
        isToAppendToFile = true;
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Deletes strings with the given substring
   *
   * @param fileContent object to form result text (without certain strings)
   * @param reader object to read from file
   * @return true if strings were deleted, false otherwise
   */
  private static boolean deleteLinesBySubstring(StringBuilder fileContent, BufferedReader reader) {
    boolean isFileChanged = false;
    int deletedLinesCount = 0;
    try {
      // reads from file line by line
      String line = reader.readLine();
      while (line != null) {
        // if line does not contain given substring, it's added to result text
        if (!line.contains(SUBSTRING_TO_REMOVE_LINES_WITH)) fileContent.append(line).append("\n");
        // otherwise it's skipped and counter containing number of deleted line is incremented
        else {
          isFileChanged = true;
          deletedLinesCount++;
        }
        line = reader.readLine();
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    System.out.println(deletedLinesCount + " lines were deleted.");
    return isFileChanged;
  }
}
