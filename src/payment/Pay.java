package payment;

import java.util.Scanner;
import com.google.gson.Gson;

public class Pay {
	
	Gson gson = new Gson();
	Scanner sc = new Scanner(System.in);
	
	boolean bIsPayment = false;
	int price = 10000; // 가격
	int total_price;	// 결재 총액
	
	public static void main(String[] args) {
		
		Paper ticket = new Ticket();
		Paper receipt = new Receipt();
		Paper[] papers = new Paper[2];
		
		//영수증, 티켓
		
		papers[0] = ticket;
		papers[1] = receipt;
	}
	
	public boolean Payment() {
		
		//
		
		return bIsPayment;
	}
	
}


