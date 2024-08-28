package mainFunc;

import java.util.List;

public class Movie1 {
    private String title; // 영화 제목
    private String genre; // 영화 장르
    private int duration; // 상영 시간 (분 단위)
    private String rating; // 영화 등급
    private List<Showtime> showtimes; // 상영 시간 및 상영관 리스트

    // 생성자
    public Movie1(String title, String genre, int duration, String rating, List<Showtime> showtimes) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
        this.showtimes = showtimes;
    }

    // 영화 제목 반환
    public String getTitle() {
        return title;
    }

    // 상영 시간 및 상영관 리스트 반환
    public List<Showtime> getShowtimes() {
        return showtimes;
    }

    // 영화 정보 출력
    @Override
    public String toString() {
        return String.format("%s, %s, %d분, %s", title, genre, duration, rating);
    }
}




