package fr.theo.data;

public class Movie {

  private String id;
  private String title;
  private int year;
  private String directorName;

  public Movie(String id, String title, int year, String directorName) {
    this.id = id;
    this.title = title;
    this.year = year;
    this.directorName = directorName;
  }

  public String getTitle() {return this.title;}
  public int getYear() {return this.year;}
  public String getDirectorName() {return this.directorName;}

  @Override
  public String toString() {
    return String.format("Movie: %s, %d, %s",
      this.title, this.year, this.directorName);
  }
}
