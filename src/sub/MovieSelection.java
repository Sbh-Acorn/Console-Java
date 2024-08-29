package sub;

import java.util.ArrayList;
import java.util.List;

public class MovieSelection {
    private List<Movie1> movies; // 선택 가능한 영화 리스트

    // 생성자
    public MovieSelection() {
        this.movies = new ArrayList<>();
    }

    // 영화 목록 추가
    public void addMovie(Movie1 movie) {
        movies.add(movie);
    }

    // 영화 목록 반환
    public List<Movie1> getMovies() {
        return movies;
    }

    // 영화 제목으로 검색
    public Movie1 searchMovie(String title) {
        return movies.stream()
            .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
            .findFirst()
            .orElse(null);
    }

    // 상영 시간 및 상영관 리스트 출력
    public void displayShowtimes(Movie1 movie) {
        if (movie != null) {
            System.out.println("\n상영 시간 및 상영관:");
            movie.getShowtimes().forEach(System.out::println);
        }
    }
}




