package fr.theo.util;

public class QueryBuilder {

  private StringBuilder builder;

  public QueryBuilder() {this.builder = new StringBuilder();}

  public QueryBuilder insert() {
    this.builder.append("INSERT ");
    return this;
  }

  public QueryBuilder delete() {
    this.builder.append("DELETE ");
    return this;
  }

  public QueryBuilder select() {
    this.builder.append("SELECT ");
    return this;
  }

  public QueryBuilder select(String name) {
    this.select();
    this.builder.append(String.format("%s ", name));
    return this;
  }

  public QueryBuilder select(String[] names) {
    this.select();
    for (int index = 0; index < names.length - 1; index++) {
      this.builder.append(String.format("%s, ", names[index]));
    }
    this.builder.append(String.format("%s ", names[names.length - 1]));
    return this;
  }
  
  public QueryBuilder update(String table) {
    this.builder.append(String.format("UPDATE %s ", table));
    return this;
  }

  public QueryBuilder values(String[] values) {
    this.builder.append("VALUES (");
    for (int index = 0; index < values.length - 1; index++) {
      if (values[index] == "NULL") this.builder.append(String.format("%s,", values[index]));
      else this.builder.append(String.format("'%s', ", values[index]));
    }
    this.builder.append(String.format("'%s') ", values[values.length - 1]));
    return this;
  }

  public QueryBuilder set(String[] names, String[] values) {
    this.builder.append("SET ");
    for (int index = 0; index < names.length - 1; index++) {
      this.builder.append(String.format("`%s` = '%s', ", names[index], values[index]));
    }
    this.builder.append(String.format("`%s` = '%s' ", names[names.length - 1], values[values.length - 1]));
    return this;
  }

  public QueryBuilder into(String table, String[] names) {
    this.builder.append(String.format("INTO `%s` (", table));
    for (int index = 0; index < names.length - 1; index++) {
      this.builder.append(String.format("`%s`, ", names[index]));
    }
    this.builder.append(String.format("`%s`) ", names[names.length - 1]));
    return this;
  }

  public QueryBuilder from(String table) {
    this.builder.append(String.format("FROM %s", table)); 
    return this;
  }

  public QueryBuilder where(String condition) {
    this.builder.append("WHERE " + condition);
    return this;
  }

  public QueryBuilder count(String name) {
    this.builder.append(String.format("COUNT(%s) ", name));
    return this;
  }

  public String build() {
    System.out.println(this.builder.toString());
    return this.builder.toString();
  }

}
