package mainFunc;

import java.util.Map;

public class Theater {
    private String theaterName;
    private int[] size;
    private Map<String,String[]> movieAndTime;
    
    public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public int[] getSize() {
		return size;
	}

	public void setTsize(int[] size) {
		this.size = size;
	}

	public Map<String, String[]> getMovieAndTime() {
		return movieAndTime;
	}

	public void setMovieAndTime(Map<String, String[]> movieAndTime) {
		this.movieAndTime = movieAndTime;
	}

	@Override
    public String toString() {
        return theaterName;
    }
}