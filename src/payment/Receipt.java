package payment;

public class Receipt extends Paper {
	
	String title = " $$$$$$$$$$$$︎ [영수증]  $$$$$$$$$$$$$︎ ";
	int totalPrice;
	int price;
	int price_tax;
	String store_name;
	String date_time;

	
	// 영수증 출력
	@Override
	public void print() {
	// TODO Auto-generated method stub
	
		System.out.println("-----------------------------------");
		System.out.println(title);										//영화 입장권
		System.out.println("-----------------------------------");
		System.out.println("   금액: " + price);				//임시
		System.out.println("   부가세: " + price_tax);				//임시
		System.out.println("   합계: " + totalPrice);									//임시
		System.out.println("-----------------------------------");
		System.out.println("   거래일시: " + date_time);										//임시
		System.out.println("   가맹점: " + store_name);		//임시
		
		
	}

}
