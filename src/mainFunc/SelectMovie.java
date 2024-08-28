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
	
    // main 메서드 추가
    public static void main(String[] args) {
        SelectMovie selectMovie = new SelectMovie();
        selectMovie.print(); // print 메서드 호출
    }
    @Override
    public void print() {
        System.out.println("영화를 선택해 주세요!");

        // 로컬 파일 시스템에서 JSON 파일 불러오기
        String filePath = "resource/movieinfo.json"; // 파일 경로를 지정하세요.
        
        try (FileReader reader = new FileReader(filePath)) { // FileReader 사용
            Gson gson = new Gson();


            Type type = new TypeToken<Map<String, Movie>>() {}.getType();
            Map<String, Movie> movies = gson.fromJson(reader, type);
            
            
            List<String> movieNames = new ArrayList<>(movies.keySet());
            // 영화 정보 출력
            for (int i = 0; i < movieNames.size(); i++) {
                System.out.println((i + 1) + ". " + movieNames.get(i));
            }
            int i=1;
//            for (String movieName : movies.keySet()) {
//                System.out.print(i+"." + movieName+"	");
//                Movie movie = movies.get(movieName);
//                System.out.println(movie);  // Movie 객체의 toString() 메서드가 호출되어 출력됨
//                i++;
//            }
            
            Scanner sc = new Scanner(System.in);
            int select=sc.nextInt();
            
       
            if (select > 0 && select <= movieNames.size()) {
                String selectedMovieName = movieNames.get(select - 1);
                
                System.out.println("선택한 영화: " + selectedMovieName);
            } else {
                System.out.println("잘못된 번호입니다.");
            }
            //JSON 데이터를 Java Map으로 변환
//          Type type = new TypeToken<Map<String, Object>>() {}.getType();
//          Map<String, Object> movies = gson.fromJson(reader, type);
//          System.out.println(movies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

// Movie 클래스 정의
//class Movie {
//    String movieRating;
//    String genre;
//    String runningTime;
//    String releaseDate;
//    Map<String, Theater> theaters;
//}

//class Theater {
//    Map<String, ArrayList<String>> showTimes;
//}

//package mainFunc;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import java.io.FileReader;
//import java.io.Reader;
//import java.lang.reflect.Type;
//import java.util.List;
//import java.util.Map;
//
//public class SelectMovie  {
//    // main 메서드 추가
//    public static void main(String[] args) {
//    	// JSON 파일 경로
//        String filePath = "res/movie.json";
//
//        try (Reader reader = new FileReader(filePath)) {
//            Gson gson = new Gson();
//
//            // JSON 데이터를 Java Map으로 변환
//            //    
//            //Type type = new TypeToken<Map<String, Object>>() {}.getType();   익명클래스 만들고 객체생성해서 사용하는 코드
//            
//            
//            Type type = new MapTypeToken().getType();
//            Map<String, Movie>  jsonMap = gson.fromJson(reader, type);
//
//            // 데이터 출력
//            System.out.println(jsonMap);
//            
//           
//            
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    
//}
