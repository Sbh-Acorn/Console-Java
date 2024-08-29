package mainFunc;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Seat {
    private String theaterName;
    private String showTime;
    private int[][] seats;
    private int[] size;

    public Seat(String theaterName, String showTime) {
        this.theaterName = theaterName;
        this.showTime = showTime;
        loadSeatInfo(); // 클래스가 생성되면 자동으로 좌석 정보를 불러옵니다.
    }

    // seatinfo.json 파일에서 좌석 정보를 불러오는 메서드
    private void loadSeatInfo() {
        String key = theaterName + "_" + showTime;
        String filePath = "resource/seatinfo.json"; // 파일 경로

        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, SeatInfo>>() {}.getType();
            Map<String, SeatInfo> seatInfoMap = gson.fromJson(reader, type);

            if (seatInfoMap.containsKey(key)) {
                SeatInfo seatInfo = seatInfoMap.get(key);
                this.size = seatInfo.getSize();
                this.seats = seatInfo.getSeats();
            } else {
                System.out.println("해당 상영 시간대의 좌석 정보가 없습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 좌석 정보를 seatinfo.json 파일에 저장하는 메서드 (pretty 옵션 추가)
    public void saveSeatInfo() {
        String key = theaterName + "_" + showTime;
        String filePath = "resource/seatinfo.json"; // 파일 경로

        try (FileReader reader = new FileReader(filePath)) {
            // Gson 객체를 pretty 옵션으로 생성
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type type = new TypeToken<Map<String, SeatInfo>>() {}.getType();
            Map<String, SeatInfo> seatInfoMap = gson.fromJson(reader, type);

            // 좌석 정보를 업데이트하고 저장
            seatInfoMap.put(key, new SeatInfo(size, seats));

            try (FileWriter writer = new FileWriter(filePath)) {
                gson.toJson(seatInfoMap, writer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 좌석을 예약하는 메서드 (좌표를 받아서 좌석 상태를 1로 설정)
    public void reserveSeat(int row, int col) {
        if (seats[row][col] == 0) { // 0인 경우에만 예약 가능
            seats[row][col] = 1; // 예약된 좌석은 1로 표시
            saveSeatInfo(); // 업데이트된 좌석 정보를 저장
        } else {
            System.out.println("이미 예약된 좌석입니다.");
        }
    }

    // 좌석 정보를 반환하는 메서드
    public int[][] getSeats() {
        return seats;
    }

    // 상영관의 크기를 반환하는 메서드
    public int[] getSize() {
        return size;
    }
}

// 좌석 정보를 저장할 SeatInfo 클래스
class SeatInfo {
    private int[] size;
    private int[][] seats;

    public SeatInfo(int[] size, int[][] seats) {
        this.size = size;
        this.seats = seats;
    }

    public int[] getSize() {
        return size;
    }

    public int[][] getSeats() {
        return seats;
    }
}
