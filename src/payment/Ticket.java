package payment;

//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Ticket extends Paper {	
	
	String title = "영화 입장권";
	
	//임시지정
	String movie_title_kor = "토이스토리";
	String movie_title_eng = "ToyStory";
	String movie_date = "24/8/30, 14:35";
	String movie_seat = "F열 12번";
	int person_totalCount = 2;
	
	// 티켓출력
	@Override
	public void print() {
		
		String filePath = "resource/ticket.txt";
		PrintStream ps = null;
		PrintStream sysout = System.out;
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(filePath);
			ps = new PrintStream(fos);
			System.setOut(ps);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("╔════════════════════════════════════════╗");
		System.out.println("║███╗   ███╗ ██████╗ ██╗   ██╗██╗███████╗║");
		System.out.println("║████╗ ████║██╔═══██╗██║   ██║██║██╔════╝║");
		System.out.println("║██╔████╔██║██║   ██║██║   ██║██║█████╗  ║");
		System.out.println("║██║╚██╔╝██║██║   ██║╚██╗ ██╔╝██║██╔══╝  ║");
		System.out.println("║██║ ╚═╝ ██║╚██████╔╝ ╚████╔╝ ██║███████╗║");
		System.out.println("║╚═╝     ╚═╝ ╚═════╝   ╚═══╝  ╚═╝╚══════╝║");
		System.out.println("├----------------------------------------┤");
		System.out.println("⎮ ◼◼◼◼◼◼◼◼◼◼◼ ︎[︎" + title + "] ◼◼◼◼◼◼◼◼◼◼◼◼");		//영화 입장권
		System.out.println("├----------------------------------------┤");
		System.out.println("⎮ 영화제목: " + movie_title_kor);						//임시
		System.out.println("⎮        " + movie_title_eng);					//임시
		System.out.println("⎮ 상영일시: ");										//임시
		System.out.println("⎮ 좌석: ");											//임시
		System.out.println("⎮ 관람인원: " + person_totalCount + "명");				//임시
		System.out.println("├----------------------------------------┤");
		System.out.println("⎮ * 티켓 미참시 교환, 환불 불가");							//주의사항 안내메시지
		System.out.println("⎮ * 결재수단 변경 및 교환, 환불은 상영시간 전");
		System.out.println("⎮   구매한 매장에서 가능");
		System.out.println("⎮ * 입장지연에 따른 관람 불편 최소화를 위해");
		System.out.println("⎮   본영화는 약 10여분 후에 시작됩니다.");
		System.out.println("╚════════════════════════════════════════╝");
		
		System.setOut(sysout);
	}
}
