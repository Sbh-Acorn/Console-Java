package mainFunc;

public class Pay {
    private String movieName;
    private String theaterName;
    private String showTime;
    private int childCount;
    private int youthCount;
    private int adultCount;
    private int totalPrice;
    private int discountedPrice;

    public Pay(String movieName, String theaterName, String showTime, int childCount, int youthCount, int adultCount, int totalPrice, int discountedPrice) {
        this.movieName = movieName;
        this.theaterName = theaterName;
        this.showTime = showTime;
        this.childCount = childCount;
        this.youthCount = youthCount;
        this.adultCount = adultCount;
        this.totalPrice = totalPrice;
        this.discountedPrice = discountedPrice;
    }

    // Getters
    public String getMovieName() {
        return movieName;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public String getShowTime() {
        return showTime;
    }

    public int getChildCount() {
        return childCount;
    }

    public int getYouthCount() {
        return youthCount;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getDiscountedPrice() {
        return discountedPrice;
    }
}
