
package fr.theo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class MySQLConnectionWrapper {

  private Connection connection;
  private String url;

  public MySQLConnectionWrapper(
    String host, String port, String dataBase, 
    String username, String password
  ) {
    this.url = String.format(
      "jdbc:mysql://%s:%s/%s", 
      host, port, dataBase
    );
    try {
      this.connection = DriverManager.getConnection(this.url, username, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void close() {
    try {
      this.connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public int countRows(String table) {
    ResultSet resultSet = null;
    try {
      resultSet = this.connection.createStatement().executeQuery(
        (new QueryBuilder()).select().count("*").from(table).build()
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
        (new QueryBuilder()).select("*").from(table).build()
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
        (new QueryBuilder()).select("*").from(table).build()
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

  public void insert(String table, 
    String[] fields_names, String[] fields_values) {
    try {
      this.connection.createStatement().execute(
        (new QueryBuilder())
          .insert()
          .into(table, fields_names)
          .values(fields_values)
          .build()
      );
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public String[] select(String table, String[] fields_names) {

    ResultSet resultSet;
    String[] result = new String[fields_names.length];
    try {
      resultSet = this.connection.createStatement().executeQuery(
        (new QueryBuilder())
          .select(fields_names)
          .from(table)
          .build()
      );
      int index = 0;
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
    String[] output = new String[countRows(table)];
    String[] names = getColumnNames(table);
    try {
      ResultSet resultSet = this.connection.createStatement().executeQuery(
        (new QueryBuilder()).select("*").from(table).build()
      );
      int index = 0;
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

  public void updateById(String table, int id, String[] names, String[] values) {
    try {
      this.connection.createStatement().execute(
        (new QueryBuilder())
          .update(table)
          .set(names, values)
          .where(String.format("id=%d", id))
          .build()
      );
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void deleteById(String table, int id) {
    try {
      this.connection.createStatement().execute(
        (new QueryBuilder())
          .delete()
          .from(table)
          .where(String.format("id=%d", id))
          .build()
      );
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
}









