package mainFunc;


public class Theater {
    private String theaterName;

    // Getters and Setters
    public String getTheaterName() {
        return theaterName;
    }        

    public void setShowTimes(String theaterName) {
        this.theaterName = theaterName;
    }

    @Override
    public String toString() {
        return theaterName;
    }
}