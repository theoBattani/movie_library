package fr.theo.data;

public class Movie {

  private String title;
  private int year;
  private String director;

  public Movie(String title, int year, String director) {
    this.title = title;
    this.year = year;
    this.director = director;
  }

  public String getTitle() {return this.title;}
  public int getYear() {return this.year;}
  public String getDirector() {return this.director;}

}
