package by.b1.testing.consts;

public class Database {
  public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
  public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/test_task?useSSL=false";
  public static final String DB_USER = "root";
  public static final String DB_PASSWORD = "system";
  public static final String INSERT_QUERY =
      "insert into strings(date, english_line, russian_line, int_number, float_number) values(?,?,?,?,?);";
}
