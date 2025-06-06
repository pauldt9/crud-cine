package Models;

import utils.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;

public class Movie {
    private int idMovie; //Primary Key
    private String title;
    private int duration;
    private String genre;
    private String classification;
    private String imgRoute;

    public Movie(){

    }

    public Movie(int idMovie, String title, int duration, String genre, String classification, String imgRoute) {
        this.idMovie = idMovie;
        this.title = title;
        this.duration = duration;
        this.genre = genre;
        this.classification = classification;
        this.imgRoute = imgRoute;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getImgRoute() {
        return imgRoute;
    }
    public void setImgRoute(String imgRoute) {
        this.imgRoute = imgRoute;
    }

    public static ArrayList<Movie> getMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM movies";

        try (
                Connection connection = MySQLConnection.connect();
                Statement st = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(query)
        ) {

            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt("idMovie"),
                        rs.getString("title"),
                        rs.getInt("duration"),
                        rs.getString("genre"),
                        rs.getString("classification"),
                        rs.getString("imgRoute")
                ));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return movies;
    }

    public static Movie getMovieById(int id) {
        Movie movie = null;
        String query = "SELECT * FROM movies WHERE idMovie = " + id;

        try (
                Connection connection = MySQLConnection.connect();
                Statement st = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(query)
        ) {

            if (rs.next()) {
                movie = new Movie(
                        rs.getInt("idMovie"),
                        rs.getString("title"),
                        rs.getInt("duration"),
                        rs.getString("genre"),
                        rs.getString("classification"),
                        rs.getString("imgRoute")
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return movie;
    }

    public static int addMovie(Movie movie) {

        int id = 0;
        String query = "INSERT INTO movies " + "(title,duration,genre,classification,imgRoute)"
                + "VALUES (?,?,?,?,?)";
        int created = 0;

        try (Connection connection = MySQLConnection.connect();
             PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            pst.setString(1, movie.getTitle());
            pst.setInt(2, movie.getDuration());
            pst.setString(3, movie.getGenre());
            pst.setString(4, movie.getClassification());
            pst.setString(5, movie.getImgRoute());

            created = pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public static boolean updateMovie(Movie movie) {
        String query = "UPDATE movies SET title = ?,duration = ?," +
                "genre = ?,classification = ?,imgRoute = ? WHERE idMovie = ?";
        int updated = 0;

        try (
                Connection connection = MySQLConnection.connect();
                PreparedStatement pst = connection.prepareStatement(query)
        ) {
            pst.setString(1, movie.getTitle());
            pst.setInt(2, movie.getDuration());
            pst.setString(3, movie.getGenre());
            pst.setString(4, movie.getClassification());
            pst.setString(5, movie.getImgRoute());
            pst.setInt(6, movie.getIdMovie());


            updated = pst.executeUpdate();
            return updated > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteMovie(int id) {

        int deleted = 0;

        String query = "DELETE FROM movies WHERE idMovie = " + id;

        try (
                Connection connection = MySQLConnection.connect();
                Statement st = (Statement) connection.createStatement();
        ) {
            deleted = st.executeUpdate(query);
            System.out.println(deleted);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return deleted > 0;

    }

    public static ArrayList<Movie> getImgMovies(){
        ArrayList<Movie> moviesList = new ArrayList<>();
//        String query = "SELECT idMovie, imgRoute FROM movies";

        String query = "SELECT DISTINCT m.idMovie, m.imgRoute FROM movies m " +
                "JOIN functions s ON m.idMovie = s.idMovie";

        try (Connection conn = MySQLConnection.connect();
             Statement st = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(query))
        {
            while (rs.next()){
                Movie movie = new Movie();
                movie.setIdMovie(rs.getInt("idMovie"));
                movie.setImgRoute(rs.getString("imgRoute"));

                moviesList.add(movie);
            }

        } catch (SQLException e){
            System.out.println("Hubo un error: " + e.getMessage());
        }

        return moviesList;
    }

    public static int getNumMovies(){
        int count = 0;
        String query = "SELECT COUNT(*) AS total FROM movies";

        try (Connection conn = MySQLConnection.connect();
             Statement st = (Statement)conn.createStatement();
             ResultSet rs = st.executeQuery(query);
        ){
            while(rs.next()){
                count = rs.getInt("total");
            }

        } catch (SQLException e){
            System.out.println("Hubo un error: " + e.getMessage());
        }

        return count;
    }
}
