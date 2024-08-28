package mainFunc;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SelectTheather implements ConsolePrint{
	private String movieName;
    private String movieRating;
    private String genre;
	private String runningTime;
    private String releaseDate;
    private ArrayList<String> theaters;
	
	public SelectTheather(Movie m) {
		movieName=m.getMovieName();
		movieRating=m.getMovieRating();
		genre=m.getGenre();
		runningTime=m.getRunningTime();
		releaseDate=m.getReleaseDate();
		theaters=m.getTheaters();
	}
	@Override
	public void print() {
		String filePath = "resource/theaterinfo.json"; // 파일 경로를 지정하세요
		try(FileReader reader =new FileReader(filePath)){
			Gson gson = new Gson();
			Type type = new TypeToken<Map<String,Theater>>(){}.getType();
			Map<String,Theater> theaterinfo = gson.fromJson(reader,type);
			
			
			System.out.println("영화관을 골라주세요.");
			System.out.println(movieName + "이 상영되는 상영관입니다");
			for(int i=0;i<theaters.size();i++) {
				String nameTh=theaters.get(i);
				Theater th = theaterinfo.get(nameTh);
				
			}
			System.out.println();
			
		}catch (Exception e) {
            e.printStackTrace();
        }

	}
}
