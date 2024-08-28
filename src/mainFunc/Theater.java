package mainFunc;

import java.util.Map;

public class Theater {
    private String theaterName;
    private int[] tsize;
    private Map<String,String[]> movieAndTime;
    
    public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public int[] getTsize() {
		return tsize;
	}

	public void setTsize(int[] tsize) {
		this.tsize = tsize;
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