package by.b1.testing;

import by.b1.testing.consts.Parameters;
import by.b1.testing.tasks.Task1_1;
import by.b1.testing.tasks.Task1_2;
import by.b1.testing.tasks.Task1_3;

/**
 * @author Lizaveta Yakauleva
 * @version 1.0
 */
public class Main {

  /** program entry point */
  public static void main(String[] args) {

    // execute task 1.1
    System.out.println("\n------------- Task 1 -------------\n");
    Task1_1.generateFiles();

    // execute task 1.2
    System.out.println("\n\n------------- Task 2 -------------\n");
    System.out.println(
        "Substring"
            + Parameters.Task1_2.SUBSTRING_TO_REMOVE_LINES_WITH
            + " will be deleted from all files\n");
    Task1_2.mergeFiles();

    // execute task 1.3
    System.out.println("\n\n------------- Task 3 -------------\n");
    Task1_3.importFileToDatabase();
  }
}
