package by.b1.testing;

import by.b1.testing.tasks.Task1_1;
import by.b1.testing.tasks.Task1_2;
import by.b1.testing.tasks.Task1_3;

/**
 * @author Lizaveta Yakauleva
 * @version 1.0
 */
public class Main {

  /**
   * program entry point
   */
  public static void main(String[] args) {

    //execute task 1.1
    Task1_1.generateFiles();

    //execute task 1.2
    Task1_2.mergeFiles();

    //execute task 1.3
    Task1_3.importFileToDatabase();
  }
}
