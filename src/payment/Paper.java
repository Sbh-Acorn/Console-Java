package payment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;


public abstract class Paper implements Printer {
	
	public String title;
	
	public boolean bIsPayment = false;
	public int price = 10000; // 가격
	public int total_price;	// 결재 총액
	public int price_tax;
	public String store_name;
	public String date_time;
	
	public String movie_title_kor;
	public String movie_title_eng;
	public String movie_date;
	public String movie_seat;
	public int person_totalCount;
	
	
	public Paper() {
		
	}
	
	public Paper(String title) {
		this.title = title;
	}

	public void Print() {
		// TODO Auto-generated method stub
		
	}
	

}
