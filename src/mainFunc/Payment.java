package mainFunc;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Payment implements ConsolePrint {
    private User user;
    private List<int[]> selectedSeats;
    private Map<String, Integer> countMap;
    private String movieName;
    private String theaterName;
    private String showTime;

    // 가격 정의
    private static final int CHILD_PRICE = 8000;
    private static final int YOUTH_PRICE = 10000;
    private static final int ADULT_PRICE = 15000;

    public Payment(User user, List<int[]> selectedSeats, Map<String, Integer> countMap, String movieName, String theaterName, String showTime) {
        this.user = user;
        this.selectedSeats = selectedSeats;
        this.countMap = countMap;
        this.movieName = movieName;
        this.theaterName = theaterName;
        this.showTime = showTime;
    }

    @Override
    public void print() {
        // 가격 계산
        int totalChildPrice = countMap.get("childCount") * CHILD_PRICE;
        int totalYouthPrice = countMap.get("youthCount") * YOUTH_PRICE;
        int totalAdultPrice = countMap.get("adultCount") * ADULT_PRICE;

        int totalPrice = totalChildPrice + totalYouthPrice + totalAdultPrice;

        // 마일리지에 따른 할인 계산
        double discount = 0.0;
        switch (user.getMileage().toLowerCase()) {
            case "bronze":
                discount = 0.01;
                break;
            case "silver":
                discount = 0.02;
                break;
            case "gold":
                discount = 0.03;
                break;
        }

        double discountedPrice = totalPrice * (1 - discount);

        // 좌석 정보 출력
        StringBuilder seatsInfo = new StringBuilder();
        for (int[] seat : selectedSeats) {
            seatsInfo.append("행 ").append(seat[0] + 1).append(", 열 ").append(seat[1] + 1).append(" | ");
        }

        // 영수증 출력
        System.out.println("========== 영수증 ==========");
        System.out.println("영화: " + movieName);
        System.out.println("상영관: " + theaterName);
        System.out.println("상영시간: " + showTime);
        System.out.println("좌석: " + seatsInfo.toString());
        System.out.println("어린이: " + countMap.get("childCount") + "명, 총 가격: " + totalChildPrice + "원");
        System.out.println("청소년: " + countMap.get("youthCount") + "명, 총 가격: " + totalYouthPrice + "원");
        System.out.println("성인: " + countMap.get("adultCount") + "명, 총 가격: " + totalAdultPrice + "원");
        System.out.println("----------------------------");
        System.out.println("총 가격: " + totalPrice + "원");
        System.out.println("마일리지(" + user.getMileage() + ") 할인: -" + (int)(totalPrice * discount) + "원");
        System.out.println("할인 적용 가격: " + (int)discountedPrice + "원");
        System.out.println("============================");

        // 결제 정보를 Pay 객체로 저장
        Pay payInfo = new Pay(movieName, theaterName, showTime, countMap.get("childCount"), countMap.get("youthCount"), countMap.get("adultCount"), totalPrice, (int)discountedPrice);
        savePayInfo(user.getId(), payInfo);
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("영화를 추가 예매하시겠습니까? (y/n):");
            String decision = sc.next();

            if (decision.equalsIgnoreCase("y")) {
                System.out.println("영화 예매 화면으로 돌아갑니다.");
                SelectMovie sm = new SelectMovie(user);
                sm.print();
                break;
            } else if (decision.equalsIgnoreCase("n")) {
                System.out.println("로그아웃 합니다.");
                Login log = new Login();
                log.print();
                break;
            } else {
                System.out.println("잘못된 입력입니다. y 또는 n을 입력해주세요.");
            }
        }
    }

    // 결제 정보를 JSON 파일에 저장하는 메서드
    private void savePayInfo(String userId, Pay payInfo) {
        String filePath = "resource/payinfo.json";

        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type type = new TypeToken<Map<String, Pay>>() {}.getType();
            Map<String, Pay> payInfoMap = gson.fromJson(reader, type);

            // payInfoMap이 null일 경우, 새로운 HashMap으로 초기화
            if (payInfoMap == null) {
                payInfoMap = new HashMap<>();
            }

            // 기존 데이터가 있을 경우 업데이트, 없으면 새로 추가
            payInfoMap.put(userId, payInfo);

            try (FileWriter writer = new FileWriter(filePath)) {
                gson.toJson(payInfoMap, writer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
