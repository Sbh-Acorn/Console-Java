package sub;

import java.util.ArrayList;
import java.util.List;

public class Showtime {
    private String time; // 상영 시간
    private String theater; // 상영관
    private String[][] seats; // 좌석 배열

    // 생성자
    public Showtime(String time, String theater, int rows, int cols) {
        this.time = time;
        this.theater = theater;
        this.seats = new String[rows][cols];
        initializeSeats();
    }

    // 좌석 초기화
    private void initializeSeats() {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                seats[i][j] = "O"; // 모든 좌석을 "사용 가능"으로 초기화
            }
        }
    }

    // 사용 가능한 좌석 리스트 반환
    public List<String> getAvailableSeats() {
        List<String> availableSeats = new ArrayList<>();
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j].equals("O")) {
                    availableSeats.add(Character.toString((char) ('A' + i)) + (j + 1));
                }
            }
        }
        return availableSeats;
    }

    // 좌석 예약 메서드
    public boolean bookSeat(String seat) {
        if (seat.length() < 2 || !Character.isLetter(seat.charAt(0)) || !Character.isDigit(seat.charAt(1))) {
            System.out.println("좌석 형식이 잘못되었습니다. 예: A1");
            return false;
        }

        int row = seat.charAt(0) - 'A';
        int col = Integer.parseInt(seat.substring(1)) - 1;

        if (row < 0 || row >= seats.length || col < 0 || col >= seats[0].length) {
            System.out.println("유효하지 않은 좌석 번호입니다.");
            return false;
        }

        if (seats[row][col].equals("O")) { // 좌석이 예약되지 않은 경우
            seats[row][col] = "X"; // 좌석을 예약됨("X")으로 변경
            return true;
        }
        return false;
    }

    // 좌석 배열 출력
    public void displaySeats() {
        System.out.println("\n사용 가능한 좌석 (O = 사용 가능, X = 예약됨):");
        System.out.print("   ");
        for (int i = 1; i <= seats[0].length; i++) {
            System.out.print(i + " "); // 열 번호 출력
        }
        System.out.println();

        for (int i = 0; i < seats.length; i++) {
            System.out.print((char) ('A' + i) + "  "); // 행 번호 출력
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] + " "); // 좌석 상태 출력
            }
            System.out.println();
        }
    }

    // 상영 시간 반환
    public String getTime() {
        return time;
    }

    // 상영관 반환
    public String getTheater() {
        return theater;
    }

    // 상영 정보 출력
    @Override
    public String toString() {
        return time + " - " + theater;
    }
}








