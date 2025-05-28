package Models;

public class Movie {
    private int idMovie; //Primary Key
    private String title;
    private int duration;
    private String genre;
    private String classification;

    public Movie(int idMovie, String title, int duration, String genre, String classification) {
        this.idMovie = idMovie;
        this.title = title;
        this.duration = duration;
        this.genre = genre;
        this.classification = classification;
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

}
