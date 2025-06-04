package DAO;

import Models.Movie;
import utils.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MoviesDAO {
    public ArrayList<Movie> getImgMovies(){
        ArrayList<Movie> moviesList = new ArrayList<>();
        String sql = "SELECT idMovie, imgRoute FROM movies";

        try (Connection conn = MySQLConnection.connect();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery())
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
}
