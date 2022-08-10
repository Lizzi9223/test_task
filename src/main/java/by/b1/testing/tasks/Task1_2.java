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

public class Task1_2 {
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
        boolean isFileChanged = deleteLinesBySubstring(fileContent, reader);
        if (isFileChanged) FileUtils.write(inputFile, fileContent, StandardCharsets.UTF_8);

        System.out.println("File ' " + inputFile.getName() + "' is appending. \n");
        FileUtils.write(outputFile, fileContent, StandardCharsets.UTF_8, isToAppendToFile);
        isToAppendToFile = true;
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  private static boolean deleteLinesBySubstring(StringBuilder fileContent, BufferedReader reader) {
    boolean isFileChanged = false;
    int deletedLinesCount = 0;
    try {
      String line = reader.readLine();
      while (line != null) {
        if (!line.contains(SUBSTRING_TO_REMOVE_LINES_WITH)) fileContent.append(line).append("\n");
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
