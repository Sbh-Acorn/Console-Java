package joinLogin;

import java.util.Scanner;

public class SelectSeat {

	public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);

	        Cinema cinema1 = new Cinema("Cinema 1", 5, 5); 
	        Cinema cinema2 = new Cinema("Cinema 2", 8, 8); 
	        Cinema cinema3 = new Cinema("Cinema 3", 10, 10); 

	        Cinema[] cinemas = {cinema1, cinema2, cinema3};

	        System.out.println("상영관 선택 1 2 3 : ");
	        int cinemaChoice = scanner.nextInt();

	        if (cinemaChoice < 1 || cinemaChoice > 3) {
	            System.out.println("상영관 번호를 다시 확인해주세요.");
	            return;
	        }

	        Cinema selectedCinema = cinemas[cinemaChoice - 1];

	        selectedCinema.displaySeats();

	        System.out.println("예매하실 좌석을 선택해주세요 (예시-> 3 5):");
	        int row = scanner.nextInt();
	        int col = scanner.nextInt();

	        if (selectedCinema.bookSeat(row, col)) {
	            System.out.println("좌석 선택이 정상적으로 완료되었습니다.");
	        } else {
	            System.out.println("좌석 선택 불가");
	            System.out.println("이미 예약된 좌석이거나 좌석 번호를 다시 확인해주세요.");
	        }

	        scanner.close();
	    }
	

}
