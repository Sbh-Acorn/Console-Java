package payment;

import java.util.Scanner;

public class Pay {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
	
		int price; // 가격
		int total_price;	// 결재 총액
		
		Paper ticket = new Ticket();
		Paper receipt = new Receipt();
		Paper[] papers = new Paper[2];
		//영수증, 티켓
		
		papers[0] = ticket;
		papers[1] = receipt;
		
		ticket.Print();
		
		
		while(true) {
			int menuNum;
//			sc.nextLine();
			System.out.println("===================================");
			System.out.println("[결재창] 1.티켓출력 2.영수증출력 0.뒤로가기");
			System.out.println("-----------------------------------");
			System.out.print("입력> ");
			
			menuNum = sc.nextInt();
			
			switch(menuNum) {
			//티켓출력
			case 1:
				((Ticket)ticket).print();
				break;
			//영수증출력
			case 2:
				((Receipt)receipt).print();
				break;
			//뒤로가기
			case 0:
				break;
			}
		}
		
	}
	
}


