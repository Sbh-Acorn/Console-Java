package mainFunc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SelectSeat implements ConsolePrint {
    private Seat seat;
    private String theaterName;
    private String showTime;
    private User user;
    private String movieName;

    public SelectSeat(String theaterName, String showTime, User user, String movieName) {
        this.theaterName = theaterName;
        this.showTime = showTime;
        this.seat = new Seat(theaterName, showTime);
        this.user = user;
        this.movieName = movieName;
    }

    @Override
    public void print() {
        Scanner sc = new Scanner(System.in);
        List<int[]> selectedSeats = new ArrayList<>(); // 선택한 좌석들을 저장할 리스트

        // 좌석 상태 출력 (초기 상태)
        System.out.println("현재 좌석 상태:");
        displaySeats(seat.getSeats());

        // 좌석 선택 프로세스
        while (true) {
            System.out.println("예약할 좌석의 행 번호와 열 번호를 입력하세요 (예: 2 3), 또는 '완료'를 입력하세요:");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("완료")) {
                break; // 선택 완료 시 반복문 종료
            }

            // 좌표 입력 처리
            String[] parts = input.split(" ");
            if (parts.length == 2) {
                try {
                    int row = Integer.parseInt(parts[0]);
                    int col = Integer.parseInt(parts[1]);

                    int[][] seats = seat.getSeats();

                    if (row >= 0 && row < seats.length && col >= 0 && col < seats[0].length) {
                        if (seats[row][col] == 0) {
                            selectedSeats.add(new int[]{row, col}); // 선택된 좌석을 리스트에 추가
                            System.out.println("좌석이 선택되었습니다: 행 " + row + ", 열 " + col);
                        } else {
                            System.out.println("이미 예약된 좌석입니다. 다른 좌석을 선택해주세요.");
                        }
                    } else {
                        System.out.println("유효하지 않은 좌석입니다. 다시 시도해주세요.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("유효한 숫자를 입력해주세요.");
                }
            } else {
                System.out.println("올바른 형식으로 입력해주세요. 예: '2 3'");
            }
        }

        // 선택된 좌석들을 예약
        if (!selectedSeats.isEmpty()) {
            for (int[] seatPosition : selectedSeats) {
                seat.reserveSeat(seatPosition[0], seatPosition[1]);
            }
            System.out.println("선택된 좌석들이 예약 완료되었습니다.");
        } else {
            System.out.println("선택된 좌석이 없습니다.");
        }

        // 완료 후 좌석 상태 출력
        System.out.println("최종 좌석 상태:");
        displaySeats(seat.getSeats());

        // 좌석 수에 따른 어린이, 청소년, 성인 수 입력
        int totalSeats = selectedSeats.size();
        int childCount = 0;
        int youthCount = 0;
        int adultCount = 0;

        while (true) {
            System.out.println("선택한 좌석 수: " + totalSeats);
            System.out.print("어린이 수: ");
            childCount = sc.nextInt();
            System.out.print("청소년 수: ");
            youthCount = sc.nextInt();
            System.out.print("성인 수: ");
            adultCount = sc.nextInt();

            int totalSelected = childCount + youthCount + adultCount;

            if (totalSelected == totalSeats) {
                break;
            } else {
                System.out.println("선택한 좌석 수와 일치하지 않습니다. 다시 입력해주세요.");
            }
        }

        // Count 데이터를 Map으로 저장
        Map<String, Integer> countMap = new HashMap<>();
        countMap.put("childCount", childCount);
        countMap.put("youthCount", youthCount);
        countMap.put("adultCount", adultCount);

        // 결제 여부 확인
        while (true) {
            System.out.println("결제하시겠습니까? (y/n):");
            String decision = sc.next();

            if (decision.equalsIgnoreCase("y")) {
                // 결제 클래스 호출
                Payment payment = new Payment(user, selectedSeats, countMap, movieName, theaterName, showTime);
                payment.print();
                break;
            } else if (decision.equalsIgnoreCase("n")) {
                // 다시 좌석 선택 과정으로 돌아가기
                print();
                break;
            } else {
                System.out.println("잘못된 입력입니다. y 또는 n을 입력해주세요.");
            }
        }
    }

    // 좌석 상태를 출력하는 메서드
    private void displaySeats(int[][] seats) {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 0) {
                    System.out.print("[O] ");
                } else {
                    System.out.print("[X] ");
                }
            }
            System.out.println();
        }
    }
}
