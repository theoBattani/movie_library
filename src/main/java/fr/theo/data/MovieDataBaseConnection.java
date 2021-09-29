package fr.theo.data;

public class MovieDataBaseConnection {
    SQLConnectionWrapper sqlConnection;
    int nbColumnInTable;

    public MovieDataBaseConnection() {
        sqlConnection = new SQLConnectionWrapper("localhost", "3306", "moviedb", "root", "");
        nbColumnInTable = sqlConnection.countColumns("movie_table");
    }

    public void addMovie(Movie movie) {
        sqlConnection.insert("movie_table", 
            new String[] {"id", "title", "year", "director_name"}, 
            new String[] {"NULL", movie.getTitle(), String.format("%d",movie.getYear()), movie.getDirectorName()}
        );
    }

    public Movie[] getMovies() {
        String[] strMovies = sqlConnection.SelectAll("movie_table");
        Movie[] output = new Movie[strMovies.length];
        int index = 0;
        for (String movieStr: strMovies) {
            String id = "null";
            String title = "";
            int year = 0;
            String directorName = "";
            String[] movieAtrributes = movieStr.split(",");
            for (String strAttr: movieAtrributes) {
                String[] field = strAttr.split(":");
                switch (field[0]) {
                    case "id":
                        id = field[1];
                        break;
                    case "title": 
                        title = field[1];
                        break;
                    case "year" : 
                        year = Integer.parseInt(field[1]);
                        break;
                    case "director_name": 
                        directorName = field[1];
                        break; 
                }
            }
            Movie movie = new Movie(id, title, year, directorName);
            // System.out.println(index);
            // System.out.println(movie.toString());
            output[index] = movie;
            index++;
        }
        return output;
    }
}
