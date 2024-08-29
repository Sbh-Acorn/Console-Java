package mainFunc;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SelectMovie implements ConsolePrint {

    private User user;

    public SelectMovie(User user) {
        this.user = user;
    }

    @Override
    public void print() {
        Scanner sc = new Scanner(System.in);
        System.out.println("환영합니다, " + user.getId() + "님!");

        while (true) {
            System.out.println("=======메뉴 선택=======");
            System.out.println("    1. 영화 예매       ");
            System.out.println("    2. 정보 조회       ");
            System.out.println("    3. 로그아웃       ");
            System.out.println("=====================");
            System.out.print("메뉴를 선택하세요: ");
            int menuChoice = sc.nextInt();

            switch (menuChoice) {
                case 1:
                    selectMovie();
                    break;
                case 2:
                    viewUserInfo();
                    break;
                case 3:
                    logout();
                    return;  // 로그아웃 후 메서드 종료
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
            }
        }
    }
    
    // 영화 선택 및 예매 기능
    private void selectMovie() {
        System.out.println("영화를 선택해 주세요!");

        String filePath = "resource/movieinfo.json"; // 파일 경로를 지정하세요.

        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, Movie>>() {}.getType();
            Map<String, Movie> movies = gson.fromJson(reader, type);

            List<String> movieNames = new ArrayList<>(movies.keySet());

            for (int i = 0; i < movieNames.size(); i++) {
                String mn = movieNames.get(i);
                Movie m = movies.get(mn);
                System.out.println((i + 1) + ". " + mn + " (" + m.getMovieRating() + ")");
            }

            Scanner sc = new Scanner(System.in);
            int select = sc.nextInt();

            if (select > 0 && select <= movieNames.size()) {
                String selectedMovieName = movieNames.get(select - 1);
                System.out.println("선택한 영화: " + selectedMovieName);
                Movie selectm = movies.get(selectedMovieName);
                selectm.setMovieName(selectedMovieName);
                SelectTheather st = new SelectTheather(selectm, user);
                st.print();
            } else {
                System.out.println("잘못된 번호입니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 사용자 정보 조회 기능
    private void viewUserInfo() {
        System.out.println("사용자 정보 조회:");
        System.out.println("ID: " + user.getId());
        System.out.println("마일리지: " + user.getMileage());

        // 결제 정보 조회
        String filePath = "resource/payinfo.json";

        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, Pay>>() {}.getType();
            Map<String, Pay> payInfoMap = gson.fromJson(reader, type);

            if (payInfoMap != null && payInfoMap.containsKey(user.getId())) {
                Pay payInfo = payInfoMap.get(user.getId());

                System.out.println("결제 내역:");
                System.out.println("영화: " + payInfo.getMovieName());
                System.out.println("상영관: " + payInfo.getTheaterName());
                System.out.println("상영시간: " + payInfo.getShowTime());
                System.out.println("어린이 수: " + payInfo.getChildCount());
                System.out.println("청소년 수: " + payInfo.getYouthCount());
                System.out.println("성인 수: " + payInfo.getAdultCount());
                System.out.println("총 금액: " + payInfo.getTotalPrice() + "원");
                System.out.println("할인 적용 금액: " + payInfo.getDiscountedPrice() + "원");
            } else {
                System.out.println("결제 정보가 없습니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 로그아웃 기능
    private void logout() {
        System.out.println("로그아웃 합니다.");
        Login log = new Login();
        log.print();
    }
}
