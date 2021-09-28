package fr.theo.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLConnectionWrapper {

  Connection connection;
  String url;

  public MySQLConnectionWrapper(String host, String port, String dataBase, String username, String password) {
    this.url = String.format("jdbc:mysql://%s:%s/%s", host, port, dataBase);
    try {
      this.connection = DriverManager.getConnection(this.url, username, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void insert(String table, String[] fields_names, String[] fields_values) {

    // on ne fait rien si l'on a pas le même nombre de valeur que de noms de champ
    if (fields_names.length != fields_values.length) return;

    // creation de la requete
    String query = String.format("INSERT INTO %s (", table);
    for (int index = 0; index < fields_names.length - 1; index++) {
      query += String.format("%s,", fields_names[index]);
    }
    query += String.format("%s) ", fields_names[fields_names.length]);
    query += "VALUES (";
    for (int index = 0; index < fields_values.length - 1; index++) {
      query += String.format("%s,", fields_values[index]);
    }
    query += String.format("%s) ", fields_values[fields_names.length]);
    // System.out.println(query);

    // execution de la requete
    try {
      this.connection.createStatement().executeQuery(query);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public String[] select(String table, String[] fields_names) {

    // creation de la requete
    String query = "SELECT ";
    for (int index = 0; index < fields_names.length - 1; index++) {
      query += String.format("%s,", fields_names[index]);
    }
    query += String.format("%s ", fields_names[fields_names.length]);
    query += String.format("FROM %s", table);

    // récupération du résultat de la requete
    ResultSet resultSet;
    String[] result = new String[fields_names.length];
    int index = 0;
    try {
      resultSet = this.connection.createStatement().executeQuery(query);
      while (resultSet.next()) {
        result[index] = "";
        result[index] += String.format("%s:%s", fields_names[index], resultSet.getString(fields_names[index]));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
  
}
