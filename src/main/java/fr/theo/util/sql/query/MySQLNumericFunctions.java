package fr.theo.util.sql.query;

public class MySQLNumericFunctions {

  public static String ABS(String number) {
    return String.format("ABS(%s)", number);
  }
  public static String ACOS(String number) {
    return String.format("ACOS(%s)", number);
  }
  public static String ASIN(String number) {
    return String.format("ASIN(%s)", number);
  }
  public static String ATAN(String number) {
    return String.format("ATAN(%s)", number);
  }
  public static String ATAN2(String a, String b) {
    return String.format("ATAN2(%s, %s)", a, b);
  }
  public static String AVG(String expression) {
    return String.format("AVG(%s)", expression);
  }
  public static String CEIL(String number) {
    return String.format("CEIL(%s)", number);
  }
  public static String CEILING(String number) {
    return String.format("CEILING(%s)", number);
  }
  public static String COS(String number) {
    return String.format("COS(%s)", number);
  }
  public static String COT(String number) {
    return String.format("COT(%s)", number);
  }
  public static String COUNT(String expression) {
    return String.format("COUNT(%s)", expression);
  }
  public static String DEGREES(String radians) {
    return String.format("DEGREES(%s)", radians);
  }
  public static String DIV = "DIV";
  public static String EXP(String number) {
    return String.format("EXP(%s)", number);
  }
  public static String FLOOR(String number) {
    return String.format("FLOOR(%s)", number);
  }
}
