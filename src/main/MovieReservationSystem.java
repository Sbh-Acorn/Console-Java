package main;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import joinLogin.User;
import mainFunc.Movie1;
import mainFunc.MovieSelection;
import mainFunc.Showtime;

public class MovieReservationSystem {
    public static void main(String[] args) {
        MovieSelection movieSelection = new MovieSelection();

        // 영화 목록 추가
        movieSelection.addMovie(new Movie1("에이리언:로물루스", "공포, SF", 119, "15세 관람가", Arrays.asList(
            new Showtime("10:00", "1관", 5, 5),
            new Showtime("13:00", "2관", 5, 5),
            new Showtime("15:30", "3관", 5, 5)
        )));
        movieSelection.addMovie(new Movie1("파일럿", "코미디", 111, "12세 관람가", Arrays.asList(
            new Showtime("11:00", "1관", 5, 5),
            new Showtime("14:00", "2관", 5, 5),
            new Showtime("16:30", "3관", 5, 5)
        )));
        movieSelection.addMovie(new Movie1("트위스터스", "액션, 어드벤쳐, 드라마", 122, "12세 관람가", Arrays.asList(
            new Showtime("12:00", "1관", 5, 5),
            new Showtime("15:00", "2관", 5, 5),
            new Showtime("17:30", "3관", 5, 5)
        )));
        movieSelection.addMovie(new Movie1("행복의 나라", "드라마", 124, "12세 관람가", Arrays.asList(
            new Showtime("13:00", "1관", 5, 5),
            new Showtime("16:00", "2관", 5, 5),
            new Showtime("18:30", "3관", 5, 5)
        )));
        movieSelection.addMovie(new Movie1("빅토리", "드라마", 120, "12세 관람가", Arrays.asList(
            new Showtime("14:00", "1관", 5, 5),
            new Showtime("17:00", "2관", 5, 5),
            new Showtime("19:30", "3관", 5, 5)
        )));
        movieSelection.addMovie(new Movie1("소년시절의 너", "멜로/로맨스, 드라마, 범죄", 135, "15세 관람가", Arrays.asList(
            new Showtime("10:00", "1관", 5, 5),
            new Showtime("13:00", "2관", 5, 5),
            new Showtime("15:30", "3관", 5, 5)
        )));
        movieSelection.addMovie(new Movie1("한국이 싫어서", "드라마", 107, "12세 관람가", Arrays.asList(
            new Showtime("11:00", "1관", 5, 5),
            new Showtime("14:00", "2관", 5, 5),
            new Showtime("16:30", "3관", 5, 5)
        )));
        movieSelection.addMovie(new Movie1("킬", "액션/범죄", 105, "19세 관람가", Arrays.asList(
            new Showtime("12:00", "1관", 5, 5),
            new Showtime("15:00", "2관", 5, 5),
            new Showtime("17:30", "3관", 5, 5)
        )));
        movieSelection.addMovie(new Movie1("그 여름날의 거짓말", "멜로/로맨스, 미스터리", 138, "15세 관람가", Arrays.asList(
            new Showtime("13:00", "1관", 5, 5),
            new Showtime("16:00", "2관", 5, 5),
            new Showtime("18:30", "3관", 5, 5)
        )));
        movieSelection.addMovie(new Movie1("에프터 썬", "드라마", 101, "12세 관람가", Arrays.asList(
            new Showtime("14:00", "1관", 5, 5),
            new Showtime("17:00", "2관", 5, 5),
            new Showtime("19:30", "3관", 5, 5)
        )));

        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            // 영화 목록 출력
            System.out.println("\n<<<<<<<<<<영화 목록>>>>>>>>>>");
            movieSelection.getMovies().forEach(System.out::println);

            // 영화 선택
            System.out.println("\n보고 싶은 영화 제목을 입력하세요 (종료를 원하면 '종료'를 입력하세요):");
            String movieTitle = scanner.nextLine();
            
            if (movieTitle.equalsIgnoreCase("종료")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            Movie1 selectedMovie = movieSelection.searchMovie(movieTitle);
            if (selectedMovie != null) {
                movieSelection.displayShowtimes(selectedMovie);

                System.out.println("\n상영 시간과 상영관을 선택하세요 (예: 12:00 - 1관):");
                String showtimeInput = scanner.nextLine();
                Showtime selectedShowtime = selectedMovie.getShowtimes().stream()
                    .filter(showtime -> showtimeInput.equals(showtime.toString()))
                    .findFirst()
                    .orElse(null);

                if (selectedShowtime != null) {
                    selectedShowtime.displaySeats();

                    System.out.println("\n좌석을 선택하세요 (예: A1):");
                    String seat = scanner.nextLine();
                    if (selectedShowtime.bookSeat(seat)) {
                        System.out.println("예약이 완료되었습니다. 즐거운 관람되십시오.");
                    } else {
                        System.out.println("좌석이 유효하지 않거나 이미 예약되었습니다.");
                    }
                } else {
                    System.out.println("잘못된 상영 시간 또는 상영관을 선택했습니다.");
                }
            } else {
                System.out.println("입력한 제목의 영화를 찾을 수 없습니다.");
            }
        }

        scanner.close(); // Scanner 닫기
    }

	public static void startMovieReservation(User loggedInUser) {
		
		
	}
}







