
package fr.theo.data.base;

import fr.theo.data.table.Movie;
import fr.theo.util.MySQLConnectionWrapper;

public class MovieDataBaseConnection {

  private static final String TABLE = "movie_table";

  private MySQLConnectionWrapper sqlConnection;

  public MovieDataBaseConnection() {
    sqlConnection = new MySQLConnectionWrapper(
      "localhost", "3306", "moviedb", "root", ""
    );
  }

  public void close() {sqlConnection.close();}

  public void addMovie(Movie movie) {
    sqlConnection.insert(TABLE, 
      new String[] {
        "id", 
        "title", 
        "year", 
        "director_name"
      }, 
      new String[] {
        "NULL", 
        movie.getTitle(), 
        String.format("%d",movie.getYear()), 
        movie.getDirectorName()
      }
    );
  }

  public void modifyMovie(Movie movie, String[] names, String[] values) {
    sqlConnection.updateById(TABLE, movie.getId(), names, values);
  }

  public void deleteMovie(Movie movie) {
    sqlConnection.deleteById(TABLE, movie.getId());
  }

  public Movie[] getMovies() {
    String[] strMovies = sqlConnection.SelectAll("movie_table");
    Movie[] output = new Movie[strMovies.length];
    int index = 0;
    for (String movieStr: strMovies) {
      int id = 0;
      String title = "";
      int year = 0;
      String directorName = "";
      String[] movieAtrributes = movieStr.split(",");
      for (String strAttr: movieAtrributes) {
        String[] field = strAttr.split(":");
        switch (field[0]) {
          case "id": id = Integer.parseInt(field[1]); break;
          case "title": title = field[1]; break;
          case "year" : year = Integer.parseInt(field[1]); break;
          case "director_name": directorName = field[1]; break; 
        }
      }
      Movie movie = new Movie(id, title, year, directorName);
      output[index] = movie;
      index++;
    }
    return output;
  }
}








