
package fr.theo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class MySQLConnectionWrapper {

  private Connection connection;
  private String url;

  public MySQLConnectionWrapper(String host, String port, String dataBase, String username, String password) {
    this.url = String.format("jdbc:mysql://%s:%s/%s", host, port, dataBase);
    try {
      this.connection = DriverManager.getConnection(this.url, username, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void close() {try {
    this.connection.close();
  } catch (SQLException e) {
    e.printStackTrace();
  }}

  public int countRows(String table) {
    ResultSet resultSet = null;
    try {
      resultSet = this.connection.createStatement().executeQuery(
        String.format("SELECT COUNT(*) FROM %s", table)
      );
      resultSet.next();
      return resultSet.getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }

  public int countColumns(String table) {
    ResultSet resultSet = null;
    int output = 0;
    try {
      resultSet = this.connection.createStatement().executeQuery(
        String.format("SELECT * FROM %s", table)
      );
      ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
      output =  resultSetMetaData.getColumnCount();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return output;
  }

  public String[] getColumnNames(String table) {
    ResultSet resultSet = null;
    int nbColumns = countColumns(table);
    String[] output = new String[nbColumns];
    try {
      resultSet = this.connection.createStatement().executeQuery(
        String.format("SELECT * FROM %s", table)
      );
      ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
      for (int index = 0; index < nbColumns; index++) {
        output[index] = resultSetMetaData.getColumnName(index+1);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return output;
  }

  public void insert(String table, String[] fields_names, String[] fields_values) {

    if (fields_names.length != fields_values.length) return;

    String query = String.format("INSERT INTO `%s` (", table);
    for (int index = 0; index < fields_names.length - 1; index++) {
      query += String.format("`%s`,", fields_names[index]);
    }
    query += String.format("`%s`) ", fields_names[fields_names.length - 1]);
    query += "VALUES (";
    for (int index = 0; index < fields_values.length - 1; index++) {
      if (fields_values[index] == "NULL") query += String.format("%s,", fields_values[index]);
      else query += String.format("'%s',", fields_values[index]);
    }
    query += String.format("'%s') ", fields_values[fields_names.length - 1]);
    System.out.println(query);

    try {
      this.connection.createStatement().execute(query);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public String[] select(String table, String[] fields_names) {

    String query = "SELECT ";
    for (int index = 0; index < fields_names.length - 1; index++) {
      query += String.format("%s,", fields_names[index]);
    }
    query += String.format("%s ", fields_names[fields_names.length - 1]);
    query += String.format("FROM %s", table);

    ResultSet resultSet;
    String[] result = new String[fields_names.length];
    int index = 0;
    try {
      resultSet = this.connection.createStatement().executeQuery(query);
      while (resultSet.next()) {
        result[index] = "";
        result[index] += String.format("%s:%s,", fields_names[index], resultSet.getString(fields_names[index]));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  public String[] SelectAll(String table) {
    String query = String.format("SELECT * FROM %s", table);
    String[] output = new String[countRows(table)];
    String[] names = getColumnNames(table);
    ResultSet resultSet = null;
    int index = 0;
    try {
      resultSet = this.connection.createStatement().executeQuery(query);
      int nbColumns = countColumns(table);
      while (resultSet.next()) {
        output[index] = "";
        for (int columnIndex = 0; columnIndex < nbColumns - 1; columnIndex++) {
          output[index] += String.format("%s:%s,", names[columnIndex], resultSet.getString(columnIndex+1));
        }
        output[index] += String.format("%s:%s", names[nbColumns-1], resultSet.getString(nbColumns));
        index++;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return output;
  }

  public void deleteById(String table, int id) {
    String query = String.format("DELETE FROM %s WHERE id=%d", table, id);
    try {
      this.connection.createStatement().execute(query);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
}
