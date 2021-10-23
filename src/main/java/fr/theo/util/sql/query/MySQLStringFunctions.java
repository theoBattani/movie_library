
package fr.theo.util.sql.query;

public class MySQLStringFunctions {

  public static String ASCII(char character) {
    return String.format("ASCII('%c')", character);
  }
  public static String ASCII(String string) {
    return String.format("ASCII(\"%s\")", string);
  }
  public static String CHAR_LENGTH(String string) {
    return String.format("CHAR_LENGTH(\"%s\"", string);
  } 
  public static String CHARACTER_LENGTH(String string) {
    return String.format("CHARACTER_LENGTH(\"%s\"", string);
  }
  public static String CONCAT(String... strings) {
    StringBuilder builder = new StringBuilder();
    builder.append("CONCAT(");
    for (String string: strings)
      builder.append(String.format("\"%s\",", string));
    builder.setLength(builder.length() - 1);
    builder.append(")");
    return builder.toString();
  }
  public static String CONCAT_WS(char sep, String... strings) {
    StringBuilder builder = new StringBuilder();
    builder.append("CONCAT_WS(");
    for (String string: strings)
      builder.append(String.format("\"%s\",", string));
    builder.setLength(builder.length() - 1);
    builder.append(")");
    return builder.toString();
  };
  public static String FIELD(char value, char... characters) {
    StringBuilder builder = new StringBuilder();
    builder.append(String.format("FIELD('%c',", value));
    for (char c: characters)
      builder.append(String.format("'%c'", c));
    builder.setLength(builder.length() - 1);
    return builder.toString();
  };
  public static String FIND_IN_SET(String value, String... string_list) {
    StringBuilder builder = new StringBuilder();
    builder.append(String.format("FIND_IN_SET(\"%s\"", value));
    for (String string: string_list)
      builder.append(String.format("\"%s\",", string));
    builder.setLength(builder.length() - 1);
    builder.append(")");
    return builder.toString();
  }
  public static String FORMAT(double number, int decimal_places) {
    return String.format("FORMAT(%f, %d)", number, decimal_places);
  }
  public static String INSERT(String string, int position, int number, String string2) {
    return String.format("INSERT(%s, %d, %d, %s)",
      string, position, number, string2
    );
  }
  public static String INSTR(String string1, String string2) {
    return String.format("INSTR(%s, %s)", string1, string2);
  }
  public static String LCASE(String string) {
    return String.format("LCASE(%s)", string);
  }
  public static String LEFT(String string, int numbers_of_char) {
    return String.format("LEFT(%s, %d)", string, numbers_of_char);
  }
  public static String LENGTH(String string) {
    return String.format("LENGTH(%s)", string);
  }
  public static String LOCATE(String substring, String string, int start) {
    return String.format("LOCATE(%s, %s, %d)", substring, string, start);
  }
  public static String LOCATE(String substring, String string) {
    return String.format("LOCATE(%s, %s, %d)", substring, string, 1);
  }
  public static String LOWER(String string) {
    return String.format("LOWER(%s)", string);
  }
  public static String LPAD(String string, int length, String lpad_string) {
    return String.format("LPAD(%s, %d, %s)", string, length, lpad_string);
  }
  public static String LTRIM(String string) {
    return String.format("LTRIM(%s)", string);
  }
  public static String MID(String string, int start, int length) {
    return String.format("MID(%s, %d, %d)", string, start, length);
  }
  public static String POSITION(String string, String substring) {
    return String.format("POSITION(%s %s %s)", 
      string, SQLKeywords.IN, substring
    );
  }
  public static String REPEAT(String string ,int number) {
    return String.format("REPEAT(%s, %d)", string, number);
  }
  public static String REPLACE(String string, String from_string, String new_string) {
    return String.format("REPLACE(%s, %s, %s)",
      string, from_string, new_string
    );
  }
  public static String REVERSE(String string) {
    return String.format("REVERSE(%s)", string);
  }
  public static String RIGHT(String string, int number_of_chars) {
    return String.format("RIGHT(%s, %d)", string, number_of_chars);
  }
  public static String RPAD(String string, int length, String rpad_string) {
    return String.format("RPAD(%s, %d, %s)", 
      string, length, rpad_string
    );
  }
  public static String RTRIM(String string) {
    return String.format("RTRIM(%s)", string);
  }
  public static String SPACE(int number) {
    return String.format("SPACE(%d)", number);
  }
  public static String STRCMP(String string1, String string2) {
    return String.format("STRCMP(%s, %s)", string1, string2);
  }
  public static String SUBSTR(String string, int start, int length) {
    return String.format("SUBSTR(%s, %d, %d)", string, start, length);
  }
  public static String SUBSTR_INDEX(String string, char delimiter, int number) {
    return String.format("SUBSTRING_INDEX(%s, %c, %d)", 
      string, delimiter, number
    );
  }
  public static String TRIM(String string) {
    return String.format("TRIM(%s)", string);
  }
  public static String UCASE(String string) {
    return String.format("UCASE(%s)", string);
  }
  public static String UPPER(String string) {
    return String.format("UPPER(%s)", string);
  }
}