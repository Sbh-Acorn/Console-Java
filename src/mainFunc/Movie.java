package mainFunc;

import java.util.ArrayList;


public class Movie {
    private String movieRating;
    private String genre;
    private String runningTime;
    private String releaseDate;
    private ArrayList<String> theaters;

    // Getters and Setters
    public String getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public ArrayList<String> getTheaters() {
        return theaters;
    }

    public void setTheaters(ArrayList<String> theaters) {
        this.theaters = theaters;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieRating='" + movieRating + '\'' +
                ", genre='" + genre + '\'' +
                ", runningTime='" + runningTime + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", theaters=" + theaters +
                '}';
    }
}
