package mainFunc;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SelectTheather implements ConsolePrint {
    private String movieName;
    private String movieRating;
    private String genre;
    private String runningTime;
    private String releaseDate;
    private ArrayList<String> theaters;
    private User user;
    Scanner sc = new Scanner(System.in);

    public SelectTheather(Movie m,User user) {
    	this.user=user;
        movieName = m.getMovieName();
        movieRating = m.getMovieRating();
        genre = m.getGenre();
        runningTime = m.getRunningTime();
        releaseDate = m.getReleaseDate();
        theaters = m.getTheaters();
    }

    @Override
    public void print() {
        String filePath = "resource/theaterinfo.json"; // 파일 경로를 지정하세요
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, Theater>>() {}.getType();
            Map<String, Theater> theaterinfo = gson.fromJson(reader, type);

            System.out.println(movieName + "이 상영되는 상영관 목록입니다");
            System.out.println("    영화:" + movieName + "    장르:" + genre + "    러닝타임:" + runningTime);
            // 상영관 목록 출력
            for (int i = 0; i < theaters.size(); i++) {
                String nameTh = theaters.get(i);
                Theater th = theaterinfo.get(nameTh);

                if (th != null) {
                    th.setTheaterName(nameTh);

                    int[] thsize = th.getSize();
                    String[] times;
                    
                    if (th.getMovieAndTime().containsKey(movieName)) {
                        times = th.getMovieAndTime().get(movieName);
                        System.out.println("\n" + nameTh + "관 총(" + thsize[0] * thsize[1] + ")");
                        System.out.println("\n");

                        // 상영 시간 목록 출력
                        for (int j = 0; j < times.length; j++) {
                            System.out.print((j + 1) + ". " + times[j] + "    ");
                        }
                        System.out.println();
                    } else {
                        System.out.println(nameTh + "관에서 해당 영화의 상영 시간이 없습니다.");
                    }
                } else {
                    System.out.println(nameTh + "라는 이름의 상영관이 없습니다.");
                }
            }

            // 상영관 선택
            String selectedTheater;
            while (true) {
                System.out.println("상영관을 선택해주세요:");
                selectedTheater = sc.nextLine();
                if (theaterinfo.containsKey(selectedTheater)) {
                    break;
                } else {
                    System.out.println("잘못된 상영관 이름입니다. 다시 입력해주세요.");
                }
            }

            // 선택한 상영관에 대한 시간 선택
            Theater selectedTh = theaterinfo.get(selectedTheater);
            String[] selectedTimes = selectedTh.getMovieAndTime().get(movieName);
            int selectedTimeIndex = -1;

            while (true) {
                System.out.println("시간대를 선택해주세요:");
                if (sc.hasNextInt()) {
                    selectedTimeIndex = sc.nextInt() - 1;
                    sc.nextLine(); // 개행 문자 제거

                    if (selectedTimeIndex >= 0 && selectedTimeIndex < selectedTimes.length) {
                        break;
                    } else {
                        System.out.println("잘못된 상영 시간을 선택했습니다. 다시 입력해주세요.");
                    }
                } else {
                    System.out.println("숫자를 입력해주세요.");
                    sc.nextLine(); // 잘못된 입력 제거
                }
            }

            // 최종 선택 출력
            System.out.println("선택한 상영관: " + selectedTheater);
            System.out.println("선택한 시간: " + selectedTimes[selectedTimeIndex]);
            
            // 선택한 상영관과 시간을 기반으로 Seat 클래스에 정보를 저장
            Seat seat = new Seat(selectedTheater, selectedTimes[selectedTimeIndex]);
            seat.saveSeatInfo();  // 좌석 정보를 저장

            // 선택한 상영관과 시간을 기반으로 SelectSeat 호출
            SelectSeat selectSeat = new SelectSeat(selectedTheater, selectedTimes[selectedTimeIndex],user,movieName);
            selectSeat.print();  // 좌석 선택 화면으로 이동
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
